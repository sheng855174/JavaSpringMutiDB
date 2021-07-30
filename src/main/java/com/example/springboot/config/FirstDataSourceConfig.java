package com.example.springboot.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.datatables.repository.DataTablesRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "firstEntityManagerFactory",
        transactionManagerRef = "firstTransactionManager",
        basePackages = { "com.example.springboot.model.first" },
        repositoryFactoryBeanClass = DataTablesRepositoryFactoryBean.class)
@Configuration
public class FirstDataSourceConfig {

    @Primary
    @Bean(name = "firstDataSource")
    public DataSource firstDataSource () {
        DataSource targetDataSource = DataSourceBuilder
                .create()
                .username("user01")
                .password("password")
                .url("jdbc:mysql://localhost:3306/DBone")
                .driverClassName("com.mysql.jdbc.Driver")
                .build();

        return targetDataSource;
    }

    @Primary
    @Bean(name = "firstEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean firstEntityManagerFactory (
            EntityManagerFactoryBuilder builder,
            @Qualifier("firstDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(firstDataSource());
        em.setPackagesToScan(new String[] { "com.example.springboot.model.first" });
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
        /*return builder.dataSource(dataSource)
                .packages("com.example.springboot.model.first").build();*/
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        // spring.jpa.generate-ddl=true 不可以用，這個專案會成功，但沒辦法跟舊有的entity mapping一致
        // 會產生hibernate_sequence的table
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.setProperty("hibernate.ddl-auto", "update");
        properties.setProperty("hibernate.ejb.entitymanager_factory_name", "endblockEntityManager");
        return properties;
    }

    @Primary
    @Bean(name = "firstTransactionManager")
    public PlatformTransactionManager firstTransactionManager (
            @Qualifier("firstEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    // First Datasource JdbcTemplate
    @Bean(name = "firstJdbcTemplate")
    public JdbcTemplate firstTemplate(@Qualifier("firstDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
