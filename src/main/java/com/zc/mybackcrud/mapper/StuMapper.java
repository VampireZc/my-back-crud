package com.zc.mybackcrud.mapper;

import com.zc.mybackcrud.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface StuMapper {
    public List<Student> getStus(
            @Param("name") String name,
            @Param("degree") Integer degree,
            @Param("clazzId") Integer clazzId
    );

    public void insert(Student student);

    public void update(Student student);

    public void delete(Integer id);

    public Student getStu(Integer id);

    public void addViolation(Integer id, Integer score, LocalDateTime updateTime);
}
