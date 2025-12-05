package utils;

public class TestData {

    public static final String BASE_URL = "https://stellarburgers.education-services.ru";
    public static final String API_URL = BASE_URL + "/api/";

    public static String getRandomEmail() {
        return "testuser" + System.currentTimeMillis() + "@example.com";
    }

    public static String getRandomName() {
        return "TestUser" + System.currentTimeMillis();
    }

    public static final String VALID_PASSWORD = "123456";
    public static final String INVALID_PASSWORD = "12345";
}