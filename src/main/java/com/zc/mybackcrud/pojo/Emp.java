package com.zc.mybackcrud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Integer id; //
    private String username; // 用户名
    private String password; // 密码
    private String name; // 姓名
    private Integer gender; // 性别
    private String phone; // 电话
    private Integer job; // 职位
    private Integer salary; // 工资
    private String image; // 图像
    private LocalDate entryDate; // 入职日期
    private Integer deptId; // 部门ID
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间

    // 封装部门名称数
    private String deptName;

    // 封装员工经历
    private List<EmpExpr> exprList;

}
