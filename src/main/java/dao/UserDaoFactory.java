package dao;

import exception.DBException;
import util.PropertyReader;

import java.util.Properties;

public class UserDaoFactory {
    public static IUserDao create() throws DBException {
        Properties properties = PropertyReader.getProperties("daotype.properties");
        String type = properties.getProperty("dao.type");

        if (type.equals("hibernate")) {
            return new UserDaoHBImpl();
        }
        if (type.equals("jdbc")) {
            return new UserDaoJDImpl();
        }

        throw new DBException("");
    }


}
