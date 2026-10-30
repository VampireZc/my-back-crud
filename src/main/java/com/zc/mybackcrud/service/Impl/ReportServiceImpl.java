package com.zc.mybackcrud.service.Impl;

import com.zc.mybackcrud.mapper.ReportMapper;
import com.zc.mybackcrud.pojo.JobOption;
import com.zc.mybackcrud.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public JobOption getJobReport() {
        List<Map<String, Object>> list = reportMapper.getJobReport();
        System.out.println("执行了");
        List<Object> jobList = list.stream().map(s -> s.get("pos")).toList();
        List<Object> dataList = list.stream().map(s -> s.get("num")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map<String, Object>> getSexReport() {
        List<Map<String, Object>> list = reportMapper.getSexReport();
        // 现场构造需要的格式
        List<Map<String, Object>> ls = list.stream()
                .map(s -> Map.of("name", s.get("sex"), "value", s.get("number")))
                .toList();
        System.out.println(ls);
        return ls;
    }
}
