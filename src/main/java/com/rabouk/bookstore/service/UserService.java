package com.rabouk.bookstore.service;

import com.rabouk.bookstore.domain.dto.UserRequestDto;
import com.rabouk.bookstore.domain.dto.UserResponseDto;
import com.rabouk.bookstore.domain.enums.Roles;
import com.rabouk.bookstore.domain.exception.FoundException;
import com.rabouk.bookstore.domain.exception.NotFoundException;
import com.rabouk.bookstore.domain.mapper.UserMapper;
import com.rabouk.bookstore.domain.model.Role;
import com.rabouk.bookstore.domain.model.User;
import com.rabouk.bookstore.repository.RoleRepository;
import com.rabouk.bookstore.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author : Raouf Bouklab
 * @created : 2022-01-15, Saturday
 **/

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDto register(UserRequestDto userRequestDto) {
        log.info("Register user: {} {}", userRequestDto.getFirstName(), userRequestDto.getLastName());

        if (userRepository.findByUsername(userRequestDto.getUsername()).isPresent()) {
            throw new FoundException("Username exists!");
        }

        User user = userMapper.mapUserRequestDtoToUser(userRequestDto);
        user = saveUser(userRequestDto, user);
        return userMapper.mapUserToUserResponseDto(user);
    }

    @Transactional
    public void updateUser(Long id, UserRequestDto userRequestDto) {
        log.info("Update user: {}", id);

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty() || !user.get().isActive()) {
            throw new NotFoundException("No user found by this Id!");
        }

        if (!user.get().getUsername().equals(userRequestDto.getUsername())) {
            throw new ValidationException("Username don't match!");
        }
        userMapper.update(userRequestDto, user.get());
        saveUser(userRequestDto, user.get());
    }

    private User saveUser(UserRequestDto userRequestDto, User user) {
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        Set<Role> authorities = new HashSet<>();
        if (userRequestDto.getAuthorities() != null && !userRequestDto.getAuthorities().isEmpty()) {
            userRequestDto.getAuthorities().forEach(authority -> {
                Optional<Role> role = roleRepository.findRoleByAuthority(authority);
                role.ifPresent(authorities::add);
            });
        } else {
            roleRepository.findRoleByAuthority(Roles.USER).ifPresent(authorities::add);
        }

        user.setRoles(authorities);
        return userRepository.save(user);
    }

    public List<UserResponseDto> getAllActiveUsers() {
        return userMapper.mapUserToUserResponseDto(userRepository.findAll()
                .stream()
                .filter(User::isActive)
                .toList());
    }

    public UserResponseDto findUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty() || !user.get().isActive()) {
            throw new NotFoundException("User not found");
        }
        return userMapper.mapUserToUserResponseDto(user.get());
    }

    public void deleteUser(Long id) {
        Optional<User> userToDelete = userRepository.findById(id);
        if (userToDelete.isEmpty() || !userToDelete.get().isActive()) {
            throw new NotFoundException("User not found");
        }
        User user = userToDelete.get();
        user.setActive(false);
        userRepository.save(user);
    }
}
