package com.rabouk.bookstore.api;

import com.rabouk.bookstore.domain.dto.AuthorResponseDto;
import com.rabouk.bookstore.domain.dto.BookRequestDto;
import com.rabouk.bookstore.domain.dto.BookResponseDto;
import com.rabouk.bookstore.domain.enums.Roles;
import com.rabouk.bookstore.service.BookService;
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
@RequestMapping("/bookstore/v1/book")
public class BookController {
    private final BookService bookService;

    @RolesAllowed({Roles.ADMIN, Roles.BOOK_ADMIN})
    @ApiOperation(value = "create book")
    @PostMapping
    public ResponseEntity<BookResponseDto> create(@Valid @RequestBody final BookRequestDto bookRequestDto) {
        return new ResponseEntity<>(bookService.create(bookRequestDto), HttpStatus.CREATED);
    }

    @RolesAllowed({Roles.ADMIN, Roles.BOOK_ADMIN})
    @ApiOperation(value = "Update book")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody final BookRequestDto bookRequestDto) {
        bookService.update(id, bookRequestDto);
        return new ResponseEntity<>("Book successfully updated", HttpStatus.OK);
    }

    @RolesAllowed({Roles.ADMIN, Roles.BOOK_ADMIN})
    @ApiOperation(value = "Delete book")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book successfully deleted", HttpStatus.OK);
    }

    @ApiOperation(value = "Get all books")
    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get book by Id")
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@Valid @PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @GetMapping("{id}/authors")
    public ResponseEntity<List<AuthorResponseDto>> getAuthorsByBookId(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getAuthorsByBookId(id), HttpStatus.OK);
    }
}
