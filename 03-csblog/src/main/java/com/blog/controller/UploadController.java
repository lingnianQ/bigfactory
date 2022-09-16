package com.blog.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UploadController {

    private String fileDir="d:/file";

    /**
     * @RequestPart 用于描述MultipartFile类型，并可以
     * 指定客户端要传递的属性名是什么
     * @param picFile
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public String doUpload(@RequestPart("picFile") MultipartFile picFile) throws IOException {//a.jpg
        //1.为文件重命名
        String originalFilename=picFile.getOriginalFilename();
        String newFileName=UUID.randomUUID()+
                originalFilename.substring(originalFilename.lastIndexOf("."));
        //2.设计文件上传目录
        File file=new File(fileDir);
        if(!file.exists())file.mkdirs();
        //3.将文件上传到服务器
        picFile.transferTo(new File(file,newFileName));
        //4.返回文件路径
        return "/"+newFileName;
    }
    //delete http://localhost/remove?name=873d2b4f-ef7b-4660-ad29-5586962958f1.jpg
    @RequestMapping("/remove")
    public String doRemove(String name){
        //String filePath = filePath+name;
        File file=new File(fileDir,name);
        if(file.exists()){
            file.delete();//删除文件
            return "delete ok";
        }
        return "file is not exists";
    }

}
