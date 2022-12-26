package com.practice.client.service;

import com.practice.client.config.RequestConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Сервис по настройке клиента
 *
 * @author Aleksey Konkin
 * @since 20.12.2022
 */
@Slf4j
@Service
public class ClientConfigService {

    private final RequestConfig config;

    private final RequestService requestService;

    // Глобальный испольнитель содержащий пул потоков
    private ExecutorService executor;


    private boolean clientON = false;

    public ClientConfigService(RequestConfig config, RequestService requestService) {
        this.config = config;
        this.requestService = requestService;
    }

    public void runClient() {
        int readQuota = config.getReadQuota();
        int writeQuota = config.getWriteQuota();
        //ids for write requests
        List<Long> writeIdList = config.getWriteIdList();
        //ids for read requests
        List<Long> readIdList = config.getReadIdList();
        // is requests looped
        Boolean isLooped = config.getIsLooped();
        clientON = true;
        executor = Executors.newFixedThreadPool(config.getThreadCount());
        Runnable task = isLooped ? loopedTask(readQuota, writeQuota, writeIdList, readIdList) :
                notLoopedTask(readQuota, writeQuota, writeIdList, readIdList);
        for (int i = 0; i < config.getThreadCount(); i++) {
            executor.submit(task);
        }

        log.info("END");
    }

    private Runnable loopedTask(int readQuota, int writeQuota, List<Long> writeIdList, List<Long> readIdList) {
        return () -> {
            while (clientON) {
                randomlyExecuteReadOrChange(readQuota, writeQuota, writeIdList, readIdList);
            }
        };
    }

    private Runnable notLoopedTask(int readQuota, int writeQuota, List<Long> writeIdList, List<Long> readIdList) {
        return () -> randomlyExecuteReadOrChange(readQuota, writeQuota, writeIdList, readIdList);
    }

    /**
     * Случайно выполнить запрос обновления или чтения
     *
     * @param readQuota   доля запросов чтения
     * @param writeQuota  доля запросов обновления(запись)
     * @param writeIdList список id для запросов на запись
     * @param readIdList  список id для запросов на чтение
     */
    private void randomlyExecuteReadOrChange(int readQuota, int writeQuota, List<Long> writeIdList, List<Long> readIdList) {
        // вероятность вызова метода getBalance
        double readProbability = (double) readQuota / (double) (readQuota + writeQuota);

        if (ThreadLocalRandom.current().nextDouble() < readProbability) {
            requestService.getBalance(randomFromList(readIdList));
        } else {
            // increase by a random number
            int min = 1;
            int max = 100;
            Long randomIncreasedCount = (long) ThreadLocalRandom.current().nextInt(min, max + 1);
            requestService.changeBalance(randomFromList(writeIdList), randomIncreasedCount);
        }
    }

    private Long randomFromList(List<Long> list) {
        Random randomizer = new Random();
        return list.get(randomizer.nextInt(list.size()));
    }

    /**
     * Остановить~прекратить запросы с клиента
     */
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

    /**
     * Остановить потоки в пуле
     *
     * @param executor исполнитель с пулом
     */
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
