/**
 * 
 */
package kr.ensmart.demo.chainedtransactionmanager.base.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

/**
 * @author choiyh44
 * @version 1.0
 * @since 2021. 9. 8.
 *
 */
@Configuration
@MapperScan(basePackages="kr.ensmart.demo.chainedtransactionmanager.**.repository.db1", sqlSessionFactoryRef="db1SqlSessionFactory")
public class Db1DatabaseConfig {
//    @Bean(name = "db1DataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "demoapp.datasource.db1")
//    DataSource db1DataSource() {
//        return DataSourceBuilder.create().build();
//    }

	@Bean(name = "db1DataSourceProperties")
    @ConfigurationProperties("demoapp.datasource.db1")
    public DataSourceProperties db1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "db1DataSource")
    @Primary
    @ConfigurationProperties("demoapp.datasource.db1.hikari")
    public HikariDataSource dataSource(@Qualifier("db1DataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "db1SqlSessionFactory")
    SqlSessionFactory db1SqlSessionFactory(@Qualifier("db1DataSource") DataSource db1DataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db1DataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("kr.ensmart.demo.chainedtransactionmanager.**.dto");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/**/db1/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mapper/mybatis-db1config.xml"));
        return sqlSessionFactoryBean.getObject();
    }
 
    @Bean(name = "db1SqlSessionTemplate")
    SqlSessionTemplate db1SqlSessionTemplate(SqlSessionFactory db1SqlSessionFactory) throws Exception { 
        return new SqlSessionTemplate(db1SqlSessionFactory);
    }

    @Bean(name="db1TransactionManager")
    @Primary
    PlatformTransactionManager db1TransactionManager(@Autowired @Qualifier("db1DataSource") DataSource db1DataSource) {
        return new DataSourceTransactionManager(db1DataSource);
    }

}
