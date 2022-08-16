package com.example.logtracer.trace.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2 {

    public void execute(Strategy strategy) {
        long startTime = System.currentTimeMillis();
        strategy.call();
        long endTIme = System.currentTimeMillis();

        long elapsedTime = endTIme - startTime;
        log.info("elapsedTime : {}", elapsedTime);
    }

}
