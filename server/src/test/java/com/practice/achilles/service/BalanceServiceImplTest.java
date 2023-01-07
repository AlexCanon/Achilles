package com.practice.achilles.service;

import com.practice.achilles.model.BankAccount;
import com.practice.achilles.model.RequestDTO;
import com.practice.achilles.repository.BalanceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class BalanceServiceImplTest {

    private BalanceServiceImpl balanceService;

    @Mock
    private BalanceRepository balanceRepository;

    private final Long id = 1L;
    private final Long moneyAmount = 5L;
    private final BankAccount testAccount = new BankAccount(id, moneyAmount);

    // reactive test object
    private Mono<BankAccount> expected;

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
        balanceService = new BalanceServiceImpl(balanceRepository);

        expected = Mono.just(testAccount);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getBalance() {
        given(balanceRepository.findById(id)).willReturn(expected);
        Mono<Long> actual = balanceService.getBalance(id);
        verify(balanceRepository).findById(id);

        StepVerifier.create(actual)
                // check elements in content of subscription
                .expectNext(moneyAmount)
                // check process correct ending
                .verifyComplete();
    }

    @Test
    void changeBalance() {//todo still need fix
        given(balanceRepository.save(testAccount)).willReturn(expected);

        RequestDTO dto = new RequestDTO(1L, 2L);
        balanceService.changeBalance(dto);

        verify(balanceRepository).save(testAccount);
    }
}