package com.demo.jfinal.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

public class Filter implements Interceptor {
    @Override
    public void intercept(Invocation invocation) {
        System.out.println("Before method invoking");
        String actionkey = invocation.getActionKey();
        String name = invocation.getControllerKey();
        if (name.contains("test")) {
            System.out.println("aaa");
            Controller c = invocation.getController();
            c.redirect("/login");
            return;
        }
        invocation.invoke();
        System.out.println("After method invoking");
    }
}
