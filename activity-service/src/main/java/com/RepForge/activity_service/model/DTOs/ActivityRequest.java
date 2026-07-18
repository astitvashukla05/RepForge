package com.RepForge.activity_service.model.DTOs;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import com.RepForge.activity_service.model.ActivityType;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRequest {
  private UUID userId;
  @NotNull(message = "Activity Required")
  private ActivityType type;
  @NotNull(message = "Workout duration required")
  private Integer duration;
  @NotNull(message = "Required calorie burnt of a session")
  private Integer calorieBurnt;
  @NotNull(message = "Specify start time")
  private LocalDateTime starTime;
  private Map<String, Object> addtionalInfo;

}
