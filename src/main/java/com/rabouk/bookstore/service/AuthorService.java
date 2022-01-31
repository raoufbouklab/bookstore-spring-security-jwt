package com.rabouk.bookstore.service;

import com.rabouk.bookstore.domain.dto.AuthorRequestDto;
import com.rabouk.bookstore.domain.dto.AuthorResponseDto;
import com.rabouk.bookstore.domain.dto.BookResponseDto;
import com.rabouk.bookstore.domain.exception.FoundException;
import com.rabouk.bookstore.domain.exception.NotFoundException;
import com.rabouk.bookstore.domain.mapper.AuthorMapper;
import com.rabouk.bookstore.domain.mapper.BookMapper;
import com.rabouk.bookstore.domain.model.Author;
import com.rabouk.bookstore.repository.AuthorRepository;
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
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;

    public List<AuthorResponseDto> getAllActiveAuthors() {
        return authorMapper.mapAuthorToAuthorResponseDto(authorRepository.findAll()
                .stream()
                .filter(Author::isActive)
                .toList());
    }

    @Transactional
    public AuthorResponseDto create(AuthorRequestDto authorRequestDto) {
        Optional<Author> optionalAuthor = authorRepository.findByFirstNameAndLastName(authorRequestDto.getFirstName(),
                authorRequestDto.getLastName());
        if (optionalAuthor.isPresent()) {
            throw new FoundException("Author exists!");
        }
        Author author = authorMapper.mapAuthorRequestDtoToAuthor(authorRequestDto);
        author = authorRepository.save(author);
        return authorMapper.mapAuthorToAuthorResponseDto(author);
    }

    @Transactional
    public void update(Long id, AuthorRequestDto authorDto) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty() || !author.get().isActive()) {
            throw new NotFoundException("No author found by this Id!");
        }
        authorMapper.update(authorDto, author.get());
        authorRepository.save(author.get());
    }

    public AuthorResponseDto getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty() || !author.get().isActive()) {
            throw new NotFoundException("Author not found");
        }
        return authorMapper.mapAuthorToAuthorResponseDto(author.get());
    }

    public void deleteAuthor(Long id) {
        Optional<Author> authorToDelete = authorRepository.findById(id);
        if (authorToDelete.isEmpty() || !authorToDelete.get().isActive()) {
            throw new NotFoundException("Author not found");
        }
        Author author = authorToDelete.get();
        author.setActive(false);
        authorRepository.save(author);
    }

    public List<BookResponseDto> getBooksByAuthorId(Long authorId) {
        Author author = authorRepository.getById(authorId);
        return bookMapper.mapBookToBookResponseDto(author.getBooks());
    }
}
