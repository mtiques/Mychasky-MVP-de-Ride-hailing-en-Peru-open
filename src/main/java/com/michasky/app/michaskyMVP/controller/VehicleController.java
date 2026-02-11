package com.michasky.app.michaskyMVP.controller;

import com.michasky.app.michaskyMVP.model.Vehicle;
import com.michasky.app.michaskyMVP.service.VehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // Registrar vehículo para un conductor
    @PostMapping("/driver/{driverId}")
    public ResponseEntity<Vehicle> createVehicle(
            @PathVariable Long driverId,
            @RequestBody Vehicle vehicle) {

        return ResponseEntity.ok(
                vehicleService.createVehicle(driverId, vehicle)
        );
    }
    // Obtener vehículo
    @GetMapping("/{vehicleId}")
    public ResponseEntity<Vehicle> getVehicle(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(vehicleService.getVehicleById(vehicleId));
    }

    // Verificar vehículo
    @PatchMapping("/{vehicleId}/verify")
    public ResponseEntity<Vehicle> verifyVehicle(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(vehicleService.verifyVehicle(vehicleId));
    }

    // Listar vehículos por driver
    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<Vehicle>> getVehiclesByDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(vehicleService.getVehiclesByDriver(driverId));
    }
}

