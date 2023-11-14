package com.example.shoppingcartreservationsystem.repository;

import com.example.shoppingcartreservationsystem.models.Product;
import com.example.shoppingcartreservationsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM Users u WHERE u.user_name = :userName", nativeQuery = true)
    User findByUserName(String userName);

    @Query(value = "SELECT * FROM Users u WHERE u.email = :email", nativeQuery = true)
    User findByEmail(String email);

    List<User> findByUserId(Long userId);
}
