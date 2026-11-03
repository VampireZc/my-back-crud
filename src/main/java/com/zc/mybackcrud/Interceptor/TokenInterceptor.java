package com.zc.mybackcrud.Interceptor;

import com.zc.mybackcrud.untils.CurrentHolder;
import com.zc.mybackcrud.untils.TokenUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 是登录的时候生成token 需要放行
        if (request.getRequestURI().equals("/login")) {
            log.info("interceptor，登录请求，放行");
            return true;
        }

        // 不存在返回401
        String token = request.getHeader("token");
        if (token == null || token.isEmpty()) {
            log.info("interceptor，token为空，拦截");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }


        // 校验token
        try {
            Claims claims = TokenUtils.parseToken(token);
            // 设置当前用户id到线程本地变量
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
        } catch (Exception e) {
            log.info("interceptor，token 校验失败");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        log.info("interceptor，token 校验通过");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        // 请求处理完毕后再清理
        CurrentHolder.remove();
    }
}
