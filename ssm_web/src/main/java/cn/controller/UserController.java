package cn.controller;

import cn.bean.Role;
import cn.bean.UserInfo;
import cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    //@RolesAllowed("ADMIN,USER")js250注解
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN,ROLE_USER")
    public ModelAndView findAll(){
        ModelAndView mv =new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(int id){
        ModelAndView mv =new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }
    @RequestMapping("/findUserByIdAndAllRole.do")
    private ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) Integer id){
        ModelAndView mv =new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        List<Role> otherRoles = userService.findOtherRoles(id);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }
    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId" ,required = true) int userId,@RequestParam(name = "ids",required = true) int[] roleIds){
       userService.addRoleToUser(userId,roleIds);
       return "redirect:findAll.do";
    }
}
