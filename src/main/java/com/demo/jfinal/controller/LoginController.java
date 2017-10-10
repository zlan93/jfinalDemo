package com.demo.jfinal.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class LoginController extends Controller {

    //注解方式
    @ActionKey("/login")
    public void login() {
        render("login.jsp");
    }
}
