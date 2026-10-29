package com.zc.mybackcrud.mapper;

import com.zc.mybackcrud.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    public List<Emp> findAll(
//            @Param("pageNo") Integer pageNo,
//            @Param("pageSize") Integer pageSize,
            @Param("name") String name,
            @Param("gender") Integer gender,
            @Param("begin") LocalDate begin,
            @Param("end") LocalDate end);

    // @Select("select count(*) from emp e, dept d where e.dept_id = d.id")
    public Long getCount();

    public void deleteById(Integer[] ids);

    // 也可以把返回主键配置写在mapper里面
    // @Options(useGeneratedKeys = true, keyProperty = "id")
    public void insertEmp(Emp emp);
}
