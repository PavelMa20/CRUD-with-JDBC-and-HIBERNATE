package util;

import exception.DBException;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCHelper {
    public static Connection getMysqlConnection() throws DBException {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
            Properties properties = PropertyReader.getProperties("configJD.properties");
            StringBuilder url = new StringBuilder();
            url.
                    append(properties.getProperty("db.type")).
                    append("://").
                    append(properties.getProperty("db.host")).
                    append(":").
                    append(properties.getProperty("db.port")).
                    append("/").
                    append(properties.getProperty("db.name")).
                    append("?").
                    append("user=").
                    append(properties.getProperty("db.user")).
                    append("&").
                    append("password=").
                    append(properties.getProperty("db.password")).
                    append("&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");               //password


            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}
