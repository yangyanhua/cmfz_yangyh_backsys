package com.baizhi.yangyh.util;

import com.alibaba.fastjson.JSONObject;
import io.goeasy.GoEasy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

public class TestGoEasy {
    @Test
    public void test1(){
        GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io/publish", "BC-82da440207614095b697816ca3ef5935");
        goEasy.publish("my_channel", "搞一波!!");
    }
    @Test
    public void test2() throws InterruptedException {
        while (true){
            //监听数据库用户表变化(反复查询用户表的信息,看是否有新增的)
            //销售数量
            ArrayList<Integer> list = new ArrayList<Integer>();
            Random random = new Random();
            for (int i=0;i<6;i++){
                int i1 = random.nextInt(100);
                list.add(i1);
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data",list);
            String s = JSONObject.toJSONString(jsonObject);
            GoEasy goEasy = new GoEasy( "http://rest-hangzhou.goeasy.io/publish", "BC-82da440207614095b697816ca3ef5935");
            goEasy.publish("my_channel", s);
            Thread.sleep(5*1000);
        }
    }
}
