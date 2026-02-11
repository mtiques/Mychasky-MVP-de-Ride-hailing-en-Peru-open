package com.michasky.app.michaskyMVP.model;


import com.michasky.app.michaskyMVP.model.enums.PaymentMethod;
import com.michasky.app.michaskyMVP.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    // Relación con Trip (agregación)
    @OneToOne(optional = false)
    @JoinColumn(name = "trip_id", unique = true)
    private Trip trip;


    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentMethod method; // CASH, CARD

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;

    private LocalDateTime paidAt;


}
