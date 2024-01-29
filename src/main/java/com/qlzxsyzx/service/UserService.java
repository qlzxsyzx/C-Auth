package com.qlzxsyzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qlzxsyzx.entity.User;

public interface UserService extends IService<User>{

	//根据用户名查询用户信息
	User findByUsername(String username);
}
