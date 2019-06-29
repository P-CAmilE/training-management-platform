package com.mycharge.trainingmanagementplatform.mapper;

import com.mycharge.trainingmanagementplatform.model.A;
import com.mycharge.trainingmanagementplatform.model.MyObject;
import com.mycharge.trainingmanagementplatform.model.Result;
import org.apache.ibatis.annotations.*;


import java.sql.SQLException;
import java.util.List;

@Mapper
public interface AMapper {
    public int save(MyObject myObject) throws Exception;

    public List<MyObject> findByAname(MyObject myObject) throws Exception;
}
