package com.practice.client.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Aleksey Konkin
 * @since 18.12.2022
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix="achilles.client")
public class RequestConfig {

    private String serverUrl;
    private int threadCount = 0;// - количество клиентских потоков (>= 1)
    private int readQuota   = 0 ;// - доля запросов getBalance (>= 0)
    private int writeQuota  = 0 ;// - доля запросов changeBalance (>= 0)
    private List<Long> readIdList = new ArrayList<>(/*Arrays.asList(1L,2L,3L,4L,5L,6L,7L,8L,9L)*/);// - список идентификаторов для getBalance
    private List<Long> writeIdList = new ArrayList<>(/*Arrays.asList(1L,2L,3L,4L,5L,6L,7L,8L,9L)*/);;// - список идентификаторов для changeBalance
}//
