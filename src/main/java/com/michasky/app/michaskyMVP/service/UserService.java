package com.michasky.app.michaskyMVP.service;

import com.michasky.app.michaskyMVP.model.Driver;
import com.michasky.app.michaskyMVP.model.User;
import com.michasky.app.michaskyMVP.repository.DriverRepository;
import com.michasky.app.michaskyMVP.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final DriverRepository driverRepository;

    public UserService(UserRepository userRepository, DriverRepository driverRepository) {
        this.userRepository = userRepository;
        this.driverRepository = driverRepository;
    }

    // Crear un usuario (pasajero)
    public User createUser(User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setRating(5.0); // Rating inicial
        return userRepository.save(user);
    }

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Buscar usuario por id
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Actualizar datos del usuario
    public User updateUser(Long userId, User updatedUser) {
        User existingUser = getUserById(userId);
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        return userRepository.save(existingUser);
    }

    // Eliminar usuario
    public void deleteUser(Long userId) {
        User user = getUserById(userId);
        userRepository.delete(user);
    }

    // Registrar un driver (herencia de User)
    public Driver createDriver(Driver driver) {
        driver.setCreatedAt(LocalDateTime.now());
        driver.setRating(5.0);
        driver.setActive(true);
        return driverRepository.save(driver);
    }

    // Obtener todos los drivers
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    // Buscar driver por id
    public Driver getDriverById(Long driverId) {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
    }

    // Activar / desactivar driver
    public Driver setDriverActive(Long driverId, boolean active) {
        Driver driver = getDriverById(driverId);
        driver.setActive(active);
        return driverRepository.save(driver);
    }
}

