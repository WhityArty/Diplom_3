package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private static final String BASE_URL = "https://stellarburgers.education-services.ru/api/";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    public static Response registerUser(String email, String password, String name) {
        return given()
                .header("Content-type", "application/json")
                .body(String.format("{\"email\": \"%s\", \"password\": \"%s\", \"name\": \"%s\"}",
                        email, password, name))
                .when()
                .post("/auth/register");
    }

    public static Response loginUser(String email, String password) {
        return given()
                .header("Content-type", "application/json")
                .body(String.format("{\"email\": \"%s\", \"password\": \"%s\"}", email, password))
                .when()
                .post("/auth/login");
    }

    public static void deleteUser(String accessToken) {
        if (accessToken != null && !accessToken.isEmpty()) {
            given()
                    .header("Authorization", accessToken)
                    .when()
                    .delete("/auth/user")
                    .then()
                    .statusCode(202);
        }
    }

    public static String getAccessToken(String email, String password) {
        Response response = loginUser(email, password);
        if (response.getStatusCode() == 200) {
            return response.then().extract().path("accessToken");
        }
        return null;
    }

    // Метод для создания тестового пользователя
    public static TestUser createTestUser() {
        String email = TestData.getRandomEmail();
        String password = TestData.VALID_PASSWORD;
        String name = TestData.getRandomName();

        Response response = registerUser(email, password, name);
        if (response.getStatusCode() == 200) {
            String accessToken = getAccessToken(email, password);
            return new TestUser(email, password, name, accessToken);
        }
        return null;
    }

    public static class TestUser {
        public final String email;
        public final String password;
        public final String name;
        public final String accessToken;

        public TestUser(String email, String password, String name, String accessToken) {
            this.email = email;
            this.password = password;
            this.name = name;
            this.accessToken = accessToken;
        }
    }
}