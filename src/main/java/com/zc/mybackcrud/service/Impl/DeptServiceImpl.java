package com.zc.mybackcrud.service.Impl;

import com.zc.mybackcrud.mapper.DeptMapper;
import com.zc.mybackcrud.pojo.Dept;
import com.zc.mybackcrud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public Integer deleteById(Integer id) {
        return deptMapper.deleteById(id);
    }

    @Override
    public void save(Dept dept) {
        // 设置创建时间和更新时间为当前时间
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.save(dept);
    }
}
