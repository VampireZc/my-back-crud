package com.zc.mybackcrud.service;

import com.zc.mybackcrud.pojo.Dept;

import java.util.List;

public interface DeptService {

    // 查询所有部门
    public List<Dept> findAll();

    // 根据ID删除
    public Integer deleteById(Integer id);

    // 新增部门
    public void save(Dept dept);
}
