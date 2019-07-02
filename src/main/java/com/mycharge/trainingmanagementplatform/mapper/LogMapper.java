package com.mycharge.trainingmanagementplatform.mapper;

import org.apache.ibatis.annotations.Param;
import com.mycharge.trainingmanagementplatform.model.*;

public interface LogMapper {

    public int studentLogIn(MyObject myObject);

    public int teacherLogIn(MyObject myObject);

    public int companyLogIn(MyObject myObject);

    public int studentRegister(MyObject myObject);

    public int companyRegister(MyObject myObject);
}