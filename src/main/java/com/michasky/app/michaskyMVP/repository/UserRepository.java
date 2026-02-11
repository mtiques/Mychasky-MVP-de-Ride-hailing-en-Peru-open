package com.michasky.app.michaskyMVP.repository;

import com.michasky.app.michaskyMVP.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Método adicional opcional para buscar por email o teléfono
    User findByEmail(String email);
    User findByPhoneNumber(String phoneNumber);
}
