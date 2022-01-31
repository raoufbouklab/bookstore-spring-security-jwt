package com.rabouk.bookstore.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-26, Wednesday
 **/

@Data
public class BookRequestDto {

    @NotNull
    @NotBlank(message = "Title is mandatory")
    private String title;
    private String about;
    private Integer noPage;
    private String language;
    private String isbn13;
    private String publisher;
    private LocalDate publishDate;
    private List<Long> authorsIds;
}
