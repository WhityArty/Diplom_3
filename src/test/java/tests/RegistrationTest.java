package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import pages.*;
import utils.ApiClient;
import utils.TestData;
import dto.User;

import static org.junit.Assert.assertTrue;

@DisplayName("Тесты регистрации")
public class RegistrationTest extends BaseTest {

    private String createdUserEmail;
    private String createdUserAccessToken;

    @After
    public void cleanup() {
        // Удаляем пользователя, если он был создан
        if (createdUserEmail != null && createdUserAccessToken != null) {
            try {
                ApiClient.deleteUser(createdUserAccessToken);
                System.out.println("Пользователь удален: " + createdUserEmail);
            } catch (Exception e) {
                System.err.println("Ошибка при удалении пользователя " + createdUserEmail + ": " + e.getMessage());
            }
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации с валидными данными")
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        String randomName = TestData.getRandomName();
        String randomEmail = TestData.getRandomEmail();
        String password = TestData.VALID_PASSWORD;

        mainPage.clickLoginAccountButton();
        loginPage.waitForLoginPageLoad();
        loginPage.clickRegisterLink();

        registrationPage.waitForRegistrationPageLoad();
        registrationPage.fillRegistrationForm(randomName, randomEmail, password);
        registrationPage.clickRegisterButton();

        // Сохраняем данные для удаления
        createdUserEmail = randomEmail;

        // Получаем access token через API для последующего удаления
        User user = new User(randomEmail, password, randomName);
        createdUserAccessToken = ApiClient.getAccessToken(user);

        loginPage.waitForLoginPageLoad();
        assertTrue("Должна отображаться страница входа после регистрации",
                loginPage.isLoginPageVisible());
    }

    @Test
    @DisplayName("Ошибка при некорректном пароле")
    @Description("Проверка отображения ошибки при пароле менее 6 символов")
    public void testRegistrationWithInvalidPassword() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickLoginAccountButton();
        loginPage.waitForLoginPageLoad();
        loginPage.clickRegisterLink();

        registrationPage.waitForRegistrationPageLoad();
        registrationPage.fillRegistrationForm(
                TestData.getRandomName(),
                TestData.getRandomEmail(),
                TestData.INVALID_PASSWORD
        );
        registrationPage.clickRegisterButton();

        assertTrue("Должна отображаться ошибка валидации пароля",
                registrationPage.isPasswordErrorVisible());
    }
}