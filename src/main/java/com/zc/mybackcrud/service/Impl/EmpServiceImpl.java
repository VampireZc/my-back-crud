package com.zc.mybackcrud.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zc.mybackcrud.mapper.EmpExprMapper;
import com.zc.mybackcrud.mapper.EmpMapper;
import com.zc.mybackcrud.pojo.*;
import com.zc.mybackcrud.pojo.ENUM.Subject;
import com.zc.mybackcrud.service.EmpLogService;
import com.zc.mybackcrud.service.EmpService;
import com.zc.mybackcrud.untils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public EnumClazz findSimple() {
        List<Map<String,Object>> list = empMapper.findSimple();

//        List<Map<String, Object>> mapList = list.stream()
//                .map(s-> Subject.option(String.valueOf(s.get("name")), s.get("value")))
//                .toList();
//
//        // 这里还需要构建一个 学科 的枚举 格式需和 mapList 保持一致
//        // 1:java, 2:前端, 3:大数据, 4:Python, 5:Go, 6:嵌入式
//        List<Map<String, Object>> subjectList = Arrays.asList(
//                Map.of("name", "java", "value", 1),
//                Map.of("name", "前端", "value", 2),
//                Map.of("name", "大数据", "value", 3),
//                Map.of("name", "Python", "value", 4),
//                Map.of("name", "Go", "value", 5),
//                Map.of("name", "嵌入式", "value", 6)
//        );

        // 优化写法
        List<Map<String, Object>> mapList = list.stream()
                .map(s-> Subject.option(String.valueOf(s.get("name")), s.get("id")))
                .toList();

        return new EnumClazz(mapList, Subject.options());
    }

    @Override
    public Emp loginInfo(Emp emp) {
        Emp info = empMapper.loginInfo(emp);

        if (info != null){
            // 创建一个 Map 对象，用于存储用户信息
            HashMap<String, Object> infoMap = new HashMap<>();
            infoMap.put("id", info.getId());
            infoMap.put("username", info.getUsername());

            String token = TokenUtils.generateToken(infoMap);
            System.out.println("token: " + token);
            info.setToken(token);
            return info;
        }
        return null;
    }
}
