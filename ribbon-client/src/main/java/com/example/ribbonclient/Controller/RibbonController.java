package com.example.ribbonclient.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    /**
     * @Author: JinYu
     * @Description: 通过YML里得配置，不让Ribbon从EurekaClient获取服务实例，而是根据自己配置得stores来获取
     * @param  null
     * @Return: java.lang.String
     * @Date: 15:35 2020/10/21
    */
    @GetMapping("/testRibbon")
    public String testRibbon(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("stores");
        return serviceInstance.getHost() + ":" + serviceInstance.getPort();
    }
}
