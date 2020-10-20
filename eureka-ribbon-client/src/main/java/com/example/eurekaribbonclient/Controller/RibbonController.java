package com.example.eurekaribbonclient.Controller;

import com.example.eurekaribbonclient.Service.RibbonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ribbon")
public class RibbonController {
    @Resource
    private RibbonService ribbonService;
    @GetMapping("/hi")
    public String hi(@RequestParam(required = false,defaultValue = "JinYu") String name){
        return ribbonService.hi(name);
    }
}
