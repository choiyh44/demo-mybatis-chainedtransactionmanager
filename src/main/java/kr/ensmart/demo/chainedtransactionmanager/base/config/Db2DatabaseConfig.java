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
@MapperScan(basePackages="kr.ensmart.demo.chainedtransactionmanager.**.repository.db2", sqlSessionFactoryRef="db2SqlSessionFactory")
public class Db2DatabaseConfig {
//    @Bean(name = "db2DataSource")
//    @ConfigurationProperties(prefix = "demoapp.datasource.db2")
//    DataSource db2DataSource() {
//        return DataSourceBuilder.create().build();
//    }

	@Bean(name = "db2DataSourceProperties")
    @ConfigurationProperties("demoapp.datasource.db2")
    public DataSourceProperties db2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "db2DataSource")
    @ConfigurationProperties("demoapp.datasource.db2.hikari")
    public HikariDataSource dataSource(@Qualifier("db2DataSourceProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean(name = "db2SqlSessionFactory")
    SqlSessionFactory db2SqlSessionFactory(@Qualifier("db2DataSource") DataSource db2DataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(db2DataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("kr.ensmart.demo.chainedtransactionmanager.**.dto");
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/**/db2/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:mapper/mybatis-db2config.xml"));
        return sqlSessionFactoryBean.getObject();
    }
 
    @Bean(name = "db2SqlSessionTemplate")
    SqlSessionTemplate db2SqlSessionTemplate(SqlSessionFactory db2SqlSessionFactory) throws Exception { 
        return new SqlSessionTemplate(db2SqlSessionFactory);
    }

    @Bean(name = "db2SqlSessionTemplateBatch")
    SqlSessionTemplate db2SqlSessionTemplateBatch(SqlSessionFactory db2SqlSessionFactory) throws Exception { 
        return new SqlSessionTemplate(db2SqlSessionFactory, ExecutorType.BATCH);
    }

    @Bean(name="db2TransactionManager")
    PlatformTransactionManager db2TransactionManager(@Autowired @Qualifier("db2DataSource") DataSource db2DataSource) {
        return new DataSourceTransactionManager(db2DataSource);
    }

}
