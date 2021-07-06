package com.githup.icezerocat.study.aop.intercept;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * Description: 拦截面
 * CreateDate:  2021/6/24 13:54
 *
 * @author zero
 * @version 1.0
 */
@Slf4j
@Aspect
@Component
public class AopAspect {

    @Before("execution(* com.githup.icezerocat.study.aop.service.AopService.say(..))")
    public void beforMethod(JoinPoint point) {
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        System.out.println("调用前连接点方法为：" + methodName + ",参数为：" + args);
    }

    @Pointcut("execution(* com.githup.icezerocat.study.aop.service.AopService.say(..))")
    private void iService() {
    }

    @Around("iService()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //方法参数
        String args = Arrays.toString(joinPoint.getArgs());
        args = args.replaceFirst("\\[", "(").substring(0, args.length() - 1) + ")";

        // 获取目标类名称
        String clazzName = joinPoint.getTarget().getClass().getName();

        // 获取目标类方法名称
        String methodName = joinPoint.getSignature().getName();

        log.debug("拦截123: {}:{}", methodName, args);
        return joinPoint.proceed(joinPoint.getArgs());
    }
}
