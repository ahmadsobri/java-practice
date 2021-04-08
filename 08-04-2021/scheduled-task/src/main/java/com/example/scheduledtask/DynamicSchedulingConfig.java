package com.example.scheduledtask;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

// changing the fixedDelay or fixedRate values at runtime isn't possible when we use @Scheduled annotation in Spring.
// Using Spring's SchedulingConfigurer than customizable
@Configuration
@EnableScheduling
public class DynamicSchedulingConfig implements SchedulingConfigurer {

    int tick = 0;

    @Bean
    public Executor taskExecutor() {
        return Executors.newSingleThreadScheduledExecutor();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
        taskRegistrar.addTriggerTask(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("tick = "+tick);
                        tick++; // run task
                    }
                },
                new Trigger() {
                    @Override
                    public Date nextExecutionTime(TriggerContext context) {
                        Optional<Date> lastCompletionTime = Optional.ofNullable(context.lastCompletionTime());
                        Instant nextExecutionTime = lastCompletionTime.orElseGet(Date::new).toInstant().plusMillis(1000*(tick/2)); //change dynamic time execution of task
                        return Date.from(nextExecutionTime);
                    }
                }
        );
    }
}
