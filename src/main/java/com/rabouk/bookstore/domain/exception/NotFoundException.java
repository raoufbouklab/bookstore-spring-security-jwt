package com.rabouk.bookstore.domain.exception;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-15, Saturday
 **/

public class NotFoundException extends RuntimeException {

    public NotFoundException(String exception) {
        super(exception);
    }
}
