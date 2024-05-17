package core.config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigProvider {
    private static ConfigProvider instance;
    private static Properties properties = new Properties();

    private ConfigProvider() {
        // Private constructor to prevent instantiation from outside the class.
        try (InputStream input = ConfigProvider.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public static ConfigProvider getInstance() {
        if (instance == null) {
            synchronized (ConfigProvider.class) {
                if (instance == null) {
                    instance = new ConfigProvider();
                }
            }
        }
        return instance;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}
