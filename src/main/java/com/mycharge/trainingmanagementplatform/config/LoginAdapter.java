package com.mycharge.trainingmanagementplatform.config;

import com.mycharge.trainingmanagementplatform.component.AdminLoginInterceptor;
import com.mycharge.trainingmanagementplatform.component.StudentLoginInterceptor;
import com.mycharge.trainingmanagementplatform.component.TeacherLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginAdapter implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminLoginInterceptor()).addPathPatterns("/Admin*");
//                .excludePathPatterns("/IndexHome",
//                "/login","/register","/js/*","/images/*","/css/*","/webfonts/*");
        registry.addInterceptor(new TeacherLoginInterceptor()).addPathPatterns("/Teacher*");
        registry.addInterceptor(new StudentLoginInterceptor()).addPathPatterns("/Stu*");

    }

}