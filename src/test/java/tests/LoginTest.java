package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.*;

import static org.junit.Assert.assertTrue;

@DisplayName("Тесты входа в систему")
public class LoginTest extends AuthorizedTest {

    @Test
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной")
    @Description("Проверка входа через основную кнопку на главной странице")
    public void testLoginFromMainPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.clickLoginAccountButton();
        loginPage.waitForLoginPageLoad();
        loginPage.fillLoginForm(userEmail, userPassword);
        loginPage.clickLoginButton();

        mainPage.clickPersonalAccountButton();
        profilePage.waitForProfilePageLoad();
        assertTrue("Должна отображаться страница профиля после входа",
                profilePage.isProfilePageVisible());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка входа через кнопку личного кабинета")
    public void testLoginFromPersonalAccountButton() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.clickPersonalAccountButton();
        loginPage.waitForLoginPageLoad();
        loginPage.fillLoginForm(userEmail, userPassword);
        loginPage.clickLoginButton();

        mainPage.clickPersonalAccountButton();
        profilePage.waitForProfilePageLoad();
        assertTrue("Должна отображаться страница профиля после входа",
                profilePage.isProfilePageVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа через ссылку на странице регистрации")
    public void testLoginFromRegistrationPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.clickLoginAccountButton();
        loginPage.clickRegisterLink();
        registrationPage.clickLoginLink();

        loginPage.waitForLoginPageLoad();
        loginPage.fillLoginForm(userEmail, userPassword);
        loginPage.clickLoginButton();

        mainPage.clickPersonalAccountButton();
        profilePage.waitForProfilePageLoad();
        assertTrue("Должна отображаться страница профиля после входа",
                profilePage.isProfilePageVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа через ссылку на странице восстановления пароля")
    public void testLoginFromPasswordRecoveryPage() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.clickLoginAccountButton();
        loginPage.waitForLoginPageLoad();
        loginPage.clickRecoverPasswordLink();
        passwordRecoveryPage.waitForRecoveryPageLoad();
        passwordRecoveryPage.clickLoginLink();

        loginPage.waitForLoginPageLoad();
        loginPage.fillLoginForm(userEmail, userPassword);
        loginPage.clickLoginButton();

        mainPage.clickPersonalAccountButton();
        profilePage.waitForProfilePageLoad();
        assertTrue("Должна отображаться страница профиля после входа",
                profilePage.isProfilePageVisible());
    }
}