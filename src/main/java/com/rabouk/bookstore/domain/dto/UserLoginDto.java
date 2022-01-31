package com.rabouk.bookstore.domain.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-15, Saturday
 **/

@Data
public class UserLoginDto {

    @Length.List({
            @Length(min = 5, message = "The field must be at least 5 characters"),
            @Length(max = 30, message = "The field must be less than 30 characters")
    })
    @NotBlank
    private String username;

    @Length.List({
            @Length(min = 5, message = "The field must be at least 5 characters"),
            @Length(max = 60, message = "The field must be less than 30 characters")
    })
    @NotBlank
    private String password;
}
