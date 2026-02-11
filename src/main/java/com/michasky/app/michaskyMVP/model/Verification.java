package com.michasky.app.michaskyMVP.model;


import com.michasky.app.michaskyMVP.model.enums.VerificationStatus;
import com.michasky.app.michaskyMVP.model.enums.VerificationType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "verifications")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter

public class Verification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veri_id")
    private Long id;

    // Puede ser verificación de un Driver
    @ManyToOne(optional = true)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    // O de un Vehicle
    @ManyToOne(optional = true)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @Enumerated(EnumType.STRING)
    private VerificationType type;

    @Enumerated(EnumType.STRING)
    private VerificationStatus status;

    private LocalDateTime verifiedAt;


}
