package com.practice.achilles.repository;

import com.practice.achilles.model.BankAccount;
import org.springframework.data.jpa.repository.Lock;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.persistence.LockModeType;

/**
 * @author Aleksey Konkin
 * @since 22.12.2022
 */
@Repository
public interface BalanceRepository extends ReactiveCrudRepository<BankAccount, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Mono<BankAccount> findById(Long id);
}
