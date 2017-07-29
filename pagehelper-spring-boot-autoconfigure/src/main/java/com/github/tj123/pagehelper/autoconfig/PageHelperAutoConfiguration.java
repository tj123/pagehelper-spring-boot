package com.github.tj123.pagehelper.autoconfig;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

/**
 * Created by TJ on 2017/7/29.
 */
@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
@EnableConfigurationProperties(PageHelperProperties.class)
public class PageHelperAutoConfiguration {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    List<SqlSessionFactory> sqlSessionFactories;

    @Autowired
    PageHelperProperties pageHelperProperties;

    @PostConstruct
    public void addPlugin() {
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();

        properties.setProperty("closeConn", pageHelperProperties.getCloseConn());
        properties.setProperty("autoRuntimeDialect", pageHelperProperties.getAutoRuntimeDialect());
        properties.setProperty("dialect", pageHelperProperties.getDialect());
        properties.setProperty("offsetAsPageNum", pageHelperProperties.getOffsetAsPageNum());
        properties.setProperty("rowBoundsWithCount", pageHelperProperties.getRowBoundsWithCount());
        properties.setProperty("pageSizeZero", pageHelperProperties.getPageSizeZero());
        properties.setProperty("reasonable", pageHelperProperties.getReasonable());
        properties.setProperty("params", pageHelperProperties.getParams());
        properties.setProperty("supportMethodsArguments", pageHelperProperties.getSupportMethodsArguments());
        properties.setProperty("returnPageInfo", pageHelperProperties.getReturnPageInfo());
        pageHelper.setProperties(properties);

        //添加插件
        for (SqlSessionFactory factory : sqlSessionFactories) {
            factory.getConfiguration().addInterceptor(pageHelper);
        }
    }
}
