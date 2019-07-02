package com.mycharge.trainingmanagementplatform.mapper;

import com.mycharge.trainingmanagementplatform.model.MyObject;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface AMapper {
    public int save(MyObject myObject) throws Exception;

    public List<MyObject> findByAname(MyObject myObject) throws Exception;
}
