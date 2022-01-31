package com.rabouk.bookstore.domain.mapper;

import com.rabouk.bookstore.domain.dto.UserRequestDto;
import com.rabouk.bookstore.domain.dto.UserResponseDto;
import com.rabouk.bookstore.domain.model.User;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-15, Saturday
 **/

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User mapUserRequestDtoToUser(UserRequestDto userRequestDto);

    UserResponseDto mapUserToUserResponseDto(User user);

    @BeanMapping(nullValueCheckStrategy = ALWAYS, nullValuePropertyMappingStrategy = IGNORE)
    void update(UserRequestDto userRequestDto, @MappingTarget User user);

    List<UserResponseDto> mapUserToUserResponseDto(List<User> users);
}
