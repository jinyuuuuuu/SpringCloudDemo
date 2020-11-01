package com.example.eurekafeignclient.Feign.HystrixFallBacks;

import com.example.eurekafeignclient.Feign.EurekaFeign;
import org.springframework.stereotype.Component;

@Component
public class FeignHystrixFallback1 implements EurekaFeign {
    @Override
    public String syHiFromEurekaClient(String name) {
        return "error";
    }
}
