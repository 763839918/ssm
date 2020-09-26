package cn.service;

import cn.bean.Permission;

import java.util.List;

public interface PermissionService {
    public List<Permission> findAll();
    public Permission save(Permission permission);
}
