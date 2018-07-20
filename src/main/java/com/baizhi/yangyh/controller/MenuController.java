package com.baizhi.yangyh.controller;
import com.alibaba.fastjson.JSON;
import com.baizhi.yangyh.entity.Menu;
import com.baizhi.yangyh.service.MenusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenusService menusService;
    @RequestMapping(value="/queryAll.do" )
    @ResponseBody
    public List<Menu> queryAll(HttpSession session){
        List<Menu> list = menusService.queryMenus();
        System.out.println(list);
       // session.setAttribute("list",list);
       // String menuString = JSON.toJSONString(list);
        return list;

    }
/*   @RequestMapping(value = "/queryAll.do",produces = "text/plain;charset=utf-8")
   @ResponseBody
   public String queryAll(Model model, HttpSession session){
       List<Menu> queryAll = menusService.queryMenus();
       for (Menu menu : queryAll) {
           System.out.println(queryAll);
       }
       session.setAttribute("queryAll",queryAll);
       String menuString = JSON.toJSONString(queryAll);
       System.out.println("menu.. =  "+menuString);
       return menuString;
   }*/


}
