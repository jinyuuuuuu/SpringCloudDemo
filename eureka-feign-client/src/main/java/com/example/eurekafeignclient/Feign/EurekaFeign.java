package com.example.eurekafeignclient.Feign;

import com.example.eurekafeignclient.Config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: JinYu
 * @Description: 这里定义了一个可以提供远程调用的接口，通过在Service层进行使用
 * @param null
 * @Return: null
 * @Date: 10:51 2020/10/22
*/
@FeignClient(value = "eureka-client",configuration =FeignConfiguration.class)
public interface EurekaFeign {
    /**
     * @Author: JinYu
     * @Description: API接口需要与服务提供者的API保持一致
     * @param name
     * @Return: java.lang.String
     * @Date: 11:09 2020/10/22
    */
    @GetMapping("/hi/hiHi")
    String syHiFromEurekaClient(@RequestParam(value = "name")String name);
}
