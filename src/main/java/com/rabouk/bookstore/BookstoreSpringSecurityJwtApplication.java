package com.rabouk.bookstore;

import com.rabouk.bookstore.domain.enums.Roles;
import com.rabouk.bookstore.domain.model.Role;
import com.rabouk.bookstore.domain.model.User;
import com.rabouk.bookstore.repository.RoleRepository;
import com.rabouk.bookstore.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@EnableJpaAuditing
@SpringBootApplication
@Slf4j
public class BookstoreSpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreSpringSecurityJwtApplication.class, args);
    }

    @Bean
    CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {

        return args -> {
            var listRoles = List.of(
                    new Role(Roles.ADMIN),
                    new Role(Roles.USER),
                    new Role(Roles.AUTHOR_ADMIN),
                    new Role(Roles.BOOK_ADMIN));
            listRoles.forEach(role -> addRoleIfNotExist(roleRepository, role));
            User user = new User();
            user.setUsername("raouf.bouklab");
            user.setFirstName("Raouf");
            user.setLastName("Bouklab");
            user.setPassword(passwordEncoder.encode("Test_42519"));
            user.setEmail("test@test.dz");
            Optional<Role> role = roleRepository.findRoleByAuthority(Roles.ADMIN);
            Set<Role> authorities = new HashSet<>();
            role.ifPresent(authorities::add);
            user.setRoles(authorities);
            userRepository.save(user);
            userRepository.findAll().forEach(user1 -> log.info("User {} created", user1.toString()));
        };
    }

    private void addRoleIfNotExist(RoleRepository roleRepository, Role role) {
        if (roleRepository.findRoleByAuthority(role.getAuthority()).isEmpty()) {
            log.info("Role {} created", role.getAuthority());
            roleRepository.save(role);
        }
    }
}
