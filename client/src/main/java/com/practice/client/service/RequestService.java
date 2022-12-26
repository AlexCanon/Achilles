package com.practice.client.service;

import com.practice.client.config.RequestConfig;
import com.practice.client.model.BankAccount;
import com.practice.client.model.RequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Сервис выполняющий запросы
 *
 * @author Aleksey Konkin
 * @since 25.12.2022
 */
@Slf4j
@Service
public class RequestService {

    private final RestTemplate rest;

    private final RequestConfig config;

    private final String serviceUrl;

    public RequestService(RestTemplate rest, RequestConfig config) {
        this.rest = rest;
        this.config = config;
        serviceUrl = this.config.getServerUrl();
    }

    public void getBalance(Long accountId) {
        log.info("GET REQUEST, id: " + accountId);
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", accountId.toString());
        URI url = UriComponentsBuilder
                .fromHttpUrl(serviceUrl)
                .queryParam("id", accountId)
                .build(urlVariables);
        Long fundBalance = rest.getForObject(url, Long.class);
        log.info("GET RESULT: account {} fund balance {}",accountId, fundBalance);
    }

    public void changeBalance(Long id, Long fundsIncrease) {
        log.info("UPDATE REQUEST id:{}, increaseCount:{}", id, fundsIncrease);
        RequestDTO requestDTO = new RequestDTO(id, fundsIncrease);
        rest.put(serviceUrl, requestDTO);
    }
}
