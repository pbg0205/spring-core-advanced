package com.example.logtracer.trace.strategy;

import com.example.logtracer.trace.strategy.code.ContextV2;
import com.example.logtracer.trace.strategy.code.Strategy;
import com.example.logtracer.trace.strategy.code.StrategyLogic1;
import com.example.logtracer.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    /**
     * 1. 이전의 방식은 선 조립 후 실행하는 방식이었음.
     * 2. 파라미터로 Strategy 를 전달받으면, Context의 실행 시점에 원하는
     *    Strategy 를 전달할 수 있어 더욱 유연하게 변경할 수 있다.
     */
    @Test
    @DisplayName("파라미터로 전달받는 방식의 전략패턴1 : 생성자")
    void strategyV1() {
        ContextV2 context = new ContextV2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }

    @Test
    @DisplayName("파라미터로 전달받는 방식의 전략패턴2 : 익명 클래스")
    void strategyV2() {
        ContextV2 context = new ContextV2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });

        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
    }

    @Test
    @DisplayName("파라미터로 전달받는 방식의 전략패턴3 : 람다")
    void strategyV3() {
        ContextV2 context = new ContextV2();
        context.execute(() -> log.info("비즈니스 로직1 실행"));
        context.execute(() -> log.info("비즈니스 로직2 실행"));
    }

}
