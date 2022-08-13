package com.example.springaop.dynamic_proxy;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        log.info("start");
        String resultA = target.callA();
        log.info("resultA = {}", resultA);

        log.info("start");
        String resultB = target.callB();
        log.info("resultB = {}", resultB);
    }

    /**
     * - 메서드 정보를 파라미터로 넘겨 특정 로직에 관한 정보를 동적으로 넘길 수 있다.
     */
    @DisplayName("리플렉션을 이용해 메서드를 호출한다")
    @Test
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Hello target = new Hello();

        // 클래스의 메타데이터를획득한다. 내부 클래스의 구분을 위해 $를 표시함.
        Class<?> helloClass = Class.forName("com.example.springaop.dynamic_proxy.ReflectionTest$Hello");

        Method methodCallA = helloClass.getMethod("callA");
        Object resultA = methodCallA.invoke(target);
        log.info("resultA = {}", resultA);

        Method methodCallB = helloClass.getMethod("callB");
        Object resultB = methodCallB.invoke(target);
        log.info("resultB = {}", resultB);
    }

    @DisplayName("리플렉션을 통해 변하지 않는 로직을 추상화한다")
    @Test
    void reflection2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Hello target = new Hello();

        // 클래스의 메타데이터를획득한다. 내부 클래스의 구분을 위해 $를 표시함.
        Class<?> helloClass = Class.forName("com.example.springaop.dynamic_proxy.ReflectionTest$Hello");

        Method methodCallA = helloClass.getMethod("callA");
        dynamicCall(methodCallA, target);

        Method methodCallB = helloClass.getMethod("callB");
        dynamicCall(methodCallB, target);
    }

    private void dynamicCall(Method invokedMethod, Hello target) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
        Object result = invokedMethod.invoke(target);
        log.info("result = {}", result);
    }

    private static class Hello {

        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }

    }

}
