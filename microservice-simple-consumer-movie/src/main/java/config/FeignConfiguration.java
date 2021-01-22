package config;

import feign.Contract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zhanghualei
 * @Classname FeignConfiguration
 * @Date 2021/1/22 15:32
 */
@Configuration
public class FeignConfiguration {
    /**
     * 将契约改成feign原生契约，则可以使用feign的注解
     * @author zhanghualei
     * @date 2021/1/22 15:32
     * @param
     */
    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }
}
