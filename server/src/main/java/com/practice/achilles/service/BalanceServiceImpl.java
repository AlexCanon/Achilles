package com.practice.achilles.service;

import com.practice.achilles.model.BankAccount;
import com.practice.achilles.model.RequestDTO;
import com.practice.achilles.repository.BalanceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author Aleksey Konkin
 * @since 18.12.2022
 */
@Service
@Slf4j
@Transactional(timeout = 3)//in case of deadlock (because of pessimistic write)
@RequiredArgsConstructor
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;

    @Override
    public Mono<Long> getBalance(Long id) {
        Mono<BankAccount> account = balanceRepository.findById(id);
        log.info("♥♥♥SERVICE GET CALL by id: {}", id);
        return account.map(BankAccount::getFundsAmount);
    }

    @Override
    public void changeBalance(RequestDTO requestDTO) {
        log.info("♠♠♠SERVICE PUT CALL");
        Long id = requestDTO.getId();
        Long amount = requestDTO.getChangeFunds();

        if (id != null && amount != null) {
            Mono<BankAccount> account = balanceRepository.findById(id);
            account.blockOptional().ifPresentOrElse(acc -> {
                acc.setFundsAmount(acc.getFundsAmount() + amount);
                log.info("Account {} funds was {}, increased to {} ", id, acc.getFundsAmount(), amount);
            }, () -> {
                log.warn("Account with id {} not found", id);
            });
        } else {
            log.error("Error during increasing funds, no id or amount given");
        }
    }
}
