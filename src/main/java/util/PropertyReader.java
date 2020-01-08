package util;


import exception.DBException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static Properties getProperties(String filename) throws DBException {
        try (InputStream input = PropertyReader.class.getClassLoader().getResourceAsStream(filename)) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            return prop;
        } catch (Exception ex) {
            throw  new DBException(ex);
        }

    }

}
