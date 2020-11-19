package com.example.eurekafeignclient.Service.impl;

import com.example.eurekafeignclient.Feign.EurekaFeign;
import com.example.eurekafeignclient.Service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HiServiceImpl implements HiService {




    @Resource
    private EurekaFeign eurekaFeign;
    @Override
    public String hi(String name) {
        return eurekaFeign.syHiFromEurekaClient(name);
    }
}
