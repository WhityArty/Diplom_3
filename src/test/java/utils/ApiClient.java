package utils;

import dto.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiClient {

    private static final String BASE_URL = TestData.API_URL;

    static {
        RestAssured.baseURI = BASE_URL;
    }

    public static Response registerUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/auth/register");
    }

    public static Response loginUser(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
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

    public static String getAccessToken(User user) {
        Response response = loginUser(user);
        if (response.getStatusCode() == 200) {
            return response.then().extract().path("accessToken");
        }
        return null;
    }

    // Метод для создания тестового пользователя
    public static TestUser createTestUser() {
        User user = new User(
                TestData.getRandomEmail(),
                TestData.VALID_PASSWORD,
                TestData.getRandomName()
        );

        Response response = registerUser(user);
        if (response.getStatusCode() == 200) {
            String accessToken = getAccessToken(user);
            return new TestUser(user.getEmail(), user.getPassword(), user.getName(), accessToken);
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