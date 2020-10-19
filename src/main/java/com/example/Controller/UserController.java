package com.example.Controller;

import com.example.Service.UserService;
import com.example.Util.ResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.entity.User;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/findUserByNickName")
    public Map<String,Object> findUserByNickName(String name){
        User user = userService.findUserByNickName(name);
        return ResultUtil.resultSuccess("查找成功",1,user);
    }
}
