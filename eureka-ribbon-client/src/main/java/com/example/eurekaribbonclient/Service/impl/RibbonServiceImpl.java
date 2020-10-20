package com.example.eurekaribbonclient.Service.impl;

import com.example.eurekaribbonclient.Service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
public class RibbonServiceImpl implements RibbonService {
    @Resource
    private RestTemplate restTemplate;

    @Override
    public String hi(String name) {
        return restTemplate.getForObject("http://eureka-client/hi/hiHi?name=" +name,String.class);
    }
}
