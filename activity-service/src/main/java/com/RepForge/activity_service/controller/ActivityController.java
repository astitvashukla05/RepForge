package com.RepForge.activity_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest ActivityReuqest) {
        return ResponseEntity.ok(activityService.trackActivity(ActivityReuqest));

    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getActivity(@RequestHeader("X-User-ID") String userId) {
        return ResponseEntity.ok(activityService.getActivity(userId));
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivityById(@PathVariable String activityId) {
        return ResponseEntity.ok(activityService.getActivityById(activityId));
    }

}
