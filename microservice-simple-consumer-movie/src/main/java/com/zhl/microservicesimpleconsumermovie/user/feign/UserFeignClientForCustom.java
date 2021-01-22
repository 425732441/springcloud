package com.zhl.microservicesimpleconsumermovie.user.feign;

import com.zhl.microservicesimpleconsumermovie.user.entity.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Zhanghualei
 * @Classname UserFeignClient1
 * @Date 2021/1/22 15:42
 */
public interface UserFeignClientForCustom {
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User findByIdUseFeignRequestLine(@PathVariable("id") Long id);
}