package com.zhl.microservicesimpleconsumermovie.user.controller;

import com.zhl.microservicesimpleconsumermovie.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
public class MovieController {
  @Autowired
  private RestTemplate restTemplate;
  @Autowired
  private DiscoveryClient discoveryClient;

  @GetMapping("/users/{id}")
  public User findById(@PathVariable Long id) {
    // 这里用到了RestTemplate的占位符能力
    User user = this.restTemplate.getForObject("http://localhost:8000/{id}", User.class, id);
    // ...电影微服务的业务...
    return user;
  }

  @GetMapping("/user-instance")
  public List<ServiceInstance> showInfo(){
    return this.discoveryClient.getInstances("microservice-provider-user");
  }
}
