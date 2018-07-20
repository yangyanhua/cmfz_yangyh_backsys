package com.baizhi.yangyh.controller;
import com.baizhi.yangyh.annotation.AnnName;
import com.baizhi.yangyh.annotation.Content;
import com.baizhi.yangyh.entity.Master;
import com.baizhi.yangyh.entity.PUser;
import com.baizhi.yangyh.entity.Usersite;
import com.baizhi.yangyh.service.MasterService;
import com.baizhi.yangyh.service.PuserService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Controller
@RequestMapping("/puser")
public class PuserController {
    @Autowired
   private PuserService puserService;
    @Autowired
    private MasterService masterService;
   /* @RequestMapping("/export")
    public void export(Integer page,Integer rows ,String content, String fields, HttpServletResponse response) throws IOException, InvocationTargetException {
        //导出excel
        HSSFWorkbook wo = new HSSFWorkbook();
        HSSFSheet usersheet = wo.createSheet("用户表");
        //建立标题行
        HSSFRow titleRow = usersheet.createRow(0);
        String[] split = content.split(",");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            HSSFCell cell = titleRow.createCell(i);
            cell.setCellValue(s);
        }
        //数据行
        //查询数据库所有用户信息
        List<PUser> list =puserService.queryAllPuser(page,rows);
        String[] mfiled = fields.split(",");
        for (int i = 1; i <= list.size(); i++) {
            HSSFRow datarow = usersheet.createRow(i);
            for (int j = 0; j < mfiled.length; j++) {
                HSSFCell cell = datarow.createCell(j);
                //通过拼凑方法名,调用反射的形式获取属性值
                //getId   getName
                String methodName = "get"+mfiled[j].substring(0,1).toUpperCase()+mfiled[j].substring(1);
                try {
                    Method declaredMethod = PUser.class.getDeclaredMethod(methodName, null);
                    Object result = declaredMethod.invoke(list.get(i - 1));
                    //判断返回值的类型
                    if(result instanceof Date){
                        //获取当前cell是第几列
                        usersheet.setColumnWidth(j,40*256);
                        //填写日期格式内容
                        DataFormat dataFormat = wo.createDataFormat();
                        short format = dataFormat.getFormat("yyyy年MM月dd日");
                        CellStyle cellStyle = wo.createCellStyle();
                        cellStyle.setAlignment(HorizontalAlignment.CENTER);
                        cellStyle.setDataFormat(format);
                        cell.setCellStyle(cellStyle);
                        //设置内容
                        cell.setCellValue((Date)result);
                    }else{
                        cell.setCellValue(result.toString());
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        //写出excel
        //设置响应头
        //文件类型
        response.setContentType("application/vnd.ms-excel");
        String fileName ="用户自定义信息表.xls";
        //当前行为方式   告诉浏览器是下载 还是 打开新窗口临时加载
        response.setHeader("content-disposition","inline;fileName="+new String(fileName.getBytes("utf-8"),"iso-8859-1"));
        wo.write(response.getOutputStream());
    }*/

    @RequestMapping("/Paging.do")
    @ResponseBody
    public Map<String,Object> Paging(Integer page, Integer rows){
        List<PUser> list = puserService.queryAllPuser(page, rows);
        Integer integer = puserService.myTotopage();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total",integer);
        map.put("rows",list);
        System.out.println(map);
        return map;
    }

