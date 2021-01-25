package com.zhl.mshystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class MsHystrixDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsHystrixDashboardApplication.class, args);
    }

}
