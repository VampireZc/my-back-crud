package com.zc.mybackcrud.mapper;

import com.zc.mybackcrud.pojo.EmpLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpLogMapper {
    @Insert("insert into emp_log (create_time, info) values (#{createTime}, #{info})")
    void insert(EmpLog empLog);
}