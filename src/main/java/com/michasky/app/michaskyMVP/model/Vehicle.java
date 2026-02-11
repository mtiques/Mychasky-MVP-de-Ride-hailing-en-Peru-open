package com.michasky.app.michaskyMVP.model;


import com.michasky.app.michaskyMVP.model.enums.VerificationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column(nullable = false, unique = true)
    private String plate;

    private String model;
    private String color;

    @Column(name = "vehicle_year")
    private Integer year;

    private boolean acceptsPets;

    private boolean hasRoofRack;

    private boolean verified;

    @Enumerated(EnumType.STRING)
    @Column(name = "verification_status")
    private VerificationStatus verificationStatus;



}
