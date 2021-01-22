package com.zhl.microservicesimpleconsumermovie.user.controller;

import com.zhl.microservicesimpleconsumermovie.user.entity.User;
import com.zhl.microservicesimpleconsumermovie.user.feign.UserFeignClient;
import com.zhl.microservicesimpleconsumermovie.user.feign.UserFeignClientForCustom;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author zhl
 */
@Import(FeignClientsConfiguration.class)
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
    @Autowired
    private UserFeignClient userFeignClient;
    private UserFeignClientForCustom userUserFeignClient;
    private UserFeignClientForCustom userAdminFeignClient;


    @Autowired
    public MovieController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
        // 这边的decoder、encoder、client、contract，可以debug看看是什么实例。
        this.userUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user", "password1")).target(UserFeignClientForCustom.class, "http://microservice-provider-user/");
        this.userAdminFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin", "password2"))
                .target(UserFeignClientForCustom.class, "http://microservice-provider-user/");
    }

    @GetMapping("/users/{id}")
    public User findById(@PathVariable Long id) {
        // 这里用到了RestTemplate的占位符能力
        User user = this.restTemplate.getForObject("http://microservice-provider-user/{id}", User.class, id);
        // ...电影微服务的业务...
        return user;
    }

    @GetMapping("/user-user/{id}")
    public User findByIdUser(@PathVariable Long id) {
        return this.userUserFeignClient.findByIdUseFeignRequestLine(id);
    }

    @GetMapping("/user-admin/{id}")
    public User findByIdAdmin(@PathVariable Long id) {
        return this.userAdminFeignClient.findByIdUseFeignRequestLine(id);
    }

    @GetMapping("/user/feignRequestLine/{id}")
    public User feignRequestLine(@PathVariable Long id) {
        return userFeignClient.findByIdUseFeignRequestLine(id);
    }

    /**
     * 查看某个服务的实例信息
     *
     * @param
     * @author zhanghualei
     * @date 2021/1/21 16:50
     */
    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo() {
        return this.discoveryClient.getInstances("microservice-provider-user");
    }

    /**
     * 查看负载的接口
     *
     * @param
     * @author zhanghualei
     * @date 2021/1/21 16:50
     */
    @GetMapping("/log-instance")
    public void logUserInstance() {

        ServiceInstance serviceInstance = loadBalancerClient.choose("microservice-provider-user");
        log.info(serviceInstance.getServiceId());
        log.info(serviceInstance.getHost());
        log.info(String.valueOf(serviceInstance.getPort()));
    }
}
