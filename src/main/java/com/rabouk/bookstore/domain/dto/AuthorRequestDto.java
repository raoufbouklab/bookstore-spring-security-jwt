package com.rabouk.bookstore.domain.dto;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-26, Wednesday
 **/

@Data
public class AuthorRequestDto {

    @NonNull
    @NotBlank(message = "Firstname is mandatory")
    private String firstName;
    @NonNull
    @NotBlank(message = "Lastname is mandatory")
    private String lastName;
    private String about;
    private String nationality;
}
