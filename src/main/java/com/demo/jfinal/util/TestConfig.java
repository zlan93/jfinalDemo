package com.demo.jfinal.util;

import com.demo.jfinal.controller.LoginController;
import com.demo.jfinal.controller.TestController;
import com.demo.jfinal.model.db._MappingKit;
import com.demo.jfinal.shiro.ShiroInterceptor;
import com.demo.jfinal.shiro.ShiroPlugin;
import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;

public class TestConfig extends JFinalConfig {
    /**
     * 供Shiro插件使用。
     */
    private Routes routes;

    /**
     * //此方法用来配置JFinal的常量值
     *
     * @param constants
     */
    @Override
    public void configConstant(Constants constants) {
        Prop p = PropKit.use("SystemConfig.txt");//PropKit 工具类用来操作外部配置文件
        // 开发者模式，正式部署时关闭
        constants.setDevMode(p.getBoolean("devMode"));
        constants.setBaseViewPath("/WEB-INF/views");//配置页面访问主路径,webapp下

        constants.setViewType(ViewType.JSP);//设置视图类型为Jsp，否则默认为FreeMarker
    }

    /**
     * //配置JFinal访问路由
     *
     * @param me
     */
    @Override
    public void configRoute(Routes me) {
        this.routes = me;
        me.add("/test", TestController.class, "/");//路由，指定调用哪个controller,访问地址是/controllerKey/viewPath/方法名（index()是默认方法，不需要加方法名）
        me.add("/", LoginController.class, "/");
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
        interceptors.add(new ShiroInterceptor());//shiro拦截器
//        interceptors.add(new Filter());//配置拦截器
    }

    /**
     * //用来配置JFinal的Plugin，C3P0数据库连接池插件，ActiveRecordPlugin数据库访问插件
     *
     * @param me
     */
    @Override
    public void configPlugin(Plugins me) {
        //加载数据库配置文件
        Prop p = PropKit.use("SystemConfig.txt");
        //创建c3p0连接
        C3p0Plugin c3p0Plugin = new C3p0Plugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));

        ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
        arp.setShowSql(true);
        arp.setDevMode(true);
        // 方式一： 直接配置数据表映射
//        arp.addMapping("GB_DRIVER", "ID", GbDriver.class);

        // 方式二：配置数据表映射写到一个文件中
        _MappingKit.mapping(arp);

        me.add(c3p0Plugin);
        me.add(arp);


        //shiro
        ShiroPlugin shiroPlugin = new ShiroPlugin(this.routes);
        me.add(shiroPlugin);
    }
}
