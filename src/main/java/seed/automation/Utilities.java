package seed.automation;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


public class Utilities {

    public  String getInfo(String source, String property ) {
        Properties prop;
        String value = null;
        try {
            prop = new Properties();
            String fileName=source+".properties";
            prop.load(new FileInputStream(new File(fileName)));
            value = prop.getProperty(property);
            if (value == null || value.isEmpty()) {
                throw new Exception("Value not set or empty");
            }
        } catch (Exception e) {

        }
        return value;
    }
}