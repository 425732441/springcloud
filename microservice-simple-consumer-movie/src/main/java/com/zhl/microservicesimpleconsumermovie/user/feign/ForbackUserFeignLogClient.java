package com.zhl.microservicesimpleconsumermovie.user.feign;

import com.zhl.microservicesimpleconsumermovie.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Zhanghualei
 * @Classname ForbackUserFeignLogClient
 * @Date 2021/1/23 18:18
 */
@Component
public class ForbackUserFeignLogClient implements UserFeignLogClient{
    @Override
    public User findByIdUseLog(Long id) {
        return null;
    }

    @Override
    public List<User> get1(Long id, String username) {
        return null;
    }
}
