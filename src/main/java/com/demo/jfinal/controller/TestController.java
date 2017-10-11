package com.demo.jfinal.controller;

import com.demo.jfinal.model.db.CvOrderMonitor;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

public class TestController extends Controller {

    //config配置方式
    public void index() {
        String name = getPara("name");
        setAttr("blogs", name);
//        Po p = getBean(Po.class, "p");
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
