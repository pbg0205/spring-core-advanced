package com.example.logtracer.trace.abstract_template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractTemplate {

    // 1. 부가 기능은 추상 클래스로 묶고 핵심 로직은 구현체로 분리시키는 패턴
    // 2. 변하지 않는 부분은 추상 클래스로 묶고, 변하는 부분은 구현체로 분리시키는 패턴
    public void execute() {
        long startTime = System.currentTimeMillis();
        call();
        long endTime = System.currentTimeMillis();

        long elapsedTime = endTime - startTime;
        log.info("elapsedTime: {}", elapsedTime);
    }

    protected abstract void call();
}
