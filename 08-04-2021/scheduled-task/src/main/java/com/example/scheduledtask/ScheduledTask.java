package com.example.scheduledtask;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    final int delayTimeMillis = 5000;
    final int fixedTimeMillis = 5000;

    // fixedDelay property makes sure that there is a delay of n millisecond between the finish time of an execution of a task and the start time of the next execution of the task
    @Scheduled(fixedDelay = delayTimeMillis)
    public void scheduleFixedDelayTask() {
        System.out.println("Fixed delay task - " + System.currentTimeMillis() / delayTimeMillis);
    }

    //fixedRate property runs the scheduled task at every n millisecond. It doesn't check for any previous executions of the task.
    @Scheduled(fixedRate = fixedTimeMillis)
    public void scheduleFixedRateTask() {
        System.out.println("Fixed rate task - " + System.currentTimeMillis() / fixedTimeMillis);
    }

    //task will be executed the first time after the initialDelay value, and it will continue to be executed according to the fixedDelay
    @Scheduled(fixedDelay = 1000, initialDelay = 10000)
    public void scheduleFixedRateWithInitialDelayTask() {

        long now = System.currentTimeMillis() / 10000;
        System.out.println(
                "Fixed rate task with one second initial delay - " + now);
    }

    // task to be executed at 3:48 PM on the 8th day of every month in Jakarta Time Zone
    // cron format "second, minute, hour, day of month, month, day(s) of week"

    // 0 0 * * * * = the top of every hour of every day.
    // */10 * * * * * = every ten seconds.
    // 0 0 8-10 * * * = 8, 9 and 10 o'clock of every day.
    // 0 0 6,19 * * * = 6:00 AM and 7:00 PM every day.
    // 0 0/30 8-10 * * * = 8:00, 8:30, 9:00, 9:30, 10:00 and 10:30 every day.
    // 0 0 9-17 * * MON-FRI = on the hour nine-to-five weekdays
    // 0 0 0 25 12 ? = every Christmas Day at midnight

    // (*) means match any
    // */X means "every X"
    // ? ("no specific value")

    @Scheduled(cron = "0 48 15 8 * ?", zone = "Asia/Jakarta")
    public void scheduleTaskUsingCronExpression() {

        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "schedule tasks using cron jobs - " + now);
    }

}
