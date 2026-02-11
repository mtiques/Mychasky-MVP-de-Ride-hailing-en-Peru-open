package com.michasky.app.michaskyMVP.model;

import com.michasky.app.michaskyMVP.model.enums.TripStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trips")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Setter

public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trip_id;


    // Pasajero
    @ManyToOne(optional = false)
    @JoinColumn(name = "passenger_id")
    private User passenger;

    // Conductor
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    // Vehículo
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    // Origen y destino
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "origin_lat")),
            @AttributeOverride(name = "longitude", column = @Column(name = "origin_lng"))
    })
    private Location origin;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "latitude", column = @Column(name = "destination_lat")),
            @AttributeOverride(name = "longitude", column = @Column(name = "destination_lng"))
    })
    private Location destination;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TripStatus status = TripStatus.REQUESTED;

    private LocalDateTime requestedAt;
    private LocalDateTime startedAt;
    private LocalDateTime completedAt;

    // Pago
    @OneToOne(mappedBy = "trip", cascade = CascadeType.ALL)
    private Payment payment;

    // Incidentes
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL)
    private List<Incident> incidents = new ArrayList<>();

    @PrePersist
    protected void prePersist() {
        this.requestedAt = LocalDateTime.now();
    }

}
