package com.mycharge.trainingmanagementplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.mycharge.trainingmanagementplatform.mapper")
public class TrainingManagementPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrainingManagementPlatformApplication.class, args);
    }

}
