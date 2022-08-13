package com.example.springaop.dynamic_proxy.jdk_dynamic_proxy;

import com.example.springaop.dynamic_proxy.jdk_dynamic_proxy.code.AImpl;
import com.example.springaop.dynamic_proxy.jdk_dynamic_proxy.code.AInterface;
import com.example.springaop.dynamic_proxy.jdk_dynamic_proxy.code.BImpl;
import com.example.springaop.dynamic_proxy.jdk_dynamic_proxy.code.BInterface;
import com.example.springaop.dynamic_proxy.jdk_dynamic_proxy.code.TimeInvocationHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicTest1() {
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        AInterface proxy = (AInterface) Proxy.newProxyInstance(
                AInterface.class.getClassLoader(),
                new Class[]{AInterface.class},
                handler
        );

        proxy.call();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }

    @Test
    void dynamicTest2() {
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);
        BInterface proxy = (BInterface) Proxy.newProxyInstance(
                BInterface.class.getClassLoader(),
                new Class[]{BInterface.class},
                handler
        );

        proxy.call();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());
    }

}
