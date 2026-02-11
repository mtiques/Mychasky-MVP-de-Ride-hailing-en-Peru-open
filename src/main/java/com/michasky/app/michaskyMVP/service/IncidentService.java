package com.michasky.app.michaskyMVP.service;

import com.michasky.app.michaskyMVP.model.Incident;
import com.michasky.app.michaskyMVP.model.Trip;
import com.michasky.app.michaskyMVP.repository.IncidentRepository;
import com.michasky.app.michaskyMVP.repository.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final TripRepository tripRepository;

    public IncidentService(IncidentRepository incidentRepository, TripRepository tripRepository) {
        this.incidentRepository = incidentRepository;
        this.tripRepository = tripRepository;
    }

    public Incident reportIncident(Long tripId, Incident incident) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        incident.setTrip(trip);
        incident.setCreatedAt(LocalDateTime.now());

        return incidentRepository.save(incident);
    }

    public List<Incident> getIncidentsByTrip(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        return incidentRepository.findByTrip(trip);
    }

    // Incidentes activos
    public List<Incident> getActiveIncidents() {
        return incidentRepository.findByActiveTrue();
    }


}
