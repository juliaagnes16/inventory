package com.example.demo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Log4j2
public class LoggingInterceptor implements HandlerInterceptor {

    public LoggingInterceptor() {
        log.info("LoggingInterceptor constructed!");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("[REQUEST] {} {} - Params: {}", request.getMethod(), request.getRequestURI(), request.getParameterMap());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
        log.info("[RESPONSE] {} {} - Status: {}", request.getMethod(), request.getRequestURI(), response.getStatus());
    }
}
