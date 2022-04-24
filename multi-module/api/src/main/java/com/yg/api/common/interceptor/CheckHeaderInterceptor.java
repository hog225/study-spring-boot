package com.yg.api.common.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Component
@Log4j2
public class CheckHeaderInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String val = request.getHeader("KEY");
        List<String> headerNames = new ArrayList();
        if (val != "book") {
            log.error("invalid header");
            throw new IllegalArgumentException();
        }

        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String headerName = enumeration.nextElement();
            if (!StringUtils.hasText(headerName))
                break;
            headerNames.add(headerName);
        }
        log.info("valid header header names {} ", headerNames);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
