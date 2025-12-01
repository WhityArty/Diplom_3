package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.TestData;

import static org.junit.Assert.assertTrue;

@DisplayName("Тесты регистрации")
public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка успешной регистрации с валидными данными")
    public void testSuccessfulRegistration() {
        MainPage mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        String randomName = TestData.getRandomName();
        String randomEmail = TestData.getRandomEmail();

        mainPage.clickLoginAccountButton();
        loginPage.waitForLoginPageLoad();
        loginPage.clickRegisterLink();

        registrationPage.waitForRegistrationPageLoad();
        registrationPage.fillRegistrationForm(
                randomName,
                randomEmail,
                TestData.VALID_PASSWORD
        );
        registrationPage.clickRegisterButton();

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