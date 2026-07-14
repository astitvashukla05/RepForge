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
public class ActivityRequest {
    private String userId;
    private ActivityType type;
    private Integer duration;
    private Integer calorieBurnt;
    private LocalDateTime starTime;
    private Map<String, Object> addtionalInfo;

}
