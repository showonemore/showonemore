package com.jiuye.utils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UpLoadUtils {
    public static String upload(HttpServletRequest request) throws Exception {
        //获取上传的资源
        Part part = request.getPart("file");
        //获取文件名
        String fileName = part.getSubmittedFileName();
        //重命名文件名
        fileName= UUID.randomUUID()+fileName;
        File file = new File("d:/upload");
        //判断D盘上是否有upload文件夹
        if (file!=null){
            //没有文件夹就创建文件夹
            file.mkdir();
        }
        //获取写入文件的地址
        String path =file+"/"+fileName;
        //将资源写入该地址
        part.write(path);
        return fileName;
    }
}
