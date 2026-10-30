package com.zc.mybackcrud.controller;

import com.zc.mybackcrud.pojo.Result;
import com.zc.mybackcrud.untils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;

    @PostMapping("/upload")
    public Result upload(MultipartFile file){
        if (!file.isEmpty()){
            String filename = file.getOriginalFilename();
            String extName = filename.substring(filename.lastIndexOf("."));
            String name = UUID.randomUUID().toString().replace("-", "") + extName;
            try {
                // 上传文件到OSS
                String url = aliyunOSSOperator.upload(file.getBytes(), name);
                return Result.success(url);
            } catch (Exception e) {
                e.printStackTrace();
                return Result.error("上传失败");

            }
        }
        return Result.error("上传失败");
    }

}
