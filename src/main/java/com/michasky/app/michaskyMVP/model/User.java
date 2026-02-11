package com.michasky.app.michaskyMVP.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@Getter @Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String name;

    private String email;

    private Double rating = 5.0;

    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }





}
