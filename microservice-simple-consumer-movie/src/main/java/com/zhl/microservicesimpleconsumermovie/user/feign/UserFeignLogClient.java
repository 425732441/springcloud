package com.zhl.microservicesimpleconsumermovie.user.feign;

import com.zhl.microservicesimpleconsumermovie.user.entity.User;
import config.FeignConfiguration;
import config.FeignLogConfiguration;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 使用自定义配置实现,记录log
 *
 * @author Zhanghualei
 * @Classname UserFeignClient1
 * @Date 2021/1/22 15:42
 */
@FeignClient(name = "microservice-provider-user", configuration = FeignLogConfiguration.class)
public interface UserFeignLogClient {
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    User findByIdUseLog(@PathVariable("id") Long id);

    @RequestMapping(value = "/get1",method = RequestMethod.GET)
    List<User> get1(@RequestParam("id") Long id, @RequestParam("username") String username);
}