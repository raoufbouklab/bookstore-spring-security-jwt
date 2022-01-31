package com.rabouk.bookstore.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-15, Saturday
 **/

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")})
public class User {
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

    @Column(name = "username", unique = true, nullable = false, length = 30)
    private String username;
    @Column(name = "first_name", nullable = false, length = 30)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 30)
    private String lastName;
    @Column(name = "email", unique = true, nullable = false, length = 50)
    private String email;
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @Column(name = "active")
    private boolean active = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private Set<Role> roles;
}
