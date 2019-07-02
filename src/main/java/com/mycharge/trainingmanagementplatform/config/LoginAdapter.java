package com.mycharge.trainingmanagementplatform.config;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.mycharge.trainingmanagementplatform.service.LoginInterceptor;


@Configuration
public class LoginAdapter extends WebMvcConfigurerAdapter {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/home");
        super.addInterceptors(registry);
    }

//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//
//        super.addViewControllers(registry);
//    }
}
