package com.example.Service.implement;

import com.example.Dao.UserMapper;
import com.example.entity.User;
import com.example.entity.UserExample;
import com.example.Service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public User findUserByNickName(String name) {

        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andNicknameEqualTo(name);
        List<User> user = userMapper.selectByExample(example);
        return user.get(0);
    }
}
