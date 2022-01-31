package com.rabouk.bookstore.domain.mapper;

import com.rabouk.bookstore.domain.dto.AuthorRequestDto;
import com.rabouk.bookstore.domain.dto.AuthorResponseDto;
import com.rabouk.bookstore.domain.model.Author;
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
public interface AuthorMapper {
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    Author mapAuthorRequestDtoToAuthor(AuthorRequestDto authorRequestDto);

    AuthorResponseDto mapAuthorToAuthorResponseDto(Author author);

    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    void update(AuthorRequestDto authorDto, @MappingTarget Author author);

    List<AuthorResponseDto> mapAuthorToAuthorResponseDto(List<Author> authors);
}
