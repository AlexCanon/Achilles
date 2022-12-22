package com.practice.client.config;

import java.util.List;

/**
 * @author Aleksey Konkin
 * @since 18.12.2022
 */
public class RequestConfig {
    private int threadCount = 1;// - количество клиентских потоков (>= 1)
    private int readQuota   = 0 ;// - доля запросов getBalance (>= 0)
    private int writeQuota  = 0 ;// - доля запросов changeBalance (>= 0)
    private List<Long> readIdList  ;// - список идентификаторов для getBalance
    private List<Long> writeIdList ;// - список идентификаторов для changeBalance
}//
