package com.zhl.microservicesimpleconsumermovie.user.controller;

import com.zhl.microservicesimpleconsumermovie.user.entity.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author zhl
 */
@RequestMapping("/movies")
@RestController
@Log
public class MovieController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/users/{id}")
    public User findById(@PathVariable Long id) {
        // 这里用到了RestTemplate的占位符能力
        User user = this.restTemplate.getForObject("http://microservice-provider-user/{id}", User.class, id);
        // ...电影微服务的业务...
        return user;
    }

    /**
     * 查看某个服务的实例信息
     * @author zhanghualei
     * @date 2021/1/21 16:50
     * @param
     */
    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        return this.discoveryClient.getInstances("microservice-provider-user");
    }

    /**
     * 查看负载的接口
     * @author zhanghualei
     * @date 2021/1/21 16:50
     * @param
     */
    @GetMapping("/log-instance")
    public void logUserInstance() {

        ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-provider-user");
        log.info(serviceInstance.getServiceId());
        log.info(serviceInstance.getHost());
        log.info(String.valueOf(serviceInstance.getPort()));
    }
}
