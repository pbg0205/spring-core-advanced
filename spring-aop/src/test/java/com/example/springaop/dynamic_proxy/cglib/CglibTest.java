package com.example.springaop.dynamic_proxy.cglib;

import com.example.springaop.dynamic_proxy.cglib.code.ConcreteService;
import com.example.springaop.dynamic_proxy.cglib.code.TimeMethodInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

@Slf4j
public class CglibTest {

    @Test
    void cglib() {
        ConcreteService target = new ConcreteService();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(ConcreteService.class); //프록시 생성 시 상속할 구체 클래스 지정
        enhancer.setCallback(new TimeMethodInterceptor(target)); //프록시에 적용할 실행로직 할당

        ConcreteService proxy = (ConcreteService)enhancer.create(); //프록시 생성

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();
    }

}
