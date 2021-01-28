package com.zhl.msfileupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MsFileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsFileUploadApplication.class, args);
    }

}
