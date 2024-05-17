package core.utils;

import java.io.InputStream;
import java.util.Properties;

public class TestDataProvider {
    private final Properties properties = new Properties();

    public TestDataProvider() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("testdata.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find testdata.properties");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load test data", e);
        }
    }

    /**
     * Retrieves the test data value associated with the specified key.
     *
     * @param  key  the key for which the test data value is to be retrieved
     * @return      the test data value associated with the specified key
     */
    public String getTestData(String key) {
        return properties.getProperty(key);
    }
}
