package util;

import exception.DBException;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HBHelper {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(Configuration configuration) throws DBException {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory(configuration);
        }
        return sessionFactory;

    }

    private static SessionFactory createSessionFactory(Configuration configuration) throws DBException {
        try {
           // Configuration configuration = getMySqlConfiguration();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = builder.build();
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    @SuppressWarnings("UnusedDeclaration")
    public static Configuration getMySqlConfiguration() throws DBException {
        try {
            Configuration configuration = new Configuration();
            configuration.addAnnotatedClass(User.class);
            Properties properties = PropertyReader.getProperties("configHB.properties");

            configuration.setProperty("hibernate.dialect", properties.getProperty("hibernate.dialect"));
            configuration.setProperty("hibernate.connection.driver_class", properties.getProperty("hibernate.connection.driver_class"));
            configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/" +
                    properties.getProperty("hibernate.connection.url") + "?serverTimezone=" + properties.getProperty("hibernate.jdbc.time_zone"));
            configuration.setProperty("hibernate.connection.username", properties.getProperty("hibernate.connection.username"));
            configuration.setProperty("hibernate.connection.password", properties.getProperty("hibernate.connection.password"));
            configuration.setProperty("hibernate.show_sql", properties.getProperty("hibernate.show_sql"));
            configuration.setProperty("hibernate.hbm2ddl.auto", properties.getProperty("hibernate.hbm2ddl.auto"));


            return configuration;
        }catch (Exception e){
            throw new DBException(e);
        }
    }
}
