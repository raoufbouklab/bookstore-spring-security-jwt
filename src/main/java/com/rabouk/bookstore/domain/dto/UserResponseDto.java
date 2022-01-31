package com.rabouk.bookstore.domain.dto;

import lombok.Data;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-15, Saturday
 **/

@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
}
