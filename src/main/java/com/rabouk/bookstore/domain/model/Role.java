package com.rabouk.bookstore.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-15, Saturday
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "authority", unique = true, nullable = false)
    private String authority;

    public Role(String authority) {
        this.authority = authority;
    }
}
