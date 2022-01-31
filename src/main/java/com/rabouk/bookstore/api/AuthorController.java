package com.rabouk.bookstore.api;

import com.rabouk.bookstore.domain.dto.AuthorRequestDto;
import com.rabouk.bookstore.domain.dto.AuthorResponseDto;
import com.rabouk.bookstore.domain.dto.BookResponseDto;
import com.rabouk.bookstore.domain.enums.Roles;
import com.rabouk.bookstore.service.AuthorService;
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
 * @created : 2022-01-26, Wednesday
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookstore/v1/author")
public class AuthorController {

    private final AuthorService authorService;

    @RolesAllowed({Roles.ADMIN, Roles.AUTHOR_ADMIN})
    @ApiOperation(value = "create author")
    @PostMapping
    public ResponseEntity<AuthorResponseDto> create(@Valid @RequestBody final AuthorRequestDto authorDto) {
        return new ResponseEntity<>(authorService.create(authorDto), HttpStatus.CREATED);
    }

    @RolesAllowed({Roles.ADMIN, Roles.AUTHOR_ADMIN})
    @ApiOperation(value = "Update author")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody final AuthorRequestDto authorDto) {
        authorService.update(id, authorDto);
        return new ResponseEntity<>("Author successfully updated", HttpStatus.OK);
    }

    @RolesAllowed({Roles.ADMIN, Roles.AUTHOR_ADMIN})
    @ApiOperation(value = "Delete author")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>("Author successfully deleted", HttpStatus.OK);
    }

    @ApiOperation(value = "Get all authors")
    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAllActiveAuthors() {
        return new ResponseEntity<>(authorService.getAllActiveAuthors(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get author by Id")
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @GetMapping("{id}/books")
    public ResponseEntity<List<BookResponseDto>> getBooksByAuthorId(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.getBooksByAuthorId(id), HttpStatus.OK);
    }
}
