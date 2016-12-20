package com.bong.com;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


/**
 * Created by ibong-gi on 2016. 12. 5..
 */
@Configuration
@MapperScan(basePackages = "com.bong.repository.mappers", sqlSessionFactoryRef = "cmsSqlSession")
@EnableTransactionManagement
@EnableConfigurationProperties
public class DatabaseConfig {

    @Primary
    @Bean(name = "cmsDataSource")
    @Qualifier(value = "cmsDataSource")
    @ConfigurationProperties(prefix = "spring.database.cms.datasource")
    public DataSource cmsDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "cmsTransactionManager")
    @Qualifier(value = "cmsTransactionManager")
    public DataSourceTransactionManager cmsTransactionManager(){
        return new DataSourceTransactionManager(cmsDataSource());
    }

    @Primary
    @Bean(name = "cmsSqlSession")
    @Qualifier(value = "cmsSqlSession")
    public SqlSessionFactoryBean cmsSqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(cmsDataSource());
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:sqlMappers/*.xml"));

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);

        return sqlSessionFactoryBean;
    }
}
