package com.zc.mybackcrud.Aop;

import com.zc.mybackcrud.anno.LogOperation;
import com.zc.mybackcrud.mapper.OperateLogMapper;
import com.zc.mybackcrud.pojo.OperateLog;
import com.zc.mybackcrud.untils.CurrentHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(loger)")
    public Object around(ProceedingJoinPoint pjp, LogOperation loger) throws Throwable {
        // 记录开始时间
        long startTime = System.currentTimeMillis();
        // 执行方法
        Object result = pjp.proceed();
        // 当前时间
        long endTime = System.currentTimeMillis();
        // 耗时
        long costTime = endTime - startTime;

        // 构建日志对象
        OperateLog op = new OperateLog();
        op.setOperateEmpId(getCurrentUserId());
        op.setOperateTime(LocalDateTime.now());
        op.setClassName(pjp.getTarget().getClass().getName());
        op.setMethodName(pjp.getSignature().getName());
        op.setMethodParams(Arrays.toString(pjp.getArgs()));
        op.setReturnValue(result!=null ? result.toString():"void");
        op.setCostTime(costTime);

        operateLogMapper.insert(op);
        return  result;
    }

    // 获取当前用户ID：从当前请求线程的 token 请求头中解析登录时写入的 id claim
    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
    }
}
