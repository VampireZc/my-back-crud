package com.zc.mybackcrud.mapper;

import com.zc.mybackcrud.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    // 新增
    public void add(List<EmpExpr> exprList);
    // 查询
    public List<EmpExpr> findById(Integer[] ids);
    // 删除
    public void deleteById(Integer[] ids);


}
