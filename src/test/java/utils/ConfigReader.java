package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final String CONFIG_FILE = "config.properties";
    private static Properties properties;

    static {
        properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                System.err.println("Файл конфигурации не найден: " + CONFIG_FILE);
                // Устанавливаем дефолтные значения
                properties.setProperty("yandex.driver.path", "/path/to/yandexdriver");
                properties.setProperty("yandex.browser.path", "/path/to/yandex/browser");
            } else {
                properties.load(input);
            }
        } catch (IOException e) {
            System.err.println("Ошибка загрузки конфигурации: " + e.getMessage());
            // Устанавливаем дефолтные значения
            properties.setProperty("yandex.driver.path", "/path/to/yandexdriver");
            properties.setProperty("yandex.browser.path", "/path/to/yandex/browser");
        }
    }

    public static String getYandexDriverPath() {
        String path = properties.getProperty("yandex.driver.path");
        if (path == null || path.trim().isEmpty() || path.equals("/path/to/yandexdriver")) {
            throw new RuntimeException("Путь к YandexDriver не указан в конфигурации.");
        }
        return path;
    }

    public static String getYandexBrowserPath() {
        String path = properties.getProperty("yandex.browser.path");
        if (path == null || path.trim().isEmpty() || path.equals("/path/to/yandex/browser")) {
            throw new RuntimeException("Путь к Yandex браузеру не указан в конфигурации.");
        }
        return path;
    }
}