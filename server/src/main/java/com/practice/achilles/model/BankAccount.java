package com.practice.achilles.model;

import lombok.*;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

import javax.persistence.Entity;

/**
 * @author Aleksey Konkin
 * @since 18.12.2022
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    private Long id;
    /**
     * the amount of funds in the bank account
     */
    @Column(name = "funds_amount")
    private Long fundsAmount;
}
