package com.baizhi.yangyh.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

public class FileUpload {

    public static String  upFile(MultipartFile srcFile, HttpSession session) throws IOException {
        String filename = srcFile.getOriginalFilename();
        String contentType = srcFile.getContentType();
        System.out.println("filename"+filename);
        System.out.println("contentType"+contentType);
        //将上传的文件保存到服务器
        String realPath = session.getServletContext().getRealPath("/img");
        //将上传的文件保存到服务器的指定位置
        srcFile.transferTo(new File(realPath+"/"+filename));
        return filename;
    }
}
