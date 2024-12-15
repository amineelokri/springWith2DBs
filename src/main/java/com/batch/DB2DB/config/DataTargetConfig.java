package com.batch.DB2DB.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "targetDataSourceEntityManager",
        basePackages = "com.batch.DB2DB.repository.target",
        transactionManagerRef = "targetDataSourceTransactionManager"
)
public class DataTargetConfig {

    /****
     * DataSource Configuration
     ****/
    @Primary
    @Bean("targetDataSource")
    public DataSource targetDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/studenttarget");
        dataSource.setUsername("root");
        dataSource.setPassword("Root@2001");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }


    @Primary
    @Bean("targetDataSourceEntityManager")
    public LocalContainerEntityManagerFactoryBean targetDataSourceEntityManager(
            @Qualifier("targetDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder entityManagerFactoryBuilder) {

        Map<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        jpaProperties.put("hibernate.hbm2ddl.auto", "update");
        jpaProperties.put("hibernate.show_sql", "true");

        return entityManagerFactoryBuilder
                .dataSource(dataSource)
                .packages("com.batch.DB2DB.entity.target") // Adjust to your entity package
                .persistenceUnit("TargetUser")
                .properties(jpaProperties)
                .build();
    }


    /****
     * Transaction Manager Configuration
     ****/

    @Primary
    @Bean("targetDataSourceTransactionManager")
    public PlatformTransactionManager dataSourceTransactionManager(
            @Qualifier("targetDataSourceEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
}
