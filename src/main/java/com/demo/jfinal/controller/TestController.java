package com.demo.jfinal.controller;

import com.demo.jfinal.model.db.CvOrderMonitor;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

//config配置方式
public class TestController extends Controller {
//    Action 方法必须在 Controller 中声明，该方法必须是 public 可见且没有形参。
    
    //默认方法
    public void index() {
        String name = getPara("name");
        setAttr("blogs", name);
//        Po p = getBean(Po.class, "p");
//        getModel()需要表单域名称对应于数据表字段名，而 getBean()则依赖于 setter 方法，表单域名对应于 setter 方法去掉”set”前缀字符后剩下的字符串字母变小写

//        renderText("Hello Maven Jfinal");//直接输出文字

        //查询数据库数据
        CvOrderMonitor info = new CvOrderMonitor();
        List<Record> list = info.queryList();
        for (Record d : list) {
            System.out.println(d.get("ID").toString());
        }
        render("index.jsp");
    }

}
