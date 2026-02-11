package com.michasky.app.michaskyMVP.repository;

import com.michasky.app.michaskyMVP.model.Incident;
import com.michasky.app.michaskyMVP.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findByActiveTrue();
    List<Incident> findByTrip(Trip trip);
}
