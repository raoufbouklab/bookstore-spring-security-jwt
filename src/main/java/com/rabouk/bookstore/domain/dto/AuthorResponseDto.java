package com.rabouk.bookstore.domain.dto;

import lombok.Data;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-26, Wednesday
 **/

@Data
public class AuthorResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String about;
    private String nationality;
}
