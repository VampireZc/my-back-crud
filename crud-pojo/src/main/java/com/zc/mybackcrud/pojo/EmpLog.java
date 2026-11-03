package com.zc.mybackcrud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpLog {
    private Integer id;
    private LocalDateTime createTime;
    private String info;
}
