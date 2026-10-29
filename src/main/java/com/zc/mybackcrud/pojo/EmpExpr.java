package com.zc.mybackcrud.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) // 多余字段直接忽略
public class EmpExpr {
    private Integer id;
    private Integer empId;
    @JsonProperty("begin") // 解决传递参数名不匹配
    private LocalDate exprStart;
    @JsonProperty("end")
    private LocalDate exprEnd;
    @JsonProperty("company")
    private String exprCompany;
    @JsonProperty("job")
    private String exprPosition;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
