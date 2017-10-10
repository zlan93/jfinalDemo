package com.demo.jfinal.util;

import com.demo.jfinal.controller.LoginController;
import com.demo.jfinal.controller.TestController;
import com.demo.jfinal.interceptor.Filter;
import com.jfinal.config.*;
import com.jfinal.render.ViewType;


public class TestConfig extends JFinalConfig {
    /**
     * //此方法用来配置JFinal的常量值
     *
     * @param constants
     */
    @Override
    public void configConstant(Constants constants) {
        // 开发者模式，正式部署时关闭
        constants.setDevMode(true);
        constants.setBaseViewPath("/WEB-INF/views");//配置页面访问主路径,webapp下

        constants.setViewType(ViewType.JSP);//设置视图类型为Jsp，否则默认为FreeMarker
    }

    /**
     * //配置JFinal访问路由
     *
     * @param routes
     */
    @Override
    public void configRoute(Routes routes) {
        routes.add("/test", TestController.class, "/");//路由，指定调用哪个controller,若不添加第三个参数访问时会默认添加/test路径
        routes.add("/", LoginController.class, "/");
    }

    /**
     * //用来配置JFinal的Handler
     *
     * @param handlers
     */
    @Override
    public void configHandler(Handlers handlers) {

    }

    /**
     * //用来配置JFinal的Interceptor
     *
     * @param interceptors
     */
    @Override
    public void configInterceptor(Interceptors interceptors) {
        interceptors.add(new Filter());//配置拦截器
    }

    /**
     * //用来配置JFinal的Plugin，C3P0数据库连接池插件，ActiveRecordPlugin数据库访问插件
     *
     * @param plugins
     */
    @Override
    public void configPlugin(Plugins plugins) {

    }
}
