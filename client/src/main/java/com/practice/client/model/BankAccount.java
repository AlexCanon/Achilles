package com.practice.client.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * @author Aleksey Konkin
 * @since 18.12.2022
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank_account")
public class BankAccount {

    @Id
    private Long id;
    /**
     * the amount of funds in the bank account
     */
    @Column(value = "funds_amount")
    private Long fundsAmount;
}
