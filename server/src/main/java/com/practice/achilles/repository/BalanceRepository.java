package com.practice.achilles.repository;

import com.practice.achilles.model.BankAccount;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.Optional;

/**
 * @author Aleksey Konkin
 * @since 22.12.2022
 */
@Repository
public interface BalanceRepository extends CrudRepository<BankAccount, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<BankAccount> findById(Long id);
}
