package com.example.multidatasource.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties(DatabaseProperties.class)
@EnableJpaRepositories(
        basePackages = "com.example.multidatasource.seconddb",
        entityManagerFactoryRef = SecondDatasourceConfig.ENTITY_MANAGER_BEAN_NAME,
        transactionManagerRef = XaDataSourceConfig.TRANSACTION_MANAGER_BEAN_NAME
)
public class SecondDatasourceConfig {

    public static final String ENTITY_MANAGER_BEAN_NAME = "secondEntityManager";
    private static final String DATASOURCE_BEAN_NAME = "secondDataSource";
    private static final String DATASOURCE_PROPERTIES_PREFIX = "spring.datasource.seconddb";
    private static final String HIBERNATE_PROPERTIES = "secondHibernateProperties";

    @Bean(name = ENTITY_MANAGER_BEAN_NAME)
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier(DATASOURCE_BEAN_NAME) DataSource dataSource,
            @Qualifier(HIBERNATE_PROPERTIES) DatabaseProperties.Hibernate hibernateProperties
    ) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.multidatasource.seconddb");
        em.setJpaPropertyMap(DatabaseProperties.Hibernate.propertiesToMap(hibernateProperties));
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }

    @Bean(name = HIBERNATE_PROPERTIES)
    @ConfigurationProperties(DATASOURCE_PROPERTIES_PREFIX + ".hibernate")
    public DatabaseProperties.Hibernate hibernateProperties() {
        return new DatabaseProperties.Hibernate();
    }

    @Bean(name = DATASOURCE_BEAN_NAME)
    @ConfigurationProperties(prefix = DATASOURCE_PROPERTIES_PREFIX)
    public DataSource dataSource() {
        return new AtomikosDataSourceBean();
    }
}