package com.zc.mybackcrud.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zc.mybackcrud.mapper.EmpExprMapper;
import com.zc.mybackcrud.mapper.EmpMapper;
import com.zc.mybackcrud.pojo.Emp;
import com.zc.mybackcrud.pojo.EmpExpr;
import com.zc.mybackcrud.pojo.EmpLog;
import com.zc.mybackcrud.pojo.PageResult;
import com.zc.mybackcrud.service.EmpLogService;
import com.zc.mybackcrud.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult findAll(Integer pageNo, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
        // Long count = empMapper.getCount();
        // pageNo = (pageNo - 1) * pageSize;
        // 使用分页工具
        PageHelper.startPage(pageNo, pageSize);
        Page<Emp> page = (Page<Emp>) empMapper.findAll(name, gender, begin, end);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Transactional(rollbackFor = Exception.class) // 出现所有异常都回滚
    @Override
    public void deleteById(String ids) {
        Integer[] array = Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .toArray(Integer[]::new);
        empMapper.deleteById(array);
        empExprMapper.deleteById(array);
    }

    @Transactional // 自动配置事务，确保插入操作的原子性
    @Override
    public void insertEmp(Emp emp) {
        try {
            // 插入员工信息
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
        } finally {
            // 无论失败还是成功都记录日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "操作了员工插入数据");
            empLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateEmp(Emp emp) {
        // 修改员工信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateEmp(emp);

        // 修改工作经历
        List<EmpExpr> list = emp.getExprList();
        if (!list.isEmpty()){
            Integer id = emp.getId();
            empExprMapper.deleteById(new Integer[] {id});
            // 插入工作经历
            list.forEach(expr -> {
                expr.setEmpId(emp.getId());
                expr.setCreateTime(LocalDateTime.now());
                expr.setUpdateTime(LocalDateTime.now());
            });
            empExprMapper.add(list);
        }


    }

    @Override
    public Emp findById(Integer id) {
        // 查询员工
        Emp emp = empMapper.findById(id);
        // 查询员工经历
        List<EmpExpr> empExprList = empExprMapper.findById(new Integer[] {id});
        if (!empExprList.isEmpty()){
            emp.setExprList(empExprList);
        }
        return emp;
    }

    @Override
    public Emp findByExp(Integer id) {
        return empMapper.findByIdExpr(id);
    }
}
