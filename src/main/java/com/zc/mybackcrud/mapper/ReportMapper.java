package com.zc.mybackcrud.mapper;

import com.zc.mybackcrud.pojo.JobOption;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReportMapper {

    public List<Map<String, Object>> getJobReport();

    public List<Map<String, Object>> getSexReport();
}
