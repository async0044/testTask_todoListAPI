package com.learning.todoList.repository;

import com.learning.todoList.entity.User;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByUsername(String username);  //для Security
    public Optional<User> findByEmail(String email);

    @Transactional
    default Optional<User> deleteByIdAndReturn(Long id) {
        Optional<User> optional = findById(id);
        optional.ifPresent(this::delete);
        return optional;
    }

    boolean existsByUsernameOrEmail(String username, String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

}
