package com.michasky.app.michaskyMVP.service;

import com.michasky.app.michaskyMVP.model.*;
import com.michasky.app.michaskyMVP.model.enums.PaymentMethod;
import com.michasky.app.michaskyMVP.model.enums.PaymentStatus;
import com.michasky.app.michaskyMVP.model.enums.TripStatus;
import com.michasky.app.michaskyMVP.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TripService {

    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final PaymentRepository paymentRepository;

    public TripService(TripRepository tripRepository,
                       UserRepository userRepository,
                       DriverRepository driverRepository,
                       VehicleRepository vehicleRepository,
                       PaymentRepository paymentRepository) {
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.paymentRepository = paymentRepository;
    }

    // Solicitar un viaje
    public Trip requestTrip(Long passengerId, Location origin, Location destination) {
        User passenger = userRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        BigDecimal price = calculatePrice(origin, destination);

        Trip trip = Trip.builder()
                .passenger(passenger)         // User cargado desde repo
                .origin(origin)
                .destination(destination)
                .status(TripStatus.REQUESTED)
                .requestedAt(LocalDateTime.now())
                .price(price) // precio calculado
                .build();


        return tripRepository.save(trip);
    }

    // Cancelar viaje
    public void cancelTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        if (trip.getStatus() == TripStatus.COMPLETED) {
            throw new RuntimeException("Cannot cancel a completed trip");
        }

        trip.setStatus(TripStatus.CANCELLED);
        tripRepository.save(trip);
    }

    // Completar viaje
    public void completeTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        trip.setStatus(TripStatus.COMPLETED);
        trip.setCompletedAt(LocalDateTime.now());

        // Crear pago asociado automáticamente
        Payment payment = Payment.builder()
                .trip(trip)
                .amount(trip.getPrice())
                .status(PaymentStatus.PENDING)
                .method(PaymentMethod.CASH)
                .build();

        // Asociar Payment con Trip
        trip.setPayment(payment);

        // Guardar ambos
        paymentRepository.save(payment);
        tripRepository.save(trip);
    }

    // Calificar conductor
    public void rateDriver(Long tripId, double rating) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        Driver driver = trip.getDriver();
        if (driver == null) throw new RuntimeException("Driver not assigned");

        // Para MVP, simplemente asignamos el rating
        driver.setRating(rating);
        driverRepository.save(driver);
    }

    // Asignar conductor y vehículo a un viaje
    public void assignDriver(Long tripId, Long driverId, Long vehicleId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        trip.setDriver(driver);
        trip.setVehicle(vehicle);
        trip.setStatus(TripStatus.ACCEPTED);
        trip.setStartedAt(LocalDateTime.now());

        tripRepository.save(trip);
    }

    // Calcular precio básico (puedes extenderlo con distancia, extras, etc.)
    private BigDecimal calculatePrice(Location origin, Location destination) {
        // MVP: precio fijo o por distancia aproximada
        return BigDecimal.valueOf(10.0); // ejemplo simple
    }

    // Listar viajes de un usuario
    public List<Trip> getTripsByPassenger(Long passengerId) {
        User passenger = userRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));
        return tripRepository.findByPassenger(passenger);
    }

    // Listar viajes de un driver
    public List<Trip> getTripsByDriver(Long driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        return tripRepository.findByDriver(driver);
    }

    public Trip getTripById(Long tripId) {
        return tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
    }

}

