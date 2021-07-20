package org.yg.memo.security.filter;

import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component // Config 에 추가하지 않아도 된다.
@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORSFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("---------------CORS FILTER---------------");
        response.setHeader("Access-Contol-Allow-Origin", "*");
        response.setHeader("Access-Contol-Allow-Credential", "true");
        response.setHeader("Access-Contol-Allow-Methods", "*");
        response.setHeader("Access-Contol-Allow-Max-Age", "3600");
        response.setHeader("Access-Contol-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");
        //대소문자 구별 안함
        if ("OPTION".equalsIgnoreCase(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
