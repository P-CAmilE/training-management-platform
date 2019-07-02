package com.mycharge.trainingmanagementplatform.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import com.mycharge.trainingmanagementplatform.model.*;

import java.util.List;

public interface LogMapper {

    public List<JSONObject> studentLogIn(@Param("username") String username, @Param("password") String password);

    public List<JSONObject> teacherLogIn(@Param("username") String username, @Param("password") String password);

    public List<JSONObject> companyLogIn(@Param("username") String username, @Param("password") String password);

    public int studentRegister(@Param("username") String username, @Param("password") String password, @Param("email") String email);

    public int companyRegister(@Param("username") String username, @Param("password") String password, @Param("email") String email);



}