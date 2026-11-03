package com.zc.mybackcrud.filter;

import com.zc.mybackcrud.untils.TokenUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@WebFilter("/*")
@Slf4j
public class FilterImpl implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("filter，初始化方法执行");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 是登录的时候生成token 需要放行
        if (httpRequest.getRequestURI().equals("/login")) {
            log.info("filter，登录请求，放行");
            chain.doFilter(request, response);
            return;
        }

        // 不存在返回401
        String token = httpRequest.getHeader("token");
        if (token == null || token.isEmpty()) {
            log.info("filter，token 为空，拦截");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }


        // 校验token
        try {
            TokenUtils.parseToken(token);
        } catch (Exception e) {
            log.info("filter，token 校验失败");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        log.info("filter，token 校验通过");
        // 放行
        chain.doFilter(request, response);

        log.info("filter，请求放行之后执行了");

    }

    @Override
    public void destroy() {
        log.info("filter，销毁方法执行");
    }
}
