package com.example.shoppingcartreservationsystem.service;

import com.example.shoppingcartreservationsystem.models.Reservation;
import com.example.shoppingcartreservationsystem.repository.ReservationRepository;
import com.example.shoppingcartreservationsystem.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public Reservation createReservation(Reservation Reservation) {
        return reservationRepository.save(Reservation);
    }

    public Reservation updateReservation(Long id, Reservation Reservation) {
        if (reservationRepository.existsById(id)) {
            Reservation.setId(id);
            return reservationRepository.save(Reservation);
        }
        return null;
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
