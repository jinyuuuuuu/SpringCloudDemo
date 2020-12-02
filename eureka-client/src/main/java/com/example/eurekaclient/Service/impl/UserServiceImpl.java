package com.example.eurekaclient.Service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.eurekaclient.Entity.User;
import com.example.eurekaclient.Mapper.UserMapper;
import com.example.eurekaclient.Service.UserService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public String getPassword(String username) {
        Page
        Wrapper<User> wrapper = new QueryWrapper<>();
        ((QueryWrapper<User>) wrapper).eq("username_",username);
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
        return user.getPassword_();
    }
}
