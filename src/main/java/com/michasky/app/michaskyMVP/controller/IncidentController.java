package com.michasky.app.michaskyMVP.controller;

import com.michasky.app.michaskyMVP.model.Incident;
import com.michasky.app.michaskyMVP.service.IncidentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    private final IncidentService incidentService;

    public IncidentController(IncidentService incidentService) {
        this.incidentService = incidentService;
    }

    // Reportar incidente
    @PostMapping("/trip/{tripId}")
    public ResponseEntity<Incident> reportIncident(
            @PathVariable Long tripId,
            @RequestBody Incident incident) {

        return ResponseEntity.ok(
                incidentService.reportIncident(tripId, incident)
        );
    }

    // Incidentes de un trip
    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<Incident>> getByTrip(@PathVariable Long tripId) {
        return ResponseEntity.ok(incidentService.getIncidentsByTrip(tripId));
    }

    // Incidentes activos
    @GetMapping("/active")
    public ResponseEntity<List<Incident>> getActiveIncidents() {
        return ResponseEntity.ok(incidentService.getActiveIncidents());
    }
}

