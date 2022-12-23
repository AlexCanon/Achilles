package com.practice.client.service;

import com.practice.client.config.RequestConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Aleksey Konkin
 * @since 20.12.2022
 */
@Slf4j
@Service
public class AccountService {

    private RequestConfig config;

    private ExecutorService executor;

    private boolean clientON = false;

    public AccountService(RequestConfig config) {
        this.config = config;
    }

    public void runClient() {
        int readQuota = config.getReadQuota();
        int writeQuota = config.getWriteQuota();
        List<Long> writeIdList = config.getWriteIdList();
        List<Long> readIdList = config.getReadIdList();
        clientON = true;
        executor = Executors.newFixedThreadPool(config.getThreadCount());
        Runnable task = () -> {
            while (clientON) {
                randomlyExecuteReadOrChange(readQuota, writeQuota, writeIdList, readIdList);
            }
        };
        for (int i = 0; i < config.getThreadCount(); i++) {
            executor.submit(task);
        }

        log.info("END");
    }

    private void randomlyExecuteReadOrChange(int readQuota, int writeQuota, List<Long> writeIdList, List<Long> readIdList) {
        // вероятность вызова метода getBalance
        double readProbability = (double) readQuota / (double) (readQuota + writeQuota);

        if (ThreadLocalRandom.current().nextDouble() < readProbability) {
            getBalance(randomFromList(readIdList));
        } else {
            changeBalance(randomFromList(writeIdList), 1L);
        }
    }

    private Long randomFromList(List<Long> list) {
        Random randomizer = new Random();
        return list.get(randomizer.nextInt(list.size()));
    }

    public void getBalance(Long accountId) {
        log.info("GET call, id: " + accountId);
    }

    public void changeBalance(Long id, Long fundsIncrease) {
        log.info(String.format("UPDATE CALL id:%s, increaseCount:%s", id, fundsIncrease));
    }

    public void stopRequiring() {
        if (executor == null) {
            String alreadyStopMessage = "Executor is not running";
            log.error(alreadyStopMessage);
        } else {
            clientON = false;
            shutDownThreads(executor);
            executor = null;
        }
    }

    private void shutDownThreads(ExecutorService executor) {
        try {
            log.warn("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.warn("tasks interrupted");
        } finally {
            if (!executor.isTerminated()) {
                log.error("cancel non-finished tasks");
            }
            executor.shutdownNow();
            log.warn("shutdown finished");
        }
    }
}
