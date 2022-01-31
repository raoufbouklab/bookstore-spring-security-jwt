package com.rabouk.bookstore.domain.mapper;

import com.rabouk.bookstore.domain.dto.BookRequestDto;
import com.rabouk.bookstore.domain.dto.BookResponseDto;
import com.rabouk.bookstore.domain.model.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-26, Wednesday
 **/

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book mapBookRequestDtoToBook(BookRequestDto bookRequestDto);

    BookResponseDto mapBookToBookResponseDto(Book book);

    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    void update(BookRequestDto bookRequestDto, @MappingTarget Book book);

    List<BookResponseDto> mapBookToBookResponseDto(List<Book> books);
}
