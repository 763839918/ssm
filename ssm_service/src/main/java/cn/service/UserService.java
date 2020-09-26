package cn.service;

import cn.bean.Role;
import cn.bean.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    public List<UserInfo> findAll();
    public void save(UserInfo userInfo);
    public UserInfo findById(Integer id);
    public List<Role> findOtherRoles(Integer id);
    public void addRoleToUser(Integer userId, Integer[] roleIds);
}

