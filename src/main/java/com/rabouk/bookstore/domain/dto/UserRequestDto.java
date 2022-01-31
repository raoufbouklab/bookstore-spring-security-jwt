package com.rabouk.bookstore.domain.dto;

import com.rabouk.bookstore.validation.PasswordValueMatch;
import com.rabouk.bookstore.validation.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-15, Saturday
 **/

@PasswordValueMatch.List({
        @PasswordValueMatch(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "Passwords do not match!"
        )
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {
    @Length.List({
            @Length(min = 5, message = "The field must be at least 5 characters"),
            @Length(max = 30, message = "The field must be less than 30 characters")
    })
    @NotBlank
    private String username;

    @Length(max = 30, message = "The field must be less than 30 characters")
    @NotBlank
    private String firstName;

    @Length(max = 30, message = "The field must be less than 30 characters")
    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @ValidPassword
    @NonNull
    @NotBlank(message = "New password is mandatory")
    private String password;

    @ValidPassword
    @NonNull
    @NotBlank(message = "Confirm Password is mandatory")
    private String confirmPassword;

    private Set<String> authorities;
}
