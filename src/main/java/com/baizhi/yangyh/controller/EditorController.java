package com.baizhi.yangyh.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/editor")
public class EditorController {
    @RequestMapping("/add")
    public JSONObject add(MultipartFile file, HttpServletRequest request) throws UnknownHostException {
        //{"error":0,"url":"\/ke4\/attached\/W020091124524510014093.jpg"}
        JSONObject obj=new JSONObject();

        //图片路径?
        //http://localhost:8989/cmfz_yuxb_backsys/upload/sss.jpg
        String fileName = file.getOriginalFilename();//美女.jpg      ------        asdfasdfwqaerf.jpg
        String ext = FilenameUtils.getExtension(fileName);
        String newFileName = UUID.randomUUID().toString()+ "."+ext;
        String scheme = request.getScheme();//协议名  http
        //当前服务器ip地址
        String localHost = InetAddress.getLocalHost().getHostAddress();//
        String serverName = request.getServerName();//服务器名  localhost
        int serverPort = request.getServerPort();//端口   8989
        String contextPath = request.getContextPath();
//实际服务器的路径
        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        File imgFile = new File(realPath + "/" + newFileName);
        //拼凑图片网络位置
        String netAddr=scheme+"://"+localHost+":"+serverPort+contextPath+"/upload/"+newFileName;
        try {
            //文件上传
            file.transferTo(imgFile);
            obj.put("error",0);
            obj.put("url",netAddr);
        } catch (IOException e) {
            obj.put("error",1);
            obj.put("url",netAddr);
        }
        return obj;
    }
    @RequestMapping("/browser")
    public JSONObject browser(HttpServletRequest request) throws UnknownHostException {
        /*
        *  {
	"moveup_dir_path": "",
	"current_dir_path": "",
	"current_url": "\/ke4\/php\/..\/attached\/",
	"total_count": 5,
	"file_list": [{
		"is_dir": false,
		"has_file": false,
		"filesize": 208736,
		"dir_path": "",
		"is_photo": true,
		"filetype": "jpg",
		"filename": "1241601537255682809.jpg",
		"datetime": "2018-06-06 00:36:39"
	}
        * */
        JSONObject jsonObject = new JSONObject();
        //找到服务器上的文件夹的网络位置
        String scheme = request.getScheme();//协议名  http
        //当前服务器ip地址
        String localHost = InetAddress.getLocalHost().getHostAddress();//
        String serverName = request.getServerName();//服务器名  localhost
        int serverPort = request.getServerPort();//端口   8989
        String contextPath = request.getContextPath();
        String netAddr=scheme+"://"+localHost+":"+serverPort+contextPath+"/upload/";
        jsonObject.put("current_url",netAddr);
        //获取文件夹的服务器地址
        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        File uploads = new File(realPath);
        String[] names = uploads.list();//获取当前文件夹的所有文件名
        jsonObject.put("total_count",names.length);
        ArrayList<Object> list = new ArrayList<Object>();

        for (int i = 0; i < names.length; i++) {
            //往list中设置所有的图片对象
            String name = names[i];
            JSONObject image = new JSONObject();
            image.put("is_dir",false);
            image.put("has_file",false);
            File file = new File(realPath + "/" + names[i]);
            image.put("filesize",file.length());
            image.put("is_photo",true);
            image.put("filetype",FilenameUtils.getExtension(file.getName()));
            image.put("filename",name);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
            String format = dateFormat.format(new Date());
            image.put("datetime",format);
            list.add(image);
        }
        jsonObject.put("file_list",list);
        return jsonObject;
    }
    @RequestMapping("/upload")
    public void upload(String content){
        System.out.println(content);
    }
}
