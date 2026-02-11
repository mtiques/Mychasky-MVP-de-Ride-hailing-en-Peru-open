package com.michasky.app.michaskyMVP.controller;


import com.michasky.app.michaskyMVP.model.Verification;
import com.michasky.app.michaskyMVP.model.enums.VerificationStatus;
import com.michasky.app.michaskyMVP.service.VerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/verifications")
public class VerificationController {

    private final VerificationService verificationService;

    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    // Crear verificación
    @PostMapping
    public ResponseEntity<Verification> createVerification(
            @RequestBody Verification verification) {
        return ResponseEntity.ok(verificationService.createVerification(verification));
    }

    // Verificaciones de un driver
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Verification>> getByDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(verificationService.getVerificationsByDriver(driverId));
    }

    // Verificaciones de un vehículo
    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<Verification>> getByVehicle(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(verificationService.getVerificationsByVehicle(vehicleId));
    }

    // Aprobar / rechazar verificación
    @PatchMapping("/{verificationId}/status")
    public ResponseEntity<Verification> updateStatus(
            @PathVariable Long verificationId,
            @RequestParam VerificationStatus status) {
        return ResponseEntity.ok(
                verificationService.updateStatus(verificationId, status)
        );
    }
}

