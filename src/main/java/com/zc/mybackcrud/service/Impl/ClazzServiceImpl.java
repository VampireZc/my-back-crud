package com.zc.mybackcrud.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zc.mybackcrud.mapper.ClazzMapper;
import com.zc.mybackcrud.pojo.Clazz;
import com.zc.mybackcrud.pojo.Emp;
import com.zc.mybackcrud.pojo.PageResult;
import com.zc.mybackcrud.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult findAll(String name, String begin, String end, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Clazz> list = clazzMapper.findAll(name, begin, end);
        list.forEach(clazz -> {
            String status =
                    LocalDate.now().isAfter(clazz.getEndDate()) ?
                            "已结束" : LocalDate.now().isBefore(clazz.getBeginDate()) ? "未开始" : "进行中";
            clazz.setStatus(status);
        });

        Page p = (Page<Clazz>) list;
        return new PageResult(p.getTotal(), p.getResult());
    }

    @Override
    public void deleteClazz(Integer id) {
        // 删除班级需要先删除该班级下的所有学生
        clazzMapper.deleteStu(id);
        clazzMapper.deleteClazz(id);
    }

    @Override
    public void addClazz(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.addClazz(clazz);
    }

    @Override
    public Clazz findById(Integer id) {
        return clazzMapper.findById(id);
    }

    @Override
    public void updateClazz(Clazz clazz) {
        // 更新修改时间
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateClazz(clazz);
    }
}
