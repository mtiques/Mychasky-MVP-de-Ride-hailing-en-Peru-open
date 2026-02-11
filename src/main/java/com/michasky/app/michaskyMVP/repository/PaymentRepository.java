package com.michasky.app.michaskyMVP.repository;

import com.michasky.app.michaskyMVP.model.Payment;
import com.michasky.app.michaskyMVP.model.Trip;
import com.michasky.app.michaskyMVP.model.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByTrip(Trip trip);
    List<Payment> findByStatus(PaymentStatus status);
}

