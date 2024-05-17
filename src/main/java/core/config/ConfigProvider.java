package core.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigProvider {
    private static ConfigProvider instance;
    private Map<String, String> configurations;

    private ConfigProvider() {
        configurations = new HashMap<>();
    }

    public static void loadConfigurations(String configFilePath) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + configFilePath, e);
        }
        props.forEach((key, value) -> ConfigProvider.getInstance().addConfiguration(key.toString(), value.toString()));
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
        return ConfigProvider.getInstance().getConfiguration(key);
    }

    public static String getBaseUrl() {
        return ConfigProvider.getInstance().getConfiguration("base.url");
    }

    public static String getApiKey() {
        return ConfigProvider.getInstance().getConfiguration("api.key");
    }

    public static String getLogLevel() {
        return ConfigProvider.getInstance().getConfiguration("log.level");
    }

    public void addConfiguration(String key, String value) {
        configurations.put(key, value);
    }

    public String getConfiguration(String key) {
        return configurations.get(key);
    }
}