package com.example.shoppingcartreservationsystem.repository;

import com.example.shoppingcartreservationsystem.models.Product;
import com.example.shoppingcartreservationsystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUserName(String userName);
    public User findByEmail(String email);

    List<User> findByUserId(Long userId);
}
