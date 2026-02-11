package com.michasky.app.michaskyMVP.service;

import com.michasky.app.michaskyMVP.model.Driver;
import com.michasky.app.michaskyMVP.model.enums.VerificationStatus;
import com.michasky.app.michaskyMVP.repository.DriverRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class DriverService {
    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    // Crear conductor
    public Driver createDriver(Driver driver) {
        driver.setActive(false);              // por defecto inactivo
        driver.setRating(5.0);                // rating inicial
        driver.setCreatedAt(LocalDateTime.now());
        driver.setVerificationStatus(VerificationStatus.PENDING);
        return driverRepository.save(driver);
    }

    // Activar / desactivar conductor
    public Driver setActive(Long driverId, boolean active) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        driver.setActive(active);
        return driverRepository.save(driver);
    }

    // Obtener conductor por ID
    public Driver getDriverById(Long driverId) {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
    }

    // Obtener conductores activos
    public List<Driver> getActiveDrivers() {
        return driverRepository.findByActiveTrue();
    }
}
