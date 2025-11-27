package com.alcity.service.appmember;


import com.alcity.entity.appmember.EnergyConfig;
import com.alcity.repository.appmember.AppMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

@Service
public class DynamicSchedulerService {

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    AppMemberService memberService;

    @Autowired
    EnergyConfigService energyConfigService;

    private ScheduledFuture<?> scheduledFuture;

    private long dynamicRate = 60000000L; // 60 ثانیه

    public void startScheduler() {
        Optional<EnergyConfig> config = energyConfigService.findByExpireIsFalse();
        stopScheduler(); // جلوگیری از تسک تکراری
        scheduledFuture = taskScheduler.scheduleAtFixedRate(() -> {
            memberService.checkAndUpdateExpiredUsers(); // 👈 متد خودکار اجرا می‌شود
        }, dynamicRate);

        System.out.println("Scheduler started with rate: " + dynamicRate);
    }

    public void stopScheduler() {
        if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
            scheduledFuture.cancel(true);
            System.out.println("Scheduler stopped.");
        }
    }
    public void updateRate(long newRate) {
        this.dynamicRate = newRate;
        System.out.println("Updating scheduler rate to: " + newRate);
        startScheduler(); // اعمال مقدار جدید
    }

    public long getCurrentRate() {
        return dynamicRate;
    }

}
