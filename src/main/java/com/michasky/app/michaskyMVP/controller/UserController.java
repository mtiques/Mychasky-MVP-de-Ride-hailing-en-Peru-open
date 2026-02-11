package com.michasky.app.michaskyMVP.controller;

import com.michasky.app.michaskyMVP.model.Driver;
import com.michasky.app.michaskyMVP.model.User;
import com.michasky.app.michaskyMVP.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Crear pasajero
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    // Obtener todos los pasajeros
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Crear driver
    @PostMapping("/drivers")
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        return ResponseEntity.ok(userService.createDriver(driver));
    }

    // Activar / desactivar driver
    @PatchMapping("/drivers/{driverId}/active")
    public ResponseEntity<Driver> setDriverActive(@PathVariable Long driverId,
                                                  @RequestParam boolean active) {
        return ResponseEntity.ok(userService.setDriverActive(driverId, active));
    }
}
