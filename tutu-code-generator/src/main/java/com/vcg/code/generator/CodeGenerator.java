package com.vcg.code.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("D:/work/ideaProject/shijue/visual-moment/visual-cms/src/main/java");
        gc.setAuthor("Ocean");
        gc.setOpen(false);
        gc.setSwagger2(false);
        gc.setFileOverride(true);
        gc.setDateType(DateType.TIME_PACK);
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/mytest?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setTypeConvert(new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
                if (fieldType.toLowerCase().contains("tinyint")) {
                    return DbColumnType.INTEGER;
                }
                return super.processTypeConvert(config, fieldType);
            }
        });
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("");
        pc.setParent("com.sjsk.passjava");
//        pc.setEntity("model");
        pc.setEntity("entity");
        pc.setServiceImpl(null);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };


        // 如果模板引擎是 freemarker
        String xmlTemplatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";


        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();

        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(xmlTemplatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return "D:/work/ideaProject/shijue/visual-moment/visual-cms/src/main/resources/mapper/study/" + tableInfo.getEntityName() + "Mapper"
                        + StringPool.DOT_XML;
            }
        });
        /*
         * cfg.setFileCreate(new IFileCreate() {
         *
         * @Override public boolean isCreate(ConfigBuilder configBuilder, FileType
         * fileType, String filePath) { // 判断自定义文件夹是否需要创建 checkDir("调用默认方法创建的目录，自定义目录用");
         * if (fileType == FileType.MAPPER) { // 已经生成 mapper 文件判断存在，不想重新生成返回 false return
         * !new File(filePath).exists(); } // 允许生成模板文件 return true; } });
         */
        cfg.setFileOutConfigList(focList);

        // 重新只覆盖生成新实体类
        cfg.setFileCreate((configBuilder, fileType, filePath) -> {
            if (fileType == FileType.ENTITY) {
                return true;
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            return !file.exists() || configBuilder.getGlobalConfig().isFileOverride();
        });

        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity.java");
        templateConfig.setService("templates/service.java");
//        templateConfig.setServiceImpl("templates/serviceImpl.java");
        templateConfig.setController("templates/controller.java");
        templateConfig.setXml("");

        // 关闭原有生成
        templateConfig.setServiceImpl(null);

        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.bs.mybatis.base.AuditEntity");
//        strategy.setSuperEntityClass("com.bs.mybatis.base.OnlyAuditEntity");
//        strategy.setSuperEntityClass("java.io.Serializable");
        strategy.setEntityLombokModel(true);
        strategy.setChainModel(true);
        strategy.setRestControllerStyle(true);
        // strategy.setEntityTableFieldAnnotationEnable(true);
        // 公共父类
        // strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");

        // 指定要生成的表,不指定为所有表
        strategy.setInclude("student","teacher");
//        strategy.setInclude("center_bucket_datakeep_day");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

        mpg.execute();
    }

}
