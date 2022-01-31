package com.rabouk.bookstore.domain.enums;

import lombok.Data;

@Data
public class Roles {

    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";
    public static final String AUTHOR_ADMIN = "ROLE_AUTHOR_ADMIN";
    public static final String BOOK_ADMIN = "ROLE_BOOK_ADMIN";

    private Roles() {
        //private constructor
    }
}
