package com.RepForge.ai_service.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "recommendation")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Recomendation {
    @Id
    private String id;
    private String activityId;
    private UUID userId;
    private String activityType;
    private String recommendation;
    private List<String> improvements;
    private List<String> suggestions;
    private List<String> safety;
    @CreatedDate
    private LocalDateTime createdAt;

}
