package com.zhl.microservicesimpleconsumermovie.config;

import config.RibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zhanghualei
 * @Classname TestRibbonConfiguration
 * @Date 2021/1/21 17:23
 */
@Configuration
@RibbonClient(name ="microservice-provider-user",configuration = RibbonConfiguration.class)
public class TestRibbonConfiguration {
}
