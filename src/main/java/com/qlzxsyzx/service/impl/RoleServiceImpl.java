package com.qlzxsyzx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlzxsyzx.entity.Role;
import com.qlzxsyzx.mapper.RoleMapper;
import com.qlzxsyzx.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
}
