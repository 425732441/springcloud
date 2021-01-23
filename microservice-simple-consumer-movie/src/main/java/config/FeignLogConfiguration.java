package config;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zhanghualei
 * @Classname FeignLogConfiguration
 * @Date 2021/1/23 11:07
 */
@Configuration
public class FeignLogConfiguration {
    @Bean
    public Logger.Level getFeignLogLevel(){
        return Logger.Level.FULL;
    }
    @Bean
    public BasicAuthRequestInterceptor getBasicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("user","password1");
    }
}
