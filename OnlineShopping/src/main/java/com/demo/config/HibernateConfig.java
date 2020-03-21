package com.demo.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "com.demo.model" })
@EnableTransactionManagement
public class HibernateConfig {

	private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/nobelit?userSSL=false";
	private final static String DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String DATABASE_DIALECT = "org.hibernate.dialect.MySQL57Dialect";
	private final static String DATABASE_USERNAME = "shubh";
	private final static String DATABASE_PASSWORD = "shubh573";

	@Bean("dataSource")
	public DataSource getDataSource() {

		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);

		return dataSource;

	}

	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);

		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.demo.model");

		return builder.buildSessionFactory();

	}

	private Properties getHibernateProperties() {

		Properties properties = new Properties();

		properties.put("hibernate.dialect", DATABASE_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");

		// properties.put("hibernate.hbm2ddl.auto", "create");

		return properties;
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
}
