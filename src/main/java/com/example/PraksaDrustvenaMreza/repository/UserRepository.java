package com.example.PraksaDrustvenaMreza.repository;

import com.example.PraksaDrustvenaMreza.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneById(Long id);

    User findOneByUserName(String username);

    @Query(value = "SELECT u FROM User u " +
            "WHERE u.firstName LIKE %:search% " +
            "OR u.lastName LIKE %:search% " +
            "OR u.userName LIKE %:search%")
    List<User> search(String search);
}
