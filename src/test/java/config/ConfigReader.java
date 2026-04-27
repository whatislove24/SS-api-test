package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String baseUrl() {
        return System.getenv().getOrDefault(
                "BASE_URL",
                properties.getProperty("base.url")
        );
    }

    public static String basePath() {
        return System.getenv().getOrDefault(
                "BASE_PATH",
                properties.getProperty("base.path")
        );
    }
}