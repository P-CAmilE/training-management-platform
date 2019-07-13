package com.mycharge.trainingmanagementplatform;

import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@SpringBootApplication
@MapperScan(basePackages = "com.mycharge.trainingmanagementplatform.mapper")
public class TrainingManagementPlatformApplication {

    public static void main(String[] args) {

        SpringApplication.run(TrainingManagementPlatformApplication.class, args);
    }

}
