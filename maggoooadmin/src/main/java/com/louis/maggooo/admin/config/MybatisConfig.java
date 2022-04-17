package com.louis.maggooo.admin.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.louis.maggooo.admin.model")    // 扫描DAO
public class MybatisConfig {
    @Autowired
    private DataSource dataSource;

   @Bean
   public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setTypeAliasesPackage("com.louis.maggooo.admin.model");    // 扫描Model

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
       
         sessionFactory.setMapperLocations(resolver.getResources("classpath:**/com/louis/maggooo/admin/mapper/*.xml"));
        return sessionFactory.getObject();
    }



}
