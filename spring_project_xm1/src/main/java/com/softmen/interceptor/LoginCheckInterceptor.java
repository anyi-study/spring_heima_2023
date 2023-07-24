package com.softmen.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.softmen.pojo.Result;
import com.softmen.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component //IOC容器管理
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//目标资源方法运行前运行，true放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //        获取请求URL
        String url = request.getRequestURL().toString();
        log.info("请求url:{}", url);
//        判断url
        if (url.contains("login")) {
            log.info("放行");
            return true;
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
            return false;


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
            return false;
        }

//        放行
        log.info("令牌合法，放行");
        return true;
    }

    @Override//目标资源方法运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override//视图渲染完毕后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
