package com.zc.mybackcrud.service;

import com.zc.mybackcrud.pojo.Clazz;
import com.zc.mybackcrud.pojo.PageResult;

public interface ClazzService {
    // 查询所有的班级列表
    public PageResult findAll(String name, String begin, String end, Integer page, Integer pageSize);

    // 删除班级
    public void deleteClazz(Integer id);

    // 新增班级
    public void addClazz(Clazz clazz);

    public Clazz findById(Integer id);

    public void updateClazz(Clazz clazz);
}
