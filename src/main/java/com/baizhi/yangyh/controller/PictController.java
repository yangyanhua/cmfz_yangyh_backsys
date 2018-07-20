package com.baizhi.yangyh.controller;

import com.baizhi.yangyh.entity.Pict;
import com.baizhi.yangyh.service.PictService;
import com.baizhi.yangyh.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: Administrator
 * \* Date: 2018/7/11
 * \* Time: 12:25
 * \* To change this template use File | Settings | File Templates.
 * \* Description: 轮播图控制层
 * \
 */
@Controller
@RequestMapping("/pict")
public class PictController {
    @Autowired
    private PictService pictService;

    /**
     * 分页查询所有轮播图信息
     * @return
     */
    @RequestMapping(value = "/queryAllPict.do")
    @ResponseBody
    public Map<String,Object> queryAllPict(Model model, HttpSession session,Integer page,Integer rows){
        List<Pict> queryAllPict = pictService.queryAllPict(page,rows);//传递当前页和每页需要遍历的条数
        Integer totopage = pictService.myTotopage();//传递总页码
        session.setAttribute("queryAllPict",queryAllPict);//将集合存入session中
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("total",totopage);//将总页码加入map中
        map.put("rows",queryAllPict);//集合也放入map中
        System.out.println("map =     "+map);
        return map;
    }

    /**
     * 添加轮播图信息
     * @param
     * @return
     */
    /*@RequestMapping("/addPict")
    public String addPict(Pict pict){
        pictService.addPictService(pict);
        return "redirect:/main.jsp";
    }*/

    //将文件上传致服务器
    @RequestMapping("/uploadOneImager.do")
    @ResponseBody
    public String  uploadOneImager(MultipartFile srcFile,
                                   HttpSession session) throws IOException {
        System.out.println("我是文件名"+srcFile);
        //将文件上传到服务器
       String filename = FileUpload.upFile(srcFile,session);
       return filename;
    }

    //添加轮播信息
    @RequestMapping("/addPict.do")
    @ResponseBody
    public String addPict(String  srcFiles,Pict pict){
        System.out.println("我是文件名"+srcFiles);
        pict.setPict("/img/"+srcFiles);
        //将图片保存到数据库
        pictService.addPictService(pict);
        return "upload success";
    }

    /**
     * 第一次点击修改状态
     * @param pict
     */
    @RequestMapping("/updatePict.do")
    public void updatePict(Pict pict){
        pictService.updatePictService(pict);
    }

    /**
     * 第二次点击删除
     * @param pid
     */
    @RequestMapping(value = "/deletePict.do")
    @ResponseBody
    public String deletePict(Integer pid){
        System.out.println("进入删除程序++++++++++++++++");
        pictService.removePict(pid);
        System.out.println(pid);
        System.out.println("进入删除程序2222++++++++++++++++");
        return "success";
    }
}


