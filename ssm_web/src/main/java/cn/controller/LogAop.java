package cn.controller;

import cn.bean.SysLog;
import cn.service.SysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    private Date visitTime;
    private Class clazz;
    private Method method;
    private String url;

    //前置通知，主要获取开始时间，执行的类和执行的方法
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private HttpServletRequest request;
    @Before("execution(* cn.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime =new Date();
        clazz =jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        if (args==null&args.length==0) {
            method = clazz.getMethod(methodName);
        }else {
            Class[] classArgs =new Class[args.length];
            for (int i = 0; i <args.length ; i++) {
                classArgs[i] =args[i].getClass();
            }
            clazz.getMethod(methodName,classArgs);
        }
    }
    @After("execution(* cn.controller.*.*(..))")
    public void doAfter(JoinPoint jp){
         long time =new Date().getTime() -visitTime.getTime();
         if (clazz!=null&&method!=null&&clazz!=LogAop.class){
             RequestMapping clazzAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
             if (clazzAnnotation!=null){
                 String[] clazzValue =clazzAnnotation.value();
                 RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                 String[] methodValue = methodAnnotation.value();
                 url =clazzValue[0]+methodValue[0];
             }

         }
         String ip =request.getRemoteAddr();
         //从上下文中获取当前登录操作用户
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();
        /*通过session域获取当前用户*/
        //Object spring_security_context = request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        SysLog syslog =new SysLog();
        syslog.getExecutionTime(time);
        syslog.setUrl(url);
        syslog.setUsername(username);
        syslog.setIp(ip);
        syslog.setVisitTime(visitTime);
        syslog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
        sysLogService.save(syslog);
    }

}
