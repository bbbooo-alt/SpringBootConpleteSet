package com.ggb.complete_set.aspect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect//声明这是一个切面类
@Component//将类注册为spring组件
public class LogAspect {
    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);
//    这行代码定义了要拦截的方法范围
//    execution(* com.company..*.*(..))的含义：
//            *：任意返回类型
//    com.company：包名
//            ..：表示该包及其子包
//*.*：任意类中的任意方法
//            (..)：任意参数
    @Pointcut("execution(* com.ggb.complete_set..*.*(..))")
    public void logPointcut() {} // 定义切点，此时logPointcut()就是切点

    @Around("logPointcut()") //表示这是一个环绕通知，可以在方法执行前后都进行处理
    //ProceedingJoinPoint：包含了被拦截方法的信息
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.debug("开始执行方法: {}.{}", point.getTarget().getClass().getName(),
                point.getSignature().getName());
        long beginTime = System.currentTimeMillis();
        //point.proceed()：执行被拦截的方法
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;

        log.info("执行方法: {}.{}", point.getTarget().getClass().getName(),
                point.getSignature().getName());
        log.info("执行时间: {}ms", time);

        return result;
    }
}
