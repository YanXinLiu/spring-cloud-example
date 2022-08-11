package com.yanxin.admin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@Slf4j
public class ThreadPoolConfiguration implements AsyncConfigurer {

    public static final int cpuNum = Runtime.getRuntime().availableProcessors();

    @Override
    @Bean("asyncExecutor")
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        // 设置线程池参数信息
        taskExecutor.setCorePoolSize(cpuNum);
        taskExecutor.setMaxPoolSize(cpuNum * 2);
        taskExecutor.setQueueCapacity(500);
        taskExecutor.setKeepAliveSeconds(60);
        taskExecutor.setThreadNamePrefix("dpa.pool.executor");
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        taskExecutor.setAwaitTerminationSeconds(60);
        // 修改拒绝策略为使用当前线程执行
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化线程池
        taskExecutor.initialize();
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {

        return ((ex, method, params) -> {
            log.error("ex.getMessage: {}, exception method: {}", ex, method.getName());
        });
    }
}
