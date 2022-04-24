package com.yg.api.common.filter;


import lombok.extern.log4j.Log4j2;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

@Component
@Order(1)
@Log4j2
public class HeaderManipulateFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        log.info("Filter {} ", req.getRequestURI());
        filterChain.doFilter(new AddParamsToHeader((HttpServletRequest) servletRequest, "KEY", "book"), servletResponse);

    }
}
