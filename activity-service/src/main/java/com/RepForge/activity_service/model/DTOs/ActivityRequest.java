package com.RepForge.activity_service.model.DTOs;

import java.time.LocalDateTime;
import java.util.Map;

import com.RepForge.activity_service.model.ActivityType;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest {
    private String userId;
     @NotBlank(message = "Activity Required")
    private ActivityType type;
      @NotBlank(message = "Workout duration required")
    private Integer duration;
      @NotBlank(message = "Required calorie burnt of a session")
    private Integer calorieBurnt;
       @NotBlank(message = "Specify start time")
    private LocalDateTime starTime;
    private Map<String, Object> addtionalInfo;

}
