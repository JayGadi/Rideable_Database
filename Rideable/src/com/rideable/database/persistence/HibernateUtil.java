package com.rideable.database.persistence;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
* Utility class related to the persistence.
* Contains API used for Hibernate session management.
*
*
*/
public class HibernateUtil {

private static String DATABASE_NAME = "rideable";
private static  SessionFactory sessionFactory = buildSessionFactory();
private static ServiceRegistry serviceRegistry;


//private static  Session session;

/**
* Builds Hibernate session factory based on the Hibernate configuration file.
*/
	private static SessionFactory buildSessionFactory(){

		if (sessionFactory == null) {
			try {
                Configuration configuration = new Configuration();
                configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");  //Should read from file
                configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?autoReconnect=true"); //Should read from file
                configuration.setProperty("show_sql", "false");
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                configuration.setProperty("hibernate.connection.password", "ride"); //Should read from file or prompt
                configuration.setProperty("hibernate.connection.username", "ride"); //Should read from file or promtp
                configuration.setProperty("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");
                configuration.setProperty("hibernate.current_session_context_class", "thread");
                configuration.setProperty("hibernate.connection.autocommit", "true");
                configuration.configure();
                serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                        configuration.getProperties()).configure().build();
                sessionFactory = new MetadataSources( serviceRegistry ).buildMetadata().buildSessionFactory();
            } catch (HibernateException ex) {
                throw new RuntimeException("Exception building SessionFactory: " + ex.getMessage(), ex);
            }
        }
    return sessionFactory;
	}

	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}

}