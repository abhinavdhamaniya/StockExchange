package com.abhinav.dhamaniya.StockExchange.repository;

import com.abhinav.dhamaniya.StockExchange.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM APP_USER WHERE ID = :userId AND CONFIRMED = true", nativeQuery = true)
    Optional<User> findByIdAndConfirmed(@Param("userId") int userId);

    @Query(value = "SELECT * FROM APP_USER WHERE USERNAME = :username AND PASSWORD = :password AND CONFIRMED = true", nativeQuery = true)
    Optional<User> validateUsernamePassword(@Param("username") String username, @Param("password") String password);
}