package com.practice.achilles.controller;

import com.practice.achilles.service.BalanceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Aleksey Konkin
 * @since 18.12.2022
 */
@RestController
@RequestMapping("balance/api/v1/")
public class BalanceController {
    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Optional<Long> read(@RequestParam Long id) {
        return balanceService.getBalance(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void change(@RequestParam Long id, @RequestParam Long increaseCount) {
        balanceService.changeBalance(id, increaseCount);
    }
}
