package com.michasky.app.michaskyMVP.controller;

import com.michasky.app.michaskyMVP.model.Driver;
import com.michasky.app.michaskyMVP.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    // Crear driver
    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        return ResponseEntity.ok(driverService.createDriver(driver));
    }

    // Activar / desactivar driver
    @PatchMapping("/{driverId}/active")
    public ResponseEntity<Driver> setActive(
            @PathVariable Long driverId,
            @RequestParam boolean active) {
        return ResponseEntity.ok(driverService.setActive(driverId, active));
    }

    // Obtener driver por id
    @GetMapping("/{driverId}")
    public ResponseEntity<Driver> getDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(driverService.getDriverById(driverId));
    }

    // Listar drivers activos
    @GetMapping("/active")
    public ResponseEntity<List<Driver>> getActiveDrivers() {
        return ResponseEntity.ok(driverService.getActiveDrivers());
    }
}
