//package com.mycharge.trainingmanagementplatform.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import com.mycharge.trainingmanagementplatform.component.LoginInterceptor;
//
//@Configuration
//public class LoginAdapter implements WebMvcConfigurer {
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/indexhome",
//                "/login","/register","/js/*","/images/*","/css/*","/webfonts/*");
//    }
//
//}