package com.example.eurekafeignclient.Controller;

import com.example.eurekafeignclient.Service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiFeignController {
    @Autowired
    private HiService hiService;
    @GetMapping("/hi")
    public String sayHi(@RequestParam(defaultValue = "JinYu") String name){
        return hiService.hi(name);
    }
}
