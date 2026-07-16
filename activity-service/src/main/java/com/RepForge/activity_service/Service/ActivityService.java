package com.RepForge.activity_service.Service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.RepForge.activity_service.Repository.ActivityRepo;
import com.RepForge.activity_service.exceptions.ActivityNotExists;
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
        // Request for user then validate if exsists or not
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

    public List<ActivityResponse> getActivity(String userId) {
        // Request for user then validate if exsists or not
        List<Activity> userActivities = activityRepo.findByUserId(userId);
        List<ActivityResponse> activities = new ArrayList<>();
        for (Activity activity : userActivities) {
            ActivityResponse res = new ActivityResponse();
            res.setAdditionalInfo(activity.getAdditionalInfo());
            res.setCaloriesBurnt(activity.getCaloriesBurnt());
            res.setDuration(activity.getDuration());
            res.setStartTime(activity.getStartTime());
            res.setType(activity.getType());
            activities.add(res);
        }
        return activities;
    }

    public ActivityResponse getActivityById(String acitivityId) {
        
      Activity activity=  activityRepo.findById(acitivityId).orElseThrow(()->new ActivityNotExists("activity Not Exists for id :"+acitivityId));

        
        ActivityResponse res = new ActivityResponse();
        res.setAdditionalInfo(activity.getAdditionalInfo());
        res.setCaloriesBurnt(activity.getCaloriesBurnt());
        res.setDuration(activity.getDuration());
        res.setStartTime(activity.getStartTime());
        res.setType(activity.getType());
        return res;
    }
}
