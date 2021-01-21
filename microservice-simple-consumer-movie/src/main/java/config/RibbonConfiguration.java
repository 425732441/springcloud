package config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zhanghualei
 * @Classname RibbonConfiguration
 * @Date 2021/1/21 17:21
 */
@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule(){
        //负载 随机
        return new RandomRule();
    }
}
