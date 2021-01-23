package com.zhl.microservicesimpleconsumermovie.user.feign;

import com.zhl.microservicesimpleconsumermovie.user.entity.User;
import config.FeignConfiguration;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * 使用自定义配置实现
 *
 * @author Zhanghualei
 * @Classname UserFeignClient
 * @Date 2021/1/22 15:42
 */
@FeignClient(name = "microservice-provider-user", configuration = FeignConfiguration.class)
public interface UserFeignClient {
    // @RequestLine("GET /{id}")
    // User findByIdUseFeignRequestLine(@Param("id") Long id);
}