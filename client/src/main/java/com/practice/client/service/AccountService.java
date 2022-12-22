package com.practice.client.service;

import com.practice.client.model.AccountDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.amqp.support.converter.MessageConverter;

/**
 * @author Aleksey Konkin
 * @since 20.12.2022
 */
@Service
public class AccountService {

    private final RabbitTemplate rabbit;

    @Autowired
    public AccountService(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    public void getBalance(Long accountId) {
        rabbit.convertAndSend("tacocloud.order", accountId);
    }

    public void changeBalance(AccountDTO accountDTO) {
        rabbit.convertAndSend("tacocloud.order", accountDTO);
    }
}
