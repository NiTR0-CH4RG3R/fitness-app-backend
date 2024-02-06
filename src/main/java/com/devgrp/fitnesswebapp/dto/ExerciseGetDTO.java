package com.devgrp.fitnesswebapp.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.stereotype.Component;

//@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseGetDTO {
    @Setter
    private Integer id;

    public void setDescription(String description) {
        if ( description.length() > 100 )
            this.description = description.substring(0,100);
        else
            this.description = description;
    }

    @Setter
    private String name;

    private String description;

    @Setter
    private String gifURL;
}
