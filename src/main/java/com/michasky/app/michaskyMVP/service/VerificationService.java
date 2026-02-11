package com.michasky.app.michaskyMVP.service;

import com.michasky.app.michaskyMVP.model.Driver;
import com.michasky.app.michaskyMVP.model.Vehicle;
import com.michasky.app.michaskyMVP.model.Verification;
import com.michasky.app.michaskyMVP.model.enums.VerificationStatus;
import com.michasky.app.michaskyMVP.model.enums.VerificationType;
import com.michasky.app.michaskyMVP.repository.DriverRepository;
import com.michasky.app.michaskyMVP.repository.VehicleRepository;
import com.michasky.app.michaskyMVP.repository.VerificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class VerificationService {

    private final VerificationRepository verificationRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    public VerificationService(VerificationRepository verificationRepository,
                               DriverRepository driverRepository,
                               VehicleRepository vehicleRepository) {
        this.verificationRepository = verificationRepository;
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
    }

    // Crear verificación
    public Verification createVerification(Verification verification) {
        verification.setVerifiedAt(LocalDateTime.now());
        verification.setStatus(VerificationStatus.PENDING);
        return verificationRepository.save(verification);
    }

    // Verificaciones por driver
    public List<Verification> getVerificationsByDriver(Long driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        return verificationRepository.findByDriver(driver);
    }

    // Verificaciones por vehículo
    public List<Verification> getVerificationsByVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        return verificationRepository.findByVehicle(vehicle);
    }

    // Actualizar estado de verificación
    public Verification updateStatus(Long verificationId, VerificationStatus status) {
        Verification verification = verificationRepository.findById(verificationId)
                .orElseThrow(() -> new RuntimeException("Verification not found"));

        verification.setStatus(status);
        verification.setVerifiedAt(LocalDateTime.now());

        // Efecto colateral profesional
        if (verification.getType() == VerificationType.VEHICLE && status == VerificationStatus.APPROVED) {
            Vehicle vehicle = verification.getVehicle();
            vehicle.setVerified(true);
        }

        if (verification.getType() == VerificationType.DRIVER && status == VerificationStatus.APPROVED) {
            Driver driver = verification.getDriver();
            driver.setVerificationStatus(VerificationStatus.APPROVED);
        }

        return verificationRepository.save(verification);
    }
}
