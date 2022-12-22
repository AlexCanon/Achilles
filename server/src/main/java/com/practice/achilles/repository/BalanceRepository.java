package com.practice.achilles.repository;

import com.practice.achilles.model.BankAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author Aleksey Konkin
 * @since 22.12.2022
 */
public interface BalanceRepository extends CrudRepository<BankAccount, Long> {

    Iterable<BankAccount> findAll();

    Optional<BankAccount> findById(Long id);

    BankAccount save(BankAccount ingredient);
}
