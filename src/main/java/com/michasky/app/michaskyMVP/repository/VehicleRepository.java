package com.michasky.app.michaskyMVP.repository;

import com.michasky.app.michaskyMVP.model.Driver;
import com.michasky.app.michaskyMVP.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Vehicle findByPlate(String plate);
    List<Vehicle> findByDriver(Driver driver);
}
