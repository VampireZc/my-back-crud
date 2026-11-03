package com.zc.mybackcrud.service;

import com.zc.mybackcrud.pojo.PageResult;
import com.zc.mybackcrud.pojo.Student;

import java.util.List;

public interface StuService {
    // 查询所有学生
    public PageResult getStus(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

    // 新增学生
    public void save(Student student);

    // 修改学生
    public void update(Student student);

    // 删除学生
    public void delete(Integer id);

    // 根据id查询学生
    public Student getStu(Integer id);

    // 添加违规记录
    public void addViolation(Integer id, Integer score);
}
