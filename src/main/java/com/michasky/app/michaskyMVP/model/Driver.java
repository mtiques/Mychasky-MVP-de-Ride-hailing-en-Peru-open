package com.michasky.app.michaskyMVP.model;

import com.michasky.app.michaskyMVP.model.enums.VerificationStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Table(name = "drivers")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class  Driver extends User {

    private String licenseNumber;


    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus;

    private boolean active;




}
