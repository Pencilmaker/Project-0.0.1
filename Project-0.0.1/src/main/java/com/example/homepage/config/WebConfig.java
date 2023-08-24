package com.example.homepage.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.homepage.interceptor.LogInterceptor;
import com.example.homepage.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
   // 인터셉터 등록
   private String[] excludePaths = {"/", "/login", "/join", "/member/login", "/member/join"};
   
   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      // 인터셉터 등록
      registry.addInterceptor(new LogInterceptor())
      // 인터셉터의 호출 순서 등록
      .order(1)
      // 인터셉트를 적용할 URL 패턴을 지정
      .addPathPatterns("/**")
      // 인터셉터를 제외할 URL 패턴을 지정
      .excludePathPatterns("/css/**", "/*.ico", "/error");
      
      registry.addInterceptor(new LoginCheckInterceptor())
      .order(2)
      .addPathPatterns("/**")
      .excludePathPatterns(excludePaths);
   }
   
   
}