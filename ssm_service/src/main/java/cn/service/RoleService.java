package cn.service;

import cn.bean.Permission;
import cn.bean.Role;

import java.util.List;

public interface RoleService {
    public List<Role> findAll();
    public Role save(Role role);
    public Role findById(Integer roleId);
    public List<Permission> findOtherPermissions(Integer roleId);
    void addPermissionToRole(Integer roleId, Integer[] permissionIds);
}

