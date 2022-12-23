package com.practice.client.controller;

import com.practice.client.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aleksey Konkin
 * @since 23.12.2022
 */
@RestController
@RequestMapping("client/api/v1/")
public class ClientController {
    private final AccountService accountService;

    public ClientController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("run")
    @ResponseStatus(HttpStatus.OK)
    public void run() {
        accountService.runClient();
    }

    @GetMapping("stop")
    @ResponseStatus(HttpStatus.OK)
    public void stop() {
        accountService.stopRequiring();
    }
}
