package com.zc.mybackcrud.Aop;

import com.zc.mybackcrud.anno.LogOperation;
import com.zc.mybackcrud.mapper.OperateLogMapper;
import com.zc.mybackcrud.pojo.OperateLog;
import com.zc.mybackcrud.untils.CurrentHolder;
import com.zc.mybackcrud.untils.TokenUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
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

        log.info("===> 操作日志切面触发: {}", pjp.getSignature().getName());
        operateLogMapper.insert(op);
        return  result;
    }

    // 获取当前用户ID：从当前请求线程的 token 请求头中解析登录时写入的 id claim
    private Integer getCurrentUserId() {
        return CurrentHolder.getCurrentId();
        // 非 Web 请求线程（如异步、定时任务）拿不到 RequestContext
//        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (attrs == null) {
//            log.warn("无法获取当前请求上下文，操作人ID置空");
//            return null;
//        }
//        HttpServletRequest request = attrs.getRequest();
//        String token = request.getHeader("token");
//        if (token == null || token.isEmpty()) {
//            log.warn("请求头中不存在 token，无法解析操作人ID");
//            return null;
//        }
//        try {
//            Claims claims = TokenUtils.parseToken(token);
//            Object id = claims.get("id");
//            return id == null ? null : Integer.valueOf(id.toString());
//        } catch (Exception e) {
//            log.warn("解析 token 获取操作人ID失败", e);
//            return null;
//        }
    }
}
