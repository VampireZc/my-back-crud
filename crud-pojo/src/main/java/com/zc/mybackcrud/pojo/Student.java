package com.zc.mybackcrud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private String no;
    private String gender;
    private String phone;
    private String idCard;
    private String isCollege;
    private String address;
    private LocalDate graduationDate;
    private Integer clazzId;
    private String degree;
    private Short violationCount;
    private Short violationScore;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String clazzName; // 班级名称
}
