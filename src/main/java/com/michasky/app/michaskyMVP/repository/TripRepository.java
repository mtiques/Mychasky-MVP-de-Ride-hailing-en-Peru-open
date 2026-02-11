package com.michasky.app.michaskyMVP.repository;

import com.michasky.app.michaskyMVP.model.Driver;
import com.michasky.app.michaskyMVP.model.Trip;
import com.michasky.app.michaskyMVP.model.User;
import com.michasky.app.michaskyMVP.model.Vehicle;
import com.michasky.app.michaskyMVP.model.enums.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByPassenger(User passenger);
    List<Trip> findByDriver(Driver driver);
    List<Trip> findByVehicle(Vehicle vehicle);
    List<Trip> findByStatus(TripStatus status);

}
