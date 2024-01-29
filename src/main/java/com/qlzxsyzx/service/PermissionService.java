package com.qlzxsyzx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qlzxsyzx.entity.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    List<Permission> findPermissionListByRoleId(Integer roleId);
}
