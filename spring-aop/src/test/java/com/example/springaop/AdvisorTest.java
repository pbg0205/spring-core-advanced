package com.example.springaop;

import com.example.springaop.dynamic_proxy.cglib.code.ServiceImpl;
import com.example.springaop.dynamic_proxy.cglib.code.ServiceInterface;
import com.example.springaop.dynamic_proxy.proxyfactory.code.TImeAdvice;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;

@Slf4j
public class AdvisorTest {

    @DisplayName("Advisor 를 이용한 부가 기능 추가")
    @Test
    void advisorTest1() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TImeAdvice());
        proxyFactory.addAdvisor(advisor);

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();
    }

    @DisplayName("customizing pointcut 기능 추가")
    @Test
    void advisorTest2() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new CustomPointcut(), new TImeAdvice());
        proxyFactory.addAdvisor(advisor);

        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();
    }

    private static class CustomPointcut implements Pointcut {

        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new CustomMethodMatcher();
        }

        private static class CustomMethodMatcher implements MethodMatcher {

            private static final String matchName = "save";

            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                boolean result = method.getName().equals(matchName);
                log.info("포인트컷 호출 method={} targetClass={}", method.getName(), targetClass);
                log.info("포인트컷 결과 result={}", result);
                return result;
            }

            /**
             * - isRuntime() == false : 클래스의 정적 정보만 사용하기 떄문에 스프링 내부에서 캐싱을 통해 성능향상
             * - isRuntime() == true : 매개변수가 동적으로 변경되는 것을 가정하기 때문에 캐싱을 하지 않는다.
             */
            @Override
            public boolean isRuntime() {
                return false;
            }

            /**
             * - isRuntime()이 true 이면 matches(... args) 메서드가 대신 호출된다.
             * - matches(... args) : 동적으로 넘어오는 매개변수를 판단 로직으로 사용할 수 있다.
             */
            @Override
            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                return false;
            }
        }
    }
}
