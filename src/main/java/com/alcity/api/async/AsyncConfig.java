package com.alcity.api.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsyncConfig {

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Number of threads to keep in the pool
        executor.setMaxPoolSize(10); // Maximum number of threads in the pool
        executor.setQueueCapacity(25); // Capacity of the queue for holding tasks
        executor.setThreadNamePrefix("PostgresReader-"); // Prefix for thread names
        executor.initialize();
        return executor;
    }
}
