package com.example.eurekaclient;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.example.eurekaclient.Mapper")
public class EurekaClientApplication /*extends SpringBootServletInitializer*/ {

   /* @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(EurekaClientApplication.class);
    }
*/
    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

}
