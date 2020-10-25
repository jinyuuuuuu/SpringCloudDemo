package com.example.eurekafeignclient.Config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

@Configuration
public class FeignConfiguration {
    @Bean
    public Retryer feignRetryer(){
        //会在失败时进行重试，这里配置的就是间隔100ms的5次重试
        return new Retryer.Default(100,SECONDS.toMillis(1),5);
    }
}
