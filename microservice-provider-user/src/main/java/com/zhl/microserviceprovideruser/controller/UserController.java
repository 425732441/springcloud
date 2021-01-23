package com.zhl.microserviceprovideruser.controller;

import com.zhl.microserviceprovideruser.domain.User;
import com.zhl.microserviceprovideruser.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;


/**
 * 作用：
 * ① 测试服务实例的相关内容
 * ② 为后来的服务做提供
 *
 * @author eacdy
 */
@RestController
public class UserController {
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private UserRepository userRepository;

    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    /**
     * 注：@GetMapping("/{id}")是spring 4.3的新注解等价于：
     *
     * @param id
     * @return user信息
     * @RequestMapping(value = "/id", method = RequestMethod.GET)
     * 类似的注解还有@PostMapping等等
     */
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails)principal;
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                LOGGER.info("当前用户是 {}，当前角色是 {}", userDetails.getUsername(), authority.getAuthority());
            }
        }
        User findOne = this.userRepository.findOne(id);
        return findOne;
    }

    @GetMapping("/get1")
    public List<User> get1(@RequestParam("id") Long id, @RequestParam("username") String username){
        return userRepository.findAll((Specification<User>)(root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.and(criteriaBuilder.equal(root.get("username"),username),criteriaBuilder.equal(root.get("id"),id)));
    }

    /**
     * 本地服务实例的信息
     *
     * @return
     */
    @GetMapping("/instance-info")
    public ServiceInstance showInfo() {
        ServiceInstance localServiceInstance = this.discoveryClient.getLocalServiceInstance();
        return localServiceInstance;
    }
}
