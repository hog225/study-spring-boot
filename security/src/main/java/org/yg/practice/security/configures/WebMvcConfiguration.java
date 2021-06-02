package org.yg.practice.security.configures;

import java.util.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {
    
    // Response body를 보지않고 View 를 할당하고 싶을때 
    // Controller 부분에서 처리
    @Bean(name="jsonView")
    public MappingJackson2JsonView mappingJackson2JsonView(){
        MappingJackson2JsonView mappingJackson2JsonView = new MappingJackson2JsonView();
        mappingJackson2JsonView.setContentType("application/json;charset=UTF-8");
        return mappingJackson2JsonView;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 아래 path로 들어오면 어디로 매핑해라 
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        WebMvcConfigurer.super.configureMessageConverters(converters);
    }
}
