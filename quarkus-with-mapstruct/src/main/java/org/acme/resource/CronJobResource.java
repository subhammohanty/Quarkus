package org.acme.resource;

import io.quarkus.scheduler.Scheduled;

public class CronJobResource {

    //This is to schedule job for every 10 sec
    @Scheduled(every = "10s")
    public void testSchedulerForEvery10sec(){
        System.out.println("Testing Cron For Every 10 Secs");
    }

    //This is to schedule a job to run in a specific time at 5.48 Every Day Every Month
    @Scheduled(cron = "0 50 17 * * ?")
    public void testForRunningInCertainTime(){
        System.out.println("Testing To Run In A Specific Time");
    }
}
