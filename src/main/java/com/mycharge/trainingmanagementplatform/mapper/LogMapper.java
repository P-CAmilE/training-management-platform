package com.mycharge.trainingmanagementplatform.mapper;

import org.apache.ibatis.annotations.Param;
import com.mycharge.trainingmanagementplatform.model.*;

public interface LogMapper {

    public Student studentLogIn(@Param("username") String username, @Param("password") String password);

    public Teacher teacherLogIn(@Param("username") String username, @Param("password") String password);

    public Company companyLogIn(@Param("username") String username, @Param("password") String password);

    public int studentRegister(@Param("username") String username, @Param("password") String password, @Param("email") String email);

    public int companyRegister(@Param("username") String username, @Param("password") String password, @Param("email") String email);
}