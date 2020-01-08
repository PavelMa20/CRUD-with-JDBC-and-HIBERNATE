package util;

import exception.DBException;
import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

public class DBHelper {

    private static DBHelper instance;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (instance == null) {
            instance = new DBHelper();
        }
        return instance;
    }
    public static Configuration getConfiguration() throws DBException {
        return HBHelper.getMySqlConfiguration();
    }
    public static Connection getConnection() throws DBException {
        return JDBCHelper.getMysqlConnection();
    }

}
