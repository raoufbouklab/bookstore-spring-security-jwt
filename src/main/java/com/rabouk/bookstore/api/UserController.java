package com.rabouk.bookstore.api;

import com.rabouk.bookstore.domain.dto.UserRequestDto;
import com.rabouk.bookstore.domain.dto.UserResponseDto;
import com.rabouk.bookstore.domain.enums.Roles;
import com.rabouk.bookstore.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-15, Saturday
 **/

@RestController
@RequiredArgsConstructor
@RolesAllowed(Roles.ADMIN)
@RequestMapping("/bookstore/v1/user")
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "Get all users")
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllActiveUsers() {
        return new ResponseEntity<>(userService.getAllActiveUsers(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get user by Id")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(userService.findUser(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Update user")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @Valid @RequestBody final UserRequestDto userRequestDto) {
        userService.updateUser(id, userRequestDto);
        return new ResponseEntity<>("User successfully updated", HttpStatus.OK);
    }

    @ApiOperation(value = "Delete user")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
    }

    @ApiOperation(value = "create user")
    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody final UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.register(userRequestDto), HttpStatus.CREATED);
    }
}
