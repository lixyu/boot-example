package cn.lee.boot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author ：lix492
 * 2021/4/28
 */
@Slf4j
@Aspect
@Component
public class ControllerInterceptor {
    @Pointcut("execution(* cn.lee.boot.web.*.*(..))")
    public void entranceAspect() {

    }

    @Around("entranceAspect()")
    public Object handleController(ProceedingJoinPoint proPoint) throws Throwable {
        /** *  获取请求类，方法，参数 */
        String className = proPoint.getSignature().getDeclaringTypeName();
        String methodName = proPoint.getSignature().getName();
        String strParam = Arrays.toString(proPoint.getArgs());
        proPoint.getSignature();
        System.out.println();

        Object obj = proPoint.proceed();
        return obj;
    }

}
