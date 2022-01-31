package com.rabouk.bookstore.domain.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-26, Wednesday
 **/

@Data
public class BookResponseDto {

    private Long id;
    private String title;
    private String about;
    private Integer noPage;
    private String language;
    private String isbn13;
    private String publisher;
    private LocalDate publishDate;
}
