package com.example.eurekaclient.Controller;

import com.example.eurekaclient.Service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/hi")
public class HiController {
    @Value("${server.port}")
    String port;
    @Resource
    private UserService userService;
    @GetMapping("/hiHi")
    public String Hi(@RequestParam String name){
        return "hi" + name + "i'm from port:"+ port;
    }
    @GetMapping("/getPassword")
    public String getPassword(@RequestParam String username){
        return userService.getPassword(username);
    }
}
