package com.practice.client.controller;

import com.practice.client.service.ClientConfigService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Aleksey Konkin
 * @since 23.12.2022
 */
@RestController
@RequestMapping("client/api/v1/")
public class ClientController {
    private final ClientConfigService runClientService;

    public ClientController(ClientConfigService runClientService) {
        this.runClientService = runClientService;
    }

    @GetMapping("run")
    @ResponseStatus(HttpStatus.OK)
    public void run() {
        runClientService.runClient();
    }

    @GetMapping("stop")
    @ResponseStatus(HttpStatus.OK)
    public void stop() {
        runClientService.stopRequiring();
    }
}
