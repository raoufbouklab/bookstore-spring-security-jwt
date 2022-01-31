package com.rabouk.bookstore.domain.enums;

import lombok.Data;

@Data
public class Roles {

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
    public static final String AUTHOR_ADMIN = "AUTHOR_ADMIN";
    public static final String BOOK_ADMIN = "BOOK_ADMIN";

    private Roles() {
        //private constructor
    }
}
