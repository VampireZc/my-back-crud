package com.zc.mybackcrud.mapper;

import com.zc.mybackcrud.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

    @Select("select id,name,create_time, update_time from dept order by create_time desc ")
    public List<Dept> findAll();

    @Delete("delete from dept where id = #{id}")
    public Integer deleteById(Integer id);

    @Insert("insert into dept (name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    public void save(Dept dept);

    @Update("update dept set name=#{name}, update_time=#{updateTime} where id = #{id}")
    public void update(Dept dept);

    @Select("select name from dept where id = #{id}")
    public Dept getById(Integer id);

    @Select("select id,name,create_time, update_time from dept where name like concat('%', #{name}, '%')")
    public List<Dept> findByName(String name);
}
