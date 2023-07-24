package com.softmen.filter;

import com.alibaba.fastjson.JSONObject;
import com.softmen.pojo.Result;
import com.softmen.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //        获取请求URL
        String url = request.getRequestURL().toString();
        log.info("请求url:{}", url);
//        判断url
        if (url.contains("login")) {
            log.info("放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
//        获取请求令牌
        String jwt = request.getHeader("token");


//        判断令牌
        if (!StringUtils.hasLength(jwt)) {
            log.info("请求token为空,返回错误信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换json-->阿里巴巴 fastJSON
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return;


        }
//        解析token
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {//解析失败
            e.printStackTrace();
            log.info("解析令牌失败,返回错误信息:");
            Result error = Result.error("NOT_LOGIN");
            //手动转换json-->阿里巴巴 fastJSON
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return;

        }

//        放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
