package com.mycharge.trainingmanagementplatform.mapper;

import com.mycharge.trainingmanagementplatform.model.MyObject;
import org.apache.ibatis.annotations.Mapper;

public interface FileMapper {

    public int upload(MyObject myObject);

}
