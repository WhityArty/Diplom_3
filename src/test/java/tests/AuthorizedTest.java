package tests;

import io.qameta.allure.Step;
import org.junit.After;
import org.junit.Before;
import utils.ApiClient;

public class AuthorizedTest extends BaseTest {

    protected ApiClient.TestUser testUser;
    protected String userEmail;
    protected String userPassword;
    protected String userName;

    @Before
    @Step("Создание тестового пользователя")
    public void createTestUser() {
        testUser = ApiClient.createTestUser();
        if (testUser == null) {
            throw new RuntimeException("Не удалось создать тестового пользователя");
        }
        userEmail = testUser.email;
        userPassword = testUser.password;
        userName = testUser.name;
        System.out.println("Создан тестовый пользователь: " + userEmail);
    }

    @After
    @Step("Удаление тестового пользователя")
    public void deleteTestUser() {
        if (testUser != null && testUser.accessToken != null) {
            try {
                ApiClient.deleteUser(testUser.accessToken);
                System.out.println("Тестовый пользователь удален: " + userEmail);
            } catch (Exception e) {
                System.err.println("Ошибка при удалении пользователя " + userEmail + ": " + e.getMessage());
            }
        }
    }
}