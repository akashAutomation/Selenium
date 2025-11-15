package support;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigPropertiesReader {
    private static Properties prop;

    public static void loadProperties(String filePath) {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + filePath, e);
        }
    }

    public static String get(String key) {
        ConfigPropertiesReader.loadProperties("src/test/resources/data/data.properties");
        return prop.getProperty(key);
    }
}
