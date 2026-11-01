package com.zc.mybackcrud.pojo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Student {
    private Integer id;
    private String name;
    private String no;
    private Integer gender;
    private String phone;
    private String idCard;
    private String isCollege;
    private String address;
    private LocalDate graduationDate;
    private Integer clazzId;
    private Short violationCount;
    private Short violationScore;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    private String clazzName; // 班级名称
}
