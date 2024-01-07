package com.devgrp.fitnesswebapp.entity;

import com.devgrp.fitnesswebapp.entity.compositekeys.UserLoggingLogKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoggingLog {
    @EmbeddedId
    UserLoggingLogKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn
    private User user;

    private LocalDateTime loggedOutDateTime;
}
