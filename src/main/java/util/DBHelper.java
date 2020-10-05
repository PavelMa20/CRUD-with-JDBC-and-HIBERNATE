package util;

import exception.DBException;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;


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
