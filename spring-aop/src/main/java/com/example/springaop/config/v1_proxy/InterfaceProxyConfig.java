package com.example.springaop.config.v1_proxy;

import com.example.springaop.app.v1.OrderControllerV1;
import com.example.springaop.app.v1.OrderControllerV1Impl;
import com.example.springaop.app.v1.OrderRepositoryV1;
import com.example.springaop.app.v1.OrderRepositoryV1Impl;
import com.example.springaop.app.v1.OrderServiceV1;
import com.example.springaop.app.v1.OrderServiceV1Impl;
import com.example.springaop.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import com.example.springaop.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import com.example.springaop.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import com.example.springaop.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfaceProxyConfig {

    @Bean
    public OrderControllerV1 orderController(LogTrace logTrace) {
        OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(controllerImpl, logTrace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace logTrace) {
        OrderServiceV1Impl serviceImpl = new OrderServiceV1Impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(serviceImpl, logTrace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
        OrderRepositoryV1Impl repositoryImpl = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(repositoryImpl, logTrace);
    }

}
