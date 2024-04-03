package com.virtualThreads.VirtualThreadsDemo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class VirtualThreadController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VirtualThreadController.class);
    public static final int SLEEP_TIME = 300;

    @GetMapping("/test")
    public String getResponse(){

         final Executor threadExecutor = Executors.newSingleThreadExecutor();
        try {
            TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }

        long threadId = Thread.currentThread().threadId() ;
        return  String.valueOf(threadId);
    }

    @GetMapping("/testFast")
    public String getResponseFast(){

        final Executor virtualThreadExecutor = Executors.newVirtualThreadPerTaskExecutor();
        try {
            TimeUnit.MILLISECONDS.sleep(3);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }

        long threadId = Thread.currentThread().threadId() ;
        return  String.valueOf(threadId);
    }
}
