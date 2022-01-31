package com.rabouk.bookstore.api;

import com.rabouk.bookstore.domain.dto.UserLoginDto;
import com.rabouk.bookstore.domain.dto.UserRequestDto;
import com.rabouk.bookstore.domain.dto.UserResponseDto;
import com.rabouk.bookstore.domain.mapper.UserMapper;
import com.rabouk.bookstore.domain.model.User;
import com.rabouk.bookstore.security.jwt.JwtTokenUtil;
import com.rabouk.bookstore.security.service.CustomUserDetails;
import com.rabouk.bookstore.security.service.CustomUserDetailsService;
import com.rabouk.bookstore.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-15, Saturday
 **/

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final CustomUserDetailsService userDetailsService;
    private final UserMapper userMapper;

    @ApiOperation(value = "login")
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@Valid @RequestBody final UserLoginDto userLoginDto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));
            final UserDetails userDetails = userDetailsService.loadUserByUsername(userLoginDto.getUsername());
            User user = ((CustomUserDetails) authenticate.getPrincipal()).getUser();
            UserResponseDto userResponseDto = userMapper.mapUserToUserResponseDto(user);
            String token = jwtTokenUtil.generateJwtToken(userDetails);
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(userResponseDto);
        } catch (BadCredentialsException exception) {
            throw new BadCredentialsException("Incorrect username or password", exception);
        }
    }

    @ApiOperation(value = "Register new user")
    @PostMapping(value = "/register")
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody final UserRequestDto userRequestDto) {
        return new ResponseEntity<>(userService.register(userRequestDto), HttpStatus.CREATED);
    }
}
