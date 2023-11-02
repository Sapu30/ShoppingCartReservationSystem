package com.example.shoppingcartreservationsystem.repository;

import com.example.shoppingcartreservationsystem.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<Reservation, Long> {
    // Custom query methods can be added here if needed
}