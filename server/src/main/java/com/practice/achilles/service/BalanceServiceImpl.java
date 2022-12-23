package com.practice.achilles.service;

import com.practice.achilles.model.BankAccount;

import com.practice.achilles.repository.BalanceRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Aleksey Konkin
 * @since 18.12.2022
 */
@Service
@Transactional
@Slf4j
public class BalanceServiceImpl implements BalanceService {

    private final BalanceRepository balanceRepository;

    public BalanceServiceImpl(BalanceRepository balanceRepository) {
        this.balanceRepository = balanceRepository;
    }


    //todo подсчитывать количество запросов getBalance,
    // changeBalance и их сумму в единицу времени. Результат записывать в лог
    @Override
    public Optional<Long> getBalance(Long id) {
        Optional<BankAccount> account = balanceRepository.findById(id);
        log.info("log - get call");
        log.warn("log - get call");
        log.error("log - get call");
        log.debug("log - get call");
        return account.map(BankAccount::getFundsAmount);
    }

    @Override
    public void changeBalance(Long id, Long amount) {
        log.info("log - change call");
        balanceRepository.save(new BankAccount(id, amount));
    }
}
