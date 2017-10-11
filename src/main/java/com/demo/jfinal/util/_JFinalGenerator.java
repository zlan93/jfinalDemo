package com.demo.jfinal.util;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.c3p0.C3p0Plugin;

import javax.sql.DataSource;

public class _JFinalGenerator {
    public static DataSource getDataSource() {
        //加载配置文件
        Prop p = PropKit.use("SystemConfig.txt");
        //创建c3p0连接
        C3p0Plugin c3p0Plugin = new C3p0Plugin(p.get("jdbcUrl"), p.get("user"), p.get("password"));
        c3p0Plugin.start();
        return c3p0Plugin.getDataSource();
    }

    public static void main(String[] args) {
        // base model 所使用的包名
        String baseModelPackageName = "com.demo.jfinal.model.db.base";
        // base model 文件保存路径
        String baseModelOutputDir = PathKit.getWebRootPath() + "\\src\\main\\java\\com\\demo\\jfinal\\model\\db\\base";

        // model 所使用的包名 (MappingKit 默认使用的包名)
        String modelPackageName = "com.demo.jfinal.model.db";
        // model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
        String modelOutputDir = baseModelOutputDir + "/..";

        // 创建生成器
        Generator gernerator = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
        // 设置数据库方言
        //gernerator.setDialect(new MysqlDialect());
        // 添加不需要生成的表名
        //gernerator.addExcludedTable("adv");
        // 设置是否在 Model 中生成 dao 对象
        gernerator.setGenerateDaoInModel(true);
        // 设置是否生成字典文件
        gernerator.setGenerateDataDictionary(false);
        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
        //gernerator.setRemovedTableNamePrefixes("t_");
        // 生成
        gernerator.generate();

        /*
        视图没有主键，generator生成后无法使用dao进行调用，启动后会报错，
        第一种方法在_MappingKit文件中去掉所有视图的mapping,然后在config中手动加入所有视图mapping;
        第二种方法在_MappingKit文件中将所有视图的mapping语句第二个参数primaryKey去掉
        */
    }
}
