package com.zc.mybackcrud.service;

import com.zc.mybackcrud.pojo.Dept;
import com.zc.mybackcrud.pojo.PageResult;

import java.util.List;

public interface DeptService {

    // 查询所有部门
    public PageResult findAll(Integer page, Integer pageSize);

    // 根据ID删除
    public Integer deleteById(Integer id);

    // 新增部门
    public void save(Dept dept);

    // 修改
    public void update(Dept dept);

    // 根据id查询name
    public Dept getById(Integer id);

    // 根据name查询
    public List<Dept> findByName(String name);
}
