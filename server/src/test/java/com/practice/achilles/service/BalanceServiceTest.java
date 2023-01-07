package com.practice.achilles.service;

import com.practice.achilles.model.BankAccount;
import com.practice.achilles.model.RequestDTO;
import com.practice.achilles.repository.BalanceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

class BalanceServiceImplTest {

    private BalanceServiceImpl balanceService;

    @Mock
    private BalanceRepository balanceRepository;

    Long id = 1L;
    Long changeCount = 5L;
    BankAccount testAccount = new BankAccount(id, changeCount);

    @BeforeEach
     void setUp() {
        MockitoAnnotations.openMocks(this);
        balanceService = new BalanceServiceImpl(balanceRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getBalance() {
        given(balanceRepository.findById(id)).willReturn(Optional.of(testAccount));
        Optional<Long> actual = balanceService.getBalance(id);
        verify(balanceRepository).findById(id);

        assertThat(actual).isEqualTo(Optional.of(changeCount));
    }

    @Test
    void changeBalance() {
        given(balanceRepository.save(testAccount)).willReturn(testAccount);

        RequestDTO dto = new RequestDTO(1L, 2L);
        balanceService.changeBalance(dto);

        verify(balanceRepository).save(testAccount);
    }
}