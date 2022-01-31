package com.rabouk.bookstore.repository;

import com.rabouk.bookstore.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-15, Saturday
 **/

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
