package com.zc.mybackcrud.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zc.mybackcrud.mapper.EmpExprMapper;
import com.zc.mybackcrud.mapper.EmpMapper;
import com.zc.mybackcrud.pojo.Emp;
import com.zc.mybackcrud.pojo.EmpExpr;
import com.zc.mybackcrud.pojo.PageResult;
import com.zc.mybackcrud.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Override
    public PageResult findAll(Integer pageNo, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        // Long count = empMapper.getCount();
        // pageNo = (pageNo - 1) * pageSize;
        // 使用分页工具
        PageHelper.startPage(pageNo, pageSize);
        Page<Emp> page = (Page<Emp>) empMapper.findAll(name, gender, begin, end);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void deleteById(String ids) {
        Integer[] array = Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        empMapper.deleteById(array);
        empExprMapper.deleteById(array);
    }

    @Override
    public void insertEmp(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insertEmp(emp);
        // 插入工作经历
        List<EmpExpr> list = emp.getExprList();
        if (!list.isEmpty()) {
            list.forEach(expr -> {
                expr.setEmpId(emp.getId());
                expr.setCreateTime(LocalDateTime.now());
                expr.setUpdateTime(LocalDateTime.now());
            });
            // 插入员工信息
            empExprMapper.add(list);

        }
    }
}
