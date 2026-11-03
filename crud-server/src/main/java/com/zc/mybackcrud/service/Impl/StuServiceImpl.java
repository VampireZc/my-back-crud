package com.zc.mybackcrud.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zc.mybackcrud.mapper.StuMapper;
import com.zc.mybackcrud.pojo.PageResult;
import com.zc.mybackcrud.pojo.Student;
import com.zc.mybackcrud.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StuServiceImpl implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Override
    public PageResult getStus(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        Page p = (Page<Student>) stuMapper.getStus(name, degree, clazzId);
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.insert(student);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.update(student);
    }

    @Override
    public void delete(Integer id) {
        stuMapper.delete(id);
    }

    @Override
    public Student getStu(Integer id) {
        return stuMapper.getStu(id);
    }

    @Override
    public void addViolation(Integer id, Integer score) {
        stuMapper.addViolation(id, score, LocalDateTime.now());
    }
}
