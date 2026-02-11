package com.michasky.app.michaskyMVP.service;

import com.michasky.app.michaskyMVP.model.Driver;
import com.michasky.app.michaskyMVP.model.Vehicle;
import com.michasky.app.michaskyMVP.repository.DriverRepository;
import com.michasky.app.michaskyMVP.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;


    public VehicleService(VehicleRepository vehicleRepository,
                          DriverRepository driverRepository) {
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
    }

    // Crear vehículo
    public Vehicle createVehicle(Long driverId, Vehicle vehicle) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        vehicle.setDriver(driver);
        vehicle.setVerified(false);

        return vehicleRepository.save(vehicle);
    }

    public Vehicle getVehicleById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    // Verificar vehículo (admin)
    public Vehicle verifyVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setVerified(true);
        return vehicleRepository.save(vehicle);
    }

    // Vehículos de un conductor
    public List<Vehicle> getVehiclesByDriver(Long driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        return vehicleRepository.findByDriver(driver);
    }

    // Obtener vehículo por ID
    public Vehicle getVehicle(Long vehicleId) {
        return vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }



}

