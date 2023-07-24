package com.softmen.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect//AOP类
public class TimeAspect {
    @Around("execution(* com.softmen.service.*.*(..))")

    public Object recordTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //    记录开始时间
        long begin = System.currentTimeMillis();
//        调用原始方法
        Object result = proceedingJoinPoint.proceed();
//        记录结束时间,执行计算方法
        long end = System.currentTimeMillis();
        log.info(proceedingJoinPoint.getSignature() + "执行耗时:{}ms", end - begin);
        return result;
    }
}
