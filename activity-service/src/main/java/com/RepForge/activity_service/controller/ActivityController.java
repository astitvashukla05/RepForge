package com.RepForge.activity_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RepForge.activity_service.Service.ActivityService;
import com.RepForge.activity_service.model.DTOs.ActivityRequest;
import com.RepForge.activity_service.model.DTOs.ActivityResponse;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("")
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest ActivityReuqest) {
        return ResponseEntity.ok(activityService.trackActivity(ActivityReuqest));

    }
}
