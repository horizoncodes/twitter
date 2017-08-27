package com.twitterchallenge;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StatusExecutor
{
    public static void main(String[] args)
    {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        StatusFetchTask statusFetchTask = new StatusFetchTask ();

        System.out.println("The time is : " + new Date());

        executor.scheduleAtFixedRate(statusFetchTask, 2, 30, TimeUnit.SECONDS);

        try {
            executor.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
