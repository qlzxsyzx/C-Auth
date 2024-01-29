package com.qlzxsyzx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlzxsyzx.entity.User;
import com.qlzxsyzx.mapper.UserMapper;
import com.qlzxsyzx.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User findByUsername(String username) {
        return query().eq("username", username).one();
    }
}
