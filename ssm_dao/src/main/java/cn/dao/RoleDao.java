package cn.dao;

import cn.bean.Permission;
import cn.bean.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleDao {
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result( id = true,property = "id",column = "id"),
            @Result( property = "roleName",column = "roleName"),
            @Result( property = "roleDesc",column = "roleDesc"),
            @Result( property = "permissions",column = "id" ,javaType = java.util.List.class,many = @Many(select = "cn.dao.PermissionDao.findByRoleId"))
})
public List<Role> findRoleByUserId(Integer userId);
@Select("select *from role")
    public List<Role> findAll();
    @Insert("insert into role (roleName,roleDesc) values(#{roleName},#{roleDesc})")
    public Role save(Role role);
    @Select("select * from role where id =#{roleId}")
    @Results({
            @Result( id = true,property = "id",column = "id"),
            @Result( property = "roleName",column = "roleName"),
            @Result( property = "roleDesc",column = "roleDesc"),
            @Result( property = "users" ,column = "id" ,javaType =java.util.List.class,many = @Many(select = "cn.dao.UserDao.findByUserId"))
    })
    public Role findById(Integer roleId);
    @Select("select *from permission where id not in (select permissionId from role_permission where roleId=#{roleId}")
    public List<Permission> findOtherPermissions(Integer roleId);
    @Insert("insert into role-permission (roleId,permissionId) values (#{roleId},#{permissionId})")
    public void addPermissionToRole(@Param("roleId") Integer roleId,@Param("permissionId") Integer permissionId);

}
