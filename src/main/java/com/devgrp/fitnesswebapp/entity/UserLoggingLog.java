package com.devgrp.fitnesswebapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
class UserLoggingLogKey implements Serializable {
    int userId;
    LocalDateTime loggedInDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoggingLogKey that = (UserLoggingLogKey) o;
        return Objects.equals(userId, that.userId) && Objects.equals(loggedInDateTime, that.loggedInDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, loggedInDateTime);
    }
}

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
