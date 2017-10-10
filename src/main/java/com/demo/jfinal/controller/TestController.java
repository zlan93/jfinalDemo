package com.demo.jfinal.controller;

import com.demo.jfinal.model.ext.Po;
import com.jfinal.core.Controller;

public class TestController extends Controller {

    //config配置方式
    public void index() {
        String name = getPara("name");
        Po p = getBean(Po.class, "p");
//        renderText("Hello Maven Jfinal");
        setAttr("blogs", name);
        render("index.jsp");
    }

}
