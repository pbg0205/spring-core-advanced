package com.example.springaop.dynamic_proxy.proxyfactory.code;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Slf4j
public class TImeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        Object result = invocation.proceed();

        long endTime = System.currentTimeMillis();
        long elapseTIme = endTime - startTime;

        log.info("TimeProxy 종료");
        log.info("elapsedTime = {}ms", elapseTIme);

        return result;
    }

}

