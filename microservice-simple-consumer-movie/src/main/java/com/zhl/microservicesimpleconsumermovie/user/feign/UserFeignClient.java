package com.zhl.microservicesimpleconsumermovie.user.feign;

import com.zhl.microservicesimpleconsumermovie.user.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Zhanghualei
 * @Classname UserFeignClient
 * @Date 2021/1/21 17:57
 */
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);
}
