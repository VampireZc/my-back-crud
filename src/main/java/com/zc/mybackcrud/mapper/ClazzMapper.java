package com.zc.mybackcrud.mapper;

import com.zc.mybackcrud.pojo.Clazz;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClazzMapper {
    public List<Clazz> findAll(
            @Param("name") String name,
            @Param("begin") String begin,
            @Param("end") String end
    );

    public void deleteClazz(Integer id);

    public void deleteStu(Integer id);

    public void addClazz(Clazz clazz);

    public Clazz findById(Integer id);

    public void updateClazz(Clazz clazz);
}
