package com.RepForge.activity_service.model.DTOs;

import java.time.LocalDateTime;
import java.util.Map;

import com.RepForge.activity_service.model.ActivityType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityResponse {

    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurnt;
    private LocalDateTime startTime;

    private Map<String, Object> additionalInfo;

}
