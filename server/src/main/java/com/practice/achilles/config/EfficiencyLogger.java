package com.practice.achilles.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Aleksey Konkin
 * @since 26.12.2022
 */
@Aspect
@Component
@Slf4j
public class EfficiencyLogger {
    /**
     * Количество запросов на чтение
     */
    private int readRequestsCount = 0;

    /**
     * Количество запросов на запись
     */
    private int writeRequestsCount = 0;

    @Before("@annotation(com.practice.achilles.config.LogReadRequestCount)")
    public void increaseReadRequestsCount() {
        this.readRequestsCount += 1;
    }

    @Before("@annotation(com.practice.achilles.config.LogWriteRequestCount)")
    public void increaseWriteRequestsCount() {
        this.writeRequestsCount += 1;
    }

    /**
     * Подсчитать и залогировать количество запросов к endpoint'ам
     * RPS (request per second)
     */
    @Scheduled(fixedDelay = 2000)//every 2 seconds
    public void calculateAndLogServiceRPS() {
        double readCount = 0;
        double writeCount = 0;
        if (readRequestsCount != 0) {
            readCount = (double) readRequestsCount / 2;
            readRequestsCount = 0;
        }
        if (writeRequestsCount != 0) {
            writeCount = (double) writeRequestsCount / 2;
            writeRequestsCount = 0;
        }
        if (readCount > 0 || writeCount > 0) {
            log.info("♦♦♦READ requests per second: {}", readCount);
            log.info("♦♦♦WRITE requests per second: {}", writeCount);
            log.info("♦♦♦TOTAL requests per second: {}", readCount + writeCount);
        }
    }
}
