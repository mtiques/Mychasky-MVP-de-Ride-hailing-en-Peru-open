package com.michasky.app.michaskyMVP.controller;

import com.michasky.app.michaskyMVP.model.Location;
import com.michasky.app.michaskyMVP.model.Trip;
import com.michasky.app.michaskyMVP.service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    // Solicitar viaje
    @PostMapping("/request")
    public ResponseEntity<Trip> requestTrip(@RequestParam Long passengerId,
                                            @RequestBody Location origin,
                                            @RequestBody Location destination) {
        return ResponseEntity.ok(tripService.requestTrip(passengerId, origin, destination));
    }

    // Cancelar viaje
    @PostMapping("/{tripId}/cancel")
    public ResponseEntity<Void> cancelTrip(@PathVariable Long tripId) {
        tripService.cancelTrip(tripId);
        return ResponseEntity.ok().build();
    }

    // Completar viaje
    @PostMapping("/{tripId}/complete")
    public ResponseEntity<Trip> completeTrip(@PathVariable Long tripId) {
        tripService.completeTrip(tripId);
        return ResponseEntity.ok().build();
    }

    // Asignar driver y vehículo
    @PostMapping("/{tripId}/assign")
    public ResponseEntity<Trip> assignDriver(@PathVariable Long tripId,
                                             @RequestParam Long driverId,
                                             @RequestParam Long vehicleId) {
        tripService.assignDriver(tripId, driverId, vehicleId);
        return ResponseEntity.ok().build();
    }

    // Calificar driver
    @PostMapping("/{tripId}/rate")
    public ResponseEntity<Void> rateDriver(@PathVariable Long tripId,
                                           @RequestParam double rating) {
        tripService.rateDriver(tripId, rating);
        return ResponseEntity.ok().build();
    }

    // Listar viajes por pasajero
    @GetMapping("/passenger/{passengerId}")
    public ResponseEntity<List<Trip>> getTripsByPassenger(@PathVariable Long passengerId) {
        return ResponseEntity.ok(tripService.getTripsByPassenger(passengerId));
    }

    // Listar viajes por driver
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Trip>> getTripsByDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(tripService.getTripsByDriver(driverId));
    }
}
