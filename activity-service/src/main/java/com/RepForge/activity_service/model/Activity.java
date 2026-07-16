package com.RepForge.activity_service.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

@Document(collection = "activity")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    private String id;
    private String userId;
    @NotBlank(message = "Activity Required")
    private ActivityType type;
    @NotBlank(message = "Workout duration required")
    private Integer duration;
    @NotBlank(message = "Required calorie burnt of a session")
    private Integer caloriesBurnt;
    @NotBlank(message = "Specify start time")
    private LocalDateTime startTime;
    // private LocalDateTime endTime;
    private Map<String, Object> additionalInfo;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
