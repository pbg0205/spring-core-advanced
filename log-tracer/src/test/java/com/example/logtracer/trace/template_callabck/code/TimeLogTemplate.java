package com.example.logtracer.trace.template_callabck.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeLogTemplate {

    public void execute(Callback callback) {
        long startTime = System.currentTimeMillis();
        callback.call();
        long endTime = System.currentTimeMillis();

        long elapsedTime = endTime - startTime;
        log.info("elapsedTime : {}", elapsedTime);
    }
}
