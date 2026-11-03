package com.zc.mybackcrud.controller;

import com.zc.mybackcrud.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
public class UploadControllerLocal {

    private static final String UPLOAD_DIR = "D:/upload/";

    @PostMapping("/upload2")
    public Result upload(MultipartFile file) throws IOException {
        // 获取文件路径
        String filename = file.getOriginalFilename();
        String substring = null;// 获取文件后缀
        if (filename != null) {
            substring = filename.substring(filename.lastIndexOf("."));
        }
        String name = UUID.randomUUID().toString().replace("-","") + substring;
        File targetFile = new File(UPLOAD_DIR + name);

        if (!targetFile.getParentFile().exists()){ // 不存在则创建
            targetFile.getParentFile().mkdirs();
        }

        file.transferTo(targetFile); // 保存文件到指定路径
        // 保存文件到指定路径
        return  Result.success("上传成功");
    }
}
