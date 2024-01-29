package com.qlzxsyzx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qlzxsyzx.entity.Permission;
import com.qlzxsyzx.mapper.PermissionMapper;
import com.qlzxsyzx.service.PermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Override
    public List<Permission> findPermissionListByRoleId(Integer roleId) {
        return baseMapper.selectPermissionByRoleId(roleId);
    }
}
