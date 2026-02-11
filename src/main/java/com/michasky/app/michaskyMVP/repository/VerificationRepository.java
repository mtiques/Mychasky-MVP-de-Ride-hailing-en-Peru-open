package com.michasky.app.michaskyMVP.repository;

import com.michasky.app.michaskyMVP.model.Driver;
import com.michasky.app.michaskyMVP.model.Vehicle;
import com.michasky.app.michaskyMVP.model.Verification;
import com.michasky.app.michaskyMVP.model.enums.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long> {
    List<Verification> findByDriver(Driver driver);
    List<Verification> findByVehicle(Vehicle vehicle);
    List<Verification> findByStatus(VerificationStatus status);
}
