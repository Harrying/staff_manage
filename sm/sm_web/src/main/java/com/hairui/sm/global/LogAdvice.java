package com.hairui.sm.global;

import com.hairui.sm.entity.Log;
import com.hairui.sm.entity.Staff;
import com.hairui.sm.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
@Aspect
public class LogAdvice {
    @Autowired
    private LogService logService;
    /*
    操作日志
     */
    @AfterReturning("execution(* com.hairui.sm.controller.*.*(..)) && !execution(* com.hairui.sm.controller.SelfController.*(..)) && !execution(* com.hairui.sm.controller.*.to*(..))")
    public void operationLog(JoinPoint joinPoint){
        Log log = new Log();
        //通过获取类名来获取模块
        log.setMoudle(joinPoint.getTarget().getClass().getSimpleName());
        //通过获取方法名来获取操作
        log.setOperation(joinPoint.getSignature().getName());
        //通过获取request获取session获取USER的值来获取操作员
        HttpServletRequest request =(HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("USER");
        Staff staff =(Staff)obj;
        log.setOperator(staff.getAccount());
        log.setResult("成功");
        logService.addOperationLog(log);
    }
    /*
    系统日志，出现异常进行记录
     */
    @AfterThrowing(throwing ="e",pointcut ="execution(* com.hairui.sm.controller.*.*(..)) && !execution(* com.hairui.sm.controller.SelfController.*(..))")
    public void systemLog(JoinPoint joinPoint,Throwable e){
        Log log = new Log();
        log.setMoudle(joinPoint.getTarget().getClass().getSimpleName());
        log.setOperation(joinPoint.getSignature().getName());
        HttpServletRequest request =(HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("USER");
        Staff staff =(Staff)obj;
        log.setOperator(staff.getAccount());
        log.setResult(e.getClass().getSimpleName());
        logService.addSystemLog(log);
    }
    /*
    登陆日志
     */
    @After("execution(* com.hairui.sm.controller.SelfController.login(..))")
    public void loginLog(JoinPoint joinPoint){
        log(joinPoint);
    }
    /*
    * 退出日志
    */
    @Before("execution(* com.hairui.sm.controller.SelfController.logout(..))")
    public void logoutLog(JoinPoint joinPoint){
        log(joinPoint);
    }
    private void log(JoinPoint joinPoint){
        Log log = new Log();
        log.setMoudle(joinPoint.getTarget().getClass().getSimpleName());
        log.setOperation(joinPoint.getSignature().getName());
        HttpServletRequest request =(HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        Object obj = session.getAttribute("USER");
        if(obj==null){
            log.setOperator(request.getParameter("account"));
            log.setResult("失败");
        }else {
            Staff staff = (Staff) obj;
            log.setOperator(staff.getAccount());
            log.setResult("成功");
        }
        logService.addLoginLog(log);
    }
}
