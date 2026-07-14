package com.RepForge.activity_service.Service;

import org.springframework.stereotype.Service;

import com.RepForge.activity_service.Repository.ActivityRepo;
import com.RepForge.activity_service.model.Activity;
import com.RepForge.activity_service.model.DTOs.ActivityRequest;
import com.RepForge.activity_service.model.DTOs.ActivityResponse;

import jakarta.validation.Valid;

@Service
public class ActivityService {

    private final ActivityRepo activityRepo;

    public ActivityService(ActivityRepo activityRepo) {
        this.activityRepo = activityRepo;
    }

    public ActivityResponse trackActivity(@Valid ActivityRequest request) {

        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurnt(request.getCalorieBurnt())
                .startTime(request.getStarTime())
                .additionalInfo(request.getAddtionalInfo())
                .build();

        Activity savedActivity = activityRepo.save(activity);

        ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setAdditionalInfo(savedActivity.getAdditionalInfo());
        activityResponse.setCaloriesBurnt(savedActivity.getCaloriesBurnt());
        activityResponse.setDuration(savedActivity.getDuration());
        activityResponse.setStartTime(savedActivity.getStartTime());
        activityResponse.setType(savedActivity.getType());
        return activityResponse;
    }
}
