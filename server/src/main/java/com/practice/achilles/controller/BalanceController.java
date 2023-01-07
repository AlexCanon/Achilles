package com.practice.achilles.controller;

import com.practice.achilles.config.EfficiencyLogger;
import com.practice.achilles.config.LogReadRequestCount;
import com.practice.achilles.config.LogWriteRequestCount;
import com.practice.achilles.model.RequestDTO;
import com.practice.achilles.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Aleksey Konkin
 * @since 18.12.2022
 */
@RestController
@RequestMapping("server/api/v1")
@RequiredArgsConstructor
public class BalanceController {

    private final EfficiencyLogger efficiencyLogger;
    private final BalanceService balanceService;

    @LogReadRequestCount
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Optional<Long> read(@RequestParam Long id) {
        return balanceService.getBalance(id);
    }

    @LogWriteRequestCount
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody RequestDTO requestDTO) {
        balanceService.changeBalance(requestDTO);
    }
}
