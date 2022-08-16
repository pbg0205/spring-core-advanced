package com.example.logtracer.trace.strategy;

import com.example.logtracer.trace.strategy.code.ContextV1;
import com.example.logtracer.trace.strategy.code.Strategy;
import com.example.logtracer.trace.strategy.code.StrategyLogic1;
import com.example.logtracer.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {

    @Test
    @DisplayName("전략 패턴 적용 테스트")
    void strategyV1() {
        Strategy strategyLogic1 = new StrategyLogic1();
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        Strategy strategyLogic2 = new StrategyLogic2();
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

    @Test
    @DisplayName("전략 패턴 적용 : 익명 클래스")
    void strategyV2() {
        Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        log.info("strategyLogic1={}", strategyLogic1.getClass());
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        log.info("strategyLogic1={}", strategyLogic1.getClass());
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

    @Test
    @DisplayName("전략 패턴 적용 : 익명 클래스")
    void strategyV3() {
        ContextV1 context1 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        context1.execute();

        ContextV1 context2 = new ContextV1(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
        context2.execute();
    }

    @Test
    @DisplayName("전략 패턴 적용 : 람다")
    void strategyV4() {
        Strategy strategyLogic1 = () -> log.info("비즈니스 로직1 실행");
        log.info("strategyLogic1={}", strategyLogic1.getClass());
        ContextV1 context1 = new ContextV1(strategyLogic1);
        context1.execute();

        Strategy strategyLogic2 = () -> log.info("비즈니스 로직2 실행");
        log.info("strategyLogic1={}", strategyLogic1.getClass());
        ContextV1 context2 = new ContextV1(strategyLogic2);
        context2.execute();
    }

}
