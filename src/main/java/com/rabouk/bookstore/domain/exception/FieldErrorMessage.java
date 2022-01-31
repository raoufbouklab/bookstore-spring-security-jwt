package com.rabouk.bookstore.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-15, Saturday
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldErrorMessage {
    private String field;
    private String message;
}
