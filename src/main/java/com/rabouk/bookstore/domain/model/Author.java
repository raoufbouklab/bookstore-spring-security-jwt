package com.rabouk.bookstore.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
@Table(name = "authors")
public class Author {
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

    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;
    private String about;
    private String nationality;
    private boolean active = true;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();
}
