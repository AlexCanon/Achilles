package com.practice.client.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Aleksey Konkin
 * @since 18.12.2022
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {

    private Long id;
    /**
     * the amount of funds in the bank account
     */
    private Long fundsAmount;
}
