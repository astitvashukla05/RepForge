package com.RepForge.ai_service.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.RepForge.ai_service.model.Activity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActivityMessageListener {
    // @Value("${rabbitmq.exchange.name}")
    // private String exchange;
    // @Value("${rabbitmq.routing.key}")
    // private String routingKey;
    // @Value("${rabbitmq.queue.name}")
    // private String queue;
    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void processActivity(Activity activity) {
        log.info("Received: {}", activity);
    }
}
