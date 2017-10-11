package com.demo.jfinal.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class LoginController extends Controller {

    //注解方式
    @ActionKey("/login")//这个注解方法挺鸡肋的，还是得在config文件里add这个controller，不然没什么用。感觉这个只有在不想访问地址和方法名一样时才用的上
    public void login() {
        render("login.jsp");
    }
}
