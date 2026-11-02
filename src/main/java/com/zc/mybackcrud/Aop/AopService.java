package com.zc.mybackcrud.Aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class AopService {
    @Around("execution(* com.zc.mybackcrud.service.Impl.DeptServiceImpl.*(..))") // 切面表达式，指定切面的范围
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable { // 统计这个实现类中所有方法的执行时间
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();// 执行原始方法
        long end = System.currentTimeMillis();
        log.info("方法{}执行耗时{}毫秒", pjp.getSignature(), end - start);
        return  result;
    }
}
