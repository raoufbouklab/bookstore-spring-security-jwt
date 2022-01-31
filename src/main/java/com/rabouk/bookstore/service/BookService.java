package com.rabouk.bookstore.service;

import com.rabouk.bookstore.domain.dto.AuthorResponseDto;
import com.rabouk.bookstore.domain.dto.BookRequestDto;
import com.rabouk.bookstore.domain.dto.BookResponseDto;
import com.rabouk.bookstore.domain.exception.NotFoundException;
import com.rabouk.bookstore.domain.mapper.AuthorMapper;
import com.rabouk.bookstore.domain.mapper.BookMapper;
import com.rabouk.bookstore.domain.model.Book;
import com.rabouk.bookstore.repository.AuthorRepository;
import com.rabouk.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-26, Wednesday
 **/

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;

    public List<BookResponseDto> getAllBooks() {
        return bookMapper.mapBookToBookResponseDto(bookRepository.findAll()
                .stream().filter(book -> !book.getDeleted()).toList());
    }

    @Transactional
    public BookResponseDto create(BookRequestDto bookRequestDto) {
        Book book = bookMapper.mapBookRequestDtoToBook(bookRequestDto);

        book.setAuthors(bookRequestDto
                .getAuthorsIds()
                .stream()
                .map(authorRepository::getById)
                .toList());

        book = bookRepository.save(book);
        return bookMapper.mapBookToBookResponseDto(book);
    }

    @Transactional
    public void update(Long id, BookRequestDto bookRequestDto) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty() || book.get().getDeleted()) {
            throw new NotFoundException("No book found by this Id!");
        }

        bookMapper.update(bookRequestDto, book.get());
        bookRepository.save(book.get());
    }

    public BookResponseDto getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty() || book.get().getDeleted()) {
            throw new NotFoundException("Book not found");
        }
        return bookMapper.mapBookToBookResponseDto(book.get());
    }

    public void deleteBook(Long id) {
        Optional<Book> bookToDelete = bookRepository.findById(id);
        if (bookToDelete.isEmpty() || bookToDelete.get().getDeleted()) {
            throw new NotFoundException("Book not found");
        }
        Book book = bookToDelete.get();
        book.setDeleted(true);
        bookRepository.save(book);

    }

    public List<AuthorResponseDto> getAuthorsByBookId(Long id) {
        Book book = bookRepository.getById(id);
        return authorMapper.mapAuthorToAuthorResponseDto(book.getAuthors());
    }
}
