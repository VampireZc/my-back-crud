package com.zc.mybackcrud.service;

import com.zc.mybackcrud.pojo.Emp;
import com.zc.mybackcrud.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    // 分页查询
    public PageResult findAll(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    // 删除员工
    public void deleteById(String ids);

    // 新增员工
    public void insertEmp(Emp emp);

    // 修改员工
    public void updateEmp(Emp emp);

    public Emp findById(Integer id);

    public Emp findByExp(Integer id);
}
