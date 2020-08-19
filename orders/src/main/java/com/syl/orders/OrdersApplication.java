package com.syl.orders;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableDistributedTransaction
@EnableTransactionManagement
public class OrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);






    }



}
