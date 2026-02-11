package com.michasky.app.michaskyMVP.model;

import com.michasky.app.michaskyMVP.model.enums.ReportedBy;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidents")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter

public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "trip_id")
    private Trip trip;


    @Enumerated(EnumType.STRING)
    private ReportedBy reportedBy;

    @Column(length = 500)
    private String description;

    private LocalDateTime createdAt;

    private boolean archived = false;

    @Column(nullable = false)
    private boolean active = true;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }





}
