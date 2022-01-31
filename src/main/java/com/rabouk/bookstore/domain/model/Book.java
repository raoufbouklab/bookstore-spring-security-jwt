package com.rabouk.bookstore.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-26, Wednesday
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(name = "last_modified_date")
    private LocalDateTime modifiedAt;

    private String title;
    private String about;
    private Integer noPage;
    private String language;
    private String isbn13;
    private String publisher;
    private LocalDate publishDate;
    private Boolean deleted = false;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "authors_books",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")})
    private List<Author> authors = new ArrayList<>();
}
