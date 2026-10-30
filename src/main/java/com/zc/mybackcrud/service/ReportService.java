package com.zc.mybackcrud.service;

import com.zc.mybackcrud.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    // 统计职位信息
   public JobOption getJobReport();

   // 性别统计
    public List<Map<String, Object>> getSexReport();
}
