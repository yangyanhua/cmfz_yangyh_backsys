package com.baizhi.yangyh.aspects;

import com.baizhi.yangyh.annotation.LogAnnotation;
import com.baizhi.yangyh.annotation.LogParam;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component  //代表当前对象由spring工厂创建
public class MyAspects {
    //@Before("execution(* com.baizhi.yuxb.service.impl.*.*(..))")
    public String sayHello(JoinPoint joinPoint){
        //怎么获取目标方法的值?
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }
        System.out.println("////////");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();//方法签名对象
        Method method = signature.getMethod();
        System.out.println(method);
        System.out.println("////////");
      /*  Object target = joinPoint.getTarget();
        System.out.println(target);
        System.out.println("////////");
        System.out.println(joinPoint.getThis());*/
        return null;
    }
    @Pointcut("")
    public void pc(){}
    @Around("@annotation(com.baizhi.yangyh.annotation.LogAnnotation)")
    public String logMyLog(ProceedingJoinPoint proceedingJoinPoint){
        String result = "管理员 ";
        //记录 谁 在什么时间 做了什么事  这件事的执行结果(成功|失败)
        //获取谁  管理员  session  在通知里面怎么获取session
        ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = requestAttributes.getRequest().getSession();
        String userName = (String) session.getAttribute("user");
        result += userName+",在";
        //在什么时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        String format = simpleDateFormat.format(date);
        result += format+"时间,做了";
        //做了什么事   调用了什么方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //让我们的方法名便于理解
        //拿当前方法的参数
        //获取你注解的值
        Method method = signature.getMethod();
        Object[] args = proceedingJoinPoint.getArgs();
        System.out.println(method);
        LogAnnotation log = method.getAnnotation(LogAnnotation.class);
        String methodName = log.methodName();
        LogParam[] parameters = log.parameters();
        result += methodName+"操作,参数为:";
        if(args != null){
            if(args.length>0){
                for (int i = 0; i < parameters.length; i++) {
                    LogParam parameter = parameters[i];
                    //中文名+值
                    String paramValue=String.valueOf(args[i]);
                    result += parameters[i].value()+",值为:"+paramValue+" ";
                }
            }
        }
        result += ".结果为:";
        //这件事的执行结果
        try {
            Object proceed = proceedingJoinPoint.proceed();
            result += "执行成功";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            result += "执行失败";
        }finally {
            //把日志记录  存入数据库   日志表  id  操作者 操作时间  执行方法 执行结果
            System.out.println(result);
            return null;
        }
    }
}
