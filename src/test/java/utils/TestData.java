package utils;

public class TestData {

    public static final String BASE_URL = "https://stellarburgers.education-services.ru/";

    // Для регистрации будем генерировать уникальные данные
    public static String getRandomEmail() {
        return "testuser" + System.currentTimeMillis() + "@example.com";
    }

    public static String getRandomName() {
        return "TestUser" + System.currentTimeMillis();
    }

    public static final String VALID_PASSWORD = "123456";
    public static final String INVALID_PASSWORD = "12345";

    // Существующие данные для входа
    public static final String EXISTING_EMAIL = "lafoyet@yandex.ru";
    public static final String EXISTING_PASSWORD = "1234567";

    public static String getTestEmail() {
        return EXISTING_EMAIL;
    }

    public static String getTestPassword() {
        return EXISTING_PASSWORD;
    }
}