  //查所有
    @RequestMapping("/findlist.do")
    public List<PUser> findlist(){
        System.out.println("来吗？");
        List<PUser> list =puserService .queryAllPuser();
        return list;
    }
    //删除
    @RequestMapping("/delete.do")
    public String delete(PUser PUser){
        System.out.println("来啦没？");
        System.out.println(PUser);

        puserService.Servicedelete(PUser);
        return "ok";
    }
    //修改
    @RequestMapping("/update.do")
    public String update(PUser PUser){
        System.out.println("进入修改方法..");
        System.out.println(PUser);
        puserService.Serviceupdate(PUser);
        return "ok";
    }
    //查上师
    @RequestMapping("/findupname.do")
    public List<Master> findupname(){
        List<Master> list = masterService.findlist();
        return  list;
    }
    //查询字段
    @RequestMapping("/findfield.do")
    public List<Map<String,Object>> findfield(){
        //总外层
        ArrayList<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
        //外层
        HashMap<String, Object> map = new HashMap<String, Object>();
        //子成
        ArrayList<Map<String,Object>> zilist = new ArrayList<Map<String,Object>>();
        //子成map
        HashMap<String, Object> ziMap = new HashMap<String, Object>();
        //查所有
        List<PUser> findlist = puserService.Servicefindlist();

        //通过反射获取所有公开属性
        Field[] declaredFields = PUser.class.getDeclaredFields();

        //创建一个declaredFields长度的数组
        String[] titles = new String[declaredFields.length];
        String[] attribute = new String[declaredFields.length];
        for (int i = 0; i <declaredFields.length-1; i++) {
            //代表属性对象
            Field declaredField = declaredFields[i];
            //获取指定的注解
            AnnName annotation = declaredField.getAnnotation(AnnName.class);
            Content content = declaredField.getAnnotation(Content.class);
            String value = content.value();
            String name = annotation.name();
            attribute[i] = value;
            titles[i] = name;
        }

        ziMap.put("id",attribute);
        ziMap.put("text",titles);

        zilist.add(ziMap);
        for (PUser PUser : findlist) {
            map.put("id",PUser.getId());
            map.put("text",PUser.getName());
            map.put("iconCls","icon-save");
            map.put("state","yes");
            for (Map.Entry<String, Object> entry : ziMap.entrySet()) {
                map.put("children",zilist);
            }

        }

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry);
        }
        lists.add(map);


        return lists;
    }

    //查用户注册信息
    @RequestMapping("/findtime.do")
    public Map<String, Map<String, Object>> findtime(){
        System.out.println("进入用户注册信息查询...");
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(puserService.Servicefindtime(7));
        list.add(puserService.Servicefindtime(14));
        list.add(puserService.Servicefindtime(21));
        list.add(puserService.Servicefindtime(60));
        list.add(puserService.Servicefindtime(120));
        list.add(puserService.Servicefindtime(240));
        list.add(puserService.Servicefindtime(356));
        HashMap<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
        Map<String, Object> hashMap = new HashMap<String, Object>();

        hashMap.put("data",list);
        map.put("counts",hashMap);

        return map;
    }

    //查询地区用户性别
    @RequestMapping("/findsex.do")
    public Map<String,Object> findsex(){
        System.out.println("123来了吗？");
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<Usersite> man = puserService.Servicefindman();
        List<Usersite> woman = puserService.Servicefindwoman();

        map.put("man",man);
        map.put("woman",woman);
        return map;
    }
    @RequestMapping("/insertAppUser.do")
    @ResponseBody
    public String insertAppUser(MultipartFile userTable) {
        try {
            return puserService.insertAppUser(userTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

    /**
     * 自定义导出
     * @param content
     */
    @RequestMapping("/addAppUser.do")
    public void addAppUser(String content, String fields,
                           HttpServletResponse response,Integer page,Integer rows) throws IOException {
        System.out.println(content);
        System.out.println(fields);
        //导出excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet usersheet = workbook.createSheet("userlist");
        //建立标题行
        HSSFRow titlerow = usersheet.createRow(0);
        String[] split = content.split(",");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            HSSFCell cell = titlerow.createCell(i);
            cell.setCellValue(s);
        }
        //建立数据行,查询数据库所有用户信息
        //查询出用户的数据


        List<PUser> applist = puserService.queryAllPuser();
        System.out.println("Join String[] split1");
        String[] split1 = fields.split(",");
        for (int i = 1; i <=applist.size(); i++) {
            HSSFRow datarow = usersheet.createRow(i);
            for (int j = 0; j < split1.length; j++) {
                HSSFCell cell = datarow.createCell(j);
                //通过拼凑方法名,调用反射的形式获取属性值
                String methodName = "get" + split1[j].substring(0, 1).toUpperCase() + split1[j].substring(1);
                //获取类对象
                try {
                    Method method = PUser.class.getDeclaredMethod(methodName, null);
                    //调用实例对象方法
                    Object invoke = method.invoke(applist.get(i - 1));
                    //判断返回值的类型
                    if(invoke instanceof Date){
                        //获取当前列
                        usersheet.setColumnWidth(j, 40 * 256);//设置该下标 单元格的 列宽
                        CellStyle cellStyle = workbook.createCellStyle();//设置单元格文字居中
                        cellStyle.setAlignment(HorizontalAlignment.CENTER);//对齐形式
                        DataFormat dataFormat = workbook.createDataFormat();//日期格式转换
                        short format = dataFormat.getFormat("yyyy年MM月dd日");//再次转换
                        cell.setCellValue(new Date());//填写日期格式
                        cellStyle.setDataFormat(format);

                        cell.setCellStyle(cellStyle);//给单元格风格类型
                        HSSFFont font = workbook.createFont();//文字颜色
                        font.setBold(true);//是否加粗
                        font.setColor(Font.COLOR_NORMAL);//红色
                        //设置内容
                        cell.setCellValue((Date) invoke);
                    }else{
                        cell.setCellValue(invoke.toString());
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
        //设置文件类型
        response.setContentType("application/vnd.ms-excel");
        String fileName = "userinfo.xls";
        //设置响应头,指定浏览器是下载还是临时加载
        response.setHeader("content-disposition","attachment;fileName="+new String(fileName.getBytes("utf-8"),"iso-8859-1"));
        //书写excel
        workbook.write(response.getOutputStream());
    }


}
