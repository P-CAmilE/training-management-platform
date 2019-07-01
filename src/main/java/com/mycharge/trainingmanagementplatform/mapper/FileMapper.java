package com.mycharge.trainingmanagementplatform.mapper;

import com.mycharge.trainingmanagementplatform.model.MyFile;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface FileMapper {
    //插入一行
    public void upload(MyFile myFile);


}
