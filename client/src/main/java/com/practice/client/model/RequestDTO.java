package com.practice.client.model;

import lombok.*;

/**
 * Сущность для отправки данных на изменение
 *
 * @author Aleksey Konkin
 * @since 20.12.2022
 */
@NoArgsConstructor(access= AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RequestDTO {
    private Long id;

    /**
     * Изменение счета
     */
    private Long changeFunds;
}
