package com.htzs.pms.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 从控制台输入数据库名字和表名， 就会自动生成  dao、entity、mapper
 *
 * @author: Earle
 * @createdDate: 2019-7-30
 * @updatedDate: 2019-08-08
 */
public class MySQLCodeGenerator {


    private static final String DAO = "dao";

    private static final String DOMAIN = "domain";

    private static final String SERVICE = "service";

    private static final String WEB = "web";

    private static final String CONTROLLER = "controller";

    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

    private static final String MYSQL_USER_NAME = "admin";

    private static final String MYSQL_PASSWORD = "123456";

    private static final String MYSQL_URL = "jdbc:mysql://192.168.168.220:3306/PMSDB?useUnicode=true&allowPublicKeyRetrieval=true&useSSL=false&characterEncoding=utf8";


    public static void main(String[] args) {
        // 要生成模块的名字
        String[] modules = {"ProductManagementSystem-service", "ProductManagementSystem-web", "ProductManagementSystem-dao", "ProductManagementSystem-domain"};
        // 需要生成的表； 如果不写要生成的表名就会， 重新生成所有的表
        String[] tableName = {"pms_maintain"};
        // 去除表名字的前缀
        String[] tablePrefix = {"pms_"};
        // 包路径
        String packagePath = "com.htzs.pms";
        // 放着 xml 的模块名
        String xml = "ProductManagementSystem-dao";

        for (String module : modules) {
            getExecute(module, xml, tableName, tablePrefix, packagePath);
        }

    }

    /**
     * @param module      模块名字
     * @param moduleXml   XML 模块名字
     * @param packagePath 包路径
     * @param tableName   表的名字
     * @param tablePrefix 去除表的前缀名
     */
    public static void getExecute(String module, String moduleXml, String[] tableName, String[] tablePrefix, String packagePath) {
        StrategyConfig strategyConfig = getStrategyConfig(tableName, tablePrefix);
        PackageConfig packageConfig = getPackageConfig(packagePath);
        DataSourceConfig dataSourceConfig = getDataSourceConfig();
        GlobalConfig globalConfig = getGlobalConfig(module);
        InjectionConfig injectionConfig = getInjectionConfig(moduleXml);
        TemplateConfig templateConfig = getTemplateConfig(module);
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator()
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setCfg(injectionConfig)
                .setTemplate(templateConfig);
        mpg.execute();
    }

    public static StrategyConfig getStrategyConfig(String[] tableName, String[] tablePrefix) {
        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                // 全局大写命名
                .setCapitalMode(true)
                //全局下划线命名
                // .setDbColumnUnderline(true)
                // 此处可以修改为您的表前缀
                .setTablePrefix(tablePrefix)
                // 表名生成策略
                .setNaming(NamingStrategy.underline_to_camel)
                // 需要生成的表
                .setInclude(tableName)
                // 排除生成的表
                // .setExclude(new String[]{"test"})
                .setEntityBooleanColumnRemoveIsPrefix(true)
                // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                .setEntityLombokModel(true);
        // Boolean类型字段是否移除is前缀处理
        // .setEntityBooleanColumnRemoveIsPrefix(true)
        // .setRestControllerStyle(true)
        // .setControllerMappingHyphenStyle(true)
        return strategyConfig;
    }

    public static PackageConfig getPackageConfig(String packagePath) {
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc
                // 自定义包路径
                .setParent(packagePath)
                // 这里是控制器包名，默认 web
                .setMapper(DAO)
                .setController(CONTROLLER);
        return pc;
    }

    public static DataSourceConfig getDataSourceConfig() {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc
                // 数据库类型
                .setDbType(DbType.MYSQL)
                .setTypeConvert(new MySqlTypeConvert() {
                    // 自定义数据库表字段类型转换【可选】
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                })
                .setDbQuery(new MySqlQuery() {
                    /**
                     * 重写父类预留查询自定义字段<br>
                     * 这里查询的 SQL 对应父类 tableFieldsSql 的查询字段，默认不能满足你的需求请重写它<br>
                     * 模板中调用：  table.fields 获取所有字段信息，
                     * 然后循环字段获取 field.customMap 从 MAP 中获取注入字段如下  NULL 或者 PRIVILEGES
                     */
                    @Override
                    public String[] fieldCustom() {
                        return new String[]{"NULL", "PRIVILEGES"};
                    }
                })
                .setDriverName(MYSQL_DRIVER)
                .setUsername(MYSQL_USER_NAME)
                .setPassword(MYSQL_PASSWORD)
                .setUrl(MYSQL_URL);
        return dsc;
    }

    public static GlobalConfig getGlobalConfig(String module) {

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc
                //输出目录
                .setOutputDir(new File(module).getAbsolutePath().concat("/src/main/java"))
                // 是否覆盖文件
                .setFileOverride(true)
                // 开启 activeRecord 模式
                .setActiveRecord(true)
                // XML 二级缓存
                .setEnableCache(false)
                // XML ResultMap
                .setBaseResultMap(true)
                // XML columList
                .setBaseColumnList(true)
                //.setKotlin(true) 是否生成 kotlin 代码
                .setAuthor("Earle")
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setMapperName("%sMapper")
                .setServiceName("%sService");
        return gc;
    }

    public static InjectionConfig getInjectionConfig(String moduleXml) {
        // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
        InjectionConfig ic =
                new InjectionConfig() {
                    @Override
                    public void initMap() {
                        Map<String, Object> map = new HashMap<>();
                        map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                        this.setMap(map);
                    }
                }.setFileOutConfigList(Collections.singletonList(new FileOutConfig(
                        "/templates/mapper.xml" + ".vm") {
                    // 自定义输出文件目录
                    @Override
                    public String outputFile(TableInfo tableInfo) {
                        return moduleXml + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
                    }
                }));

        return ic;
    }

    public static TemplateConfig getTemplateConfig(String module) {
        TemplateConfig tc = new TemplateConfig();
        if (module.endsWith(DAO)) {
            tc.setService(null)
                    .setXml(null)
                    .setServiceImpl(null)
                    .setEntity(null)
                    .setEntityKt(null)
                    .setController(null);
        } else if (module.endsWith(DOMAIN)) {
            tc.setMapper(null)
                    .setXml(null)
                    .setService(null)
                    .setServiceImpl(null)
                    .setController(null);
        } else if (module.endsWith(SERVICE)) {
            tc.setXml(null)
                    .setMapper(null)
                    .setEntity(null)
                    .setEntityKt(null)
                    .setController(null);
        } else if (module.endsWith(WEB)) {
            tc.setXml(null)
                    .setMapper(null)
                    .setEntity(null)
                    .setEntityKt(null)
                    .setService(null)
                    .setServiceImpl(null);
        }
        return tc;

    }

}
