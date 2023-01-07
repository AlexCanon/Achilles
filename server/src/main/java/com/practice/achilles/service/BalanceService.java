package com.practice.achilles.service;

import com.practice.achilles.model.RequestDTO;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Интерфейс сервиса для работы с банковским счётом содержит два метода
 *
 * @author Aleksey Konkin
 * @since 18.12.2022
 */
public interface BalanceService {
    /**
     * Получение баланса
     *
     * @param id идентификатор банковского счёта
     * @return сумма денег на банковском счёте
     */
    Mono<Long> getBalance(Long id);

    /**
     * Изменение баланса на определённое значение
     *
     * @param id    идентификатор банковского счёта
     * @param amount сумма денег, которую нужно добавить к банковскому счёту
     */
    void changeBalance(RequestDTO requestDTO);
}
