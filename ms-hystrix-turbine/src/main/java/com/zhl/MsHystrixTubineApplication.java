package com.zhl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

/**
 * @author Zhanghualei
 * @Classname MsHystrixTubineApplication
 * @Date 2021/1/25 16:09
 */
@SpringBootApplication
@EnableTurbineStream
public class MsHystrixTubineApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsHystrixTubineApplication.class, args);
    }
}
