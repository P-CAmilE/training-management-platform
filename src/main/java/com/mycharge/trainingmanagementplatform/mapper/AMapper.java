package com.mycharge.trainingmanagementplatform.mapper;

import com.mycharge.trainingmanagementplatform.model.A;
import com.mycharge.trainingmanagementplatform.model.Result;
import org.apache.ibatis.annotations.*;


import java.sql.SQLException;
import java.util.List;

@Mapper
public interface AMapper {
    public int save(A a) throws SQLException;

    public List<A> findByAname(String aname) throws SQLException;
}
