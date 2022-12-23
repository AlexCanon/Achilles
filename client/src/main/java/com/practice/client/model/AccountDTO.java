package com.practice.client.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Сущность для отправки данных на изменение
 *
 * @author Aleksey Konkin
 * @since 20.12.2022
 */
@NoArgsConstructor(access= AccessLevel.PRIVATE)
@AllArgsConstructor
public class AccountDTO {
    private Long id;

    /**
     * Изменение счета
     */
    private Long changeFunds;
}
