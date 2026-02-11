package com.michasky.app.michaskyMVP.controller;

import com.michasky.app.michaskyMVP.model.Payment;
import com.michasky.app.michaskyMVP.model.Trip;
import com.michasky.app.michaskyMVP.service.PaymentService;
import com.michasky.app.michaskyMVP.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final TripService tripService;

    public PaymentController(PaymentService paymentService, TripService tripService) {
        this.paymentService = paymentService;
        this.tripService = tripService;
    }

    // Obtener pago de un trip
    @GetMapping("/trip/{tripId}")
    public ResponseEntity<Payment> getPaymentByTrip(@PathVariable Long tripId) {
        Trip trip = tripService.getTripById(tripId);
        return ResponseEntity.ok(paymentService.getPaymentByTrip(trip));
    }


    // Completar pago
    @PostMapping("/trip/{tripId}/complete")
    public ResponseEntity<Payment> completePayment(@PathVariable Long tripId) {
        return ResponseEntity.ok(paymentService.completePayment(tripId));
    }

}
