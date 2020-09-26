package cn.dao;

import cn.bean.Role;
import cn.bean.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {
    @Select("select *from users")
    public List<UserInfo> findAll();
    @Select("select *from users where username=#{username}")
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo userInfo);
    @Select("select *from users where id =#{id}")
    @Results({
            @Result(id = true,property = "id" ,column = "id"),
            @Result(property = "username" ,column = "username"),
            @Result(property = "email" ,column = "email"),
            @Result(property = "password" ,column = "password"),
            @Result(property = "phoneNum" ,column = "phoneNum"),
            @Result(property = "status" ,column = "status"),
            @Result(property = "roles" ,column = "id" ,javaType =java.util.List.class,many =@Many(select ="cn.dao.RoleDao.findRoleByUserId"))
    }
    )
    public UserInfo findById(Integer id);
    @Select("select *from role  where id not in (select roleId from users_role where userId =#{id})")
    List<Role> findOtherRoles(Integer id);
    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    public void addRoleToUser(@Param("userId") Integer userId,@Param("roleId") Integer roleId);
    @Select("select *from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id" ,column = "id"),
            @Result(property = "username" ,column = "username"),
            @Result(property = "email" ,column = "email"),
            @Result(property = "password" ,column = "password"),
            @Result(property = "phoneNum" ,column = "phoneNum"),
            @Result(property = "status" ,column = "status"),
            @Result(property = "roles" ,column = "id" ,javaType =java.util.List.class,
                    many =@Many(select ="cn.dao.RoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username);
    @Select("select *from users where id =#{id}")
    public UserInfo findByUserId(Integer id);
}
