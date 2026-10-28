package com.zc.mybackcrud.mapper;

import com.zc.mybackcrud.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select id,name,create_time, update_time from dept order by create_time desc ")
    public List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    public Integer deleteById(Integer id);

    @Insert("insert into dept (name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    public void save(Dept dept);
}
