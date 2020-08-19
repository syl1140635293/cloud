package com.syl.dashboard;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableHystrixDashboard
public class DashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(DashboardApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean hystrixStreamServlet(){
        ServletRegistrationBean registrationBean =  new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        registrationBean.addUrlMappings("*");
        return registrationBean;
    }

}
