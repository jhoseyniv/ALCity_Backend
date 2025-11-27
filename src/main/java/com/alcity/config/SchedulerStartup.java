package com.alcity.config;

import com.alcity.service.appmember.DynamicSchedulerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SchedulerStartup {

    @Autowired
    private DynamicSchedulerService schedulerService;

    @PostConstruct
    public void init() {
        schedulerService.startScheduler();
    }
}
