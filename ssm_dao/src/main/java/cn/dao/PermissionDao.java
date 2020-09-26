package cn.dao;

import cn.bean.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PermissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id} )")
    public List<Permission> findByRoleId(Integer id);
    @Select("select *from permission")
    public List<Permission> findAll();
    @Insert("insert into permission (permissionName,url) values (#{permissionName},#{url})")
    public Permission save(Permission permission);
}
