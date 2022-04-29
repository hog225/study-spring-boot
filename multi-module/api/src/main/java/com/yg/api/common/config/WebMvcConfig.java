package com.yg.api.common.config;

import com.yg.api.common.interceptor.CheckHeaderInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
    private final CheckHeaderInterceptor checkHeaderInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkHeaderInterceptor)
                .addPathPatterns("/books/**")
                .order(Ordered.HIGHEST_PRECEDENCE);
    }
}
