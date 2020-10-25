package com.example.eurekaribbonclient.Service.impl;

import com.example.eurekaribbonclient.Service.RibbonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class RibbonServiceImpl implements RibbonService {
    @Resource
    private RestTemplate restTemplate;


    @HystrixCommand(fallbackMethod = "hiError")
    @Override
    public String hi(String name) {
        return restTemplate.getForObject("http://eureka-client/hi/hiHi?name=" +name,String.class);
    }
    public String hiError(String name){
        return "hi "+name+" Error";
    }
}
