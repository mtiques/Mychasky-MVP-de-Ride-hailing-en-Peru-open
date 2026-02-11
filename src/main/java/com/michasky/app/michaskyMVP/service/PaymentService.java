package com.michasky.app.michaskyMVP.service;

import com.michasky.app.michaskyMVP.model.Payment;
import com.michasky.app.michaskyMVP.model.Trip;
import com.michasky.app.michaskyMVP.model.enums.PaymentStatus;
import com.michasky.app.michaskyMVP.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Guardar o actualizar un pago
    public Payment savePayment(Payment payment) {
        if (payment.getPaidAt() == null && payment.getStatus() == null) {
            payment.setPaidAt(LocalDateTime.now());
        }
        return paymentRepository.save(payment);
    }

    // Listar pagos por Trip
    public Payment getPaymentByTrip(Trip trip) {
        return paymentRepository.findByTrip(trip)
                .orElseThrow(() -> new RuntimeException("Payment not found for trip"));
    }

    // Marcar pago como completado
    public Payment completePayment(Long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        payment.setStatus(PaymentStatus.COMPLETED);
        payment.setPaidAt(LocalDateTime.now());
        return paymentRepository.save(payment);
    }
}

