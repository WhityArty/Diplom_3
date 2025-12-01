package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import utils.TestData;

import static org.junit.Assert.assertTrue;

@DisplayName("Тесты навигации")
public class NavigationTest extends BaseTest {

    @Test
    @DisplayName("Переход в личный кабинет")
    @Description("Проверка перехода в личный кабинет по клику")
    public void testNavigateToPersonalAccount() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.clickLoginAccountButton();
        loginPage.waitForLoginPageLoad();
        loginPage.fillLoginForm(TestData.getTestEmail(), TestData.getTestPassword());
        loginPage.clickLoginButton();

        assertTrue("Пользователь должен быть авторизован",
                mainPage.isUserLoggedIn());

        mainPage.clickPersonalAccountButton();
        profilePage.waitForProfilePageLoad();
        assertTrue("Должна отображаться страница профиля",
                profilePage.isProfilePageVisible());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    @Description("Проверка перехода обратно в конструктор из личного кабинета")
    public void testNavigateFromProfileToConstructor() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.clickLoginAccountButton();
        loginPage.waitForLoginPageLoad();
        loginPage.fillLoginForm(TestData.getTestEmail(), TestData.getTestPassword());
        loginPage.clickLoginButton();

        assertTrue("Пользователь должен быть авторизован",
                mainPage.isUserLoggedIn());

        mainPage.clickPersonalAccountButton();
        profilePage.waitForProfilePageLoad();
        assertTrue("Должна отображаться страница профиля",
                profilePage.isProfilePageVisible());

        profilePage.clickConstructorButton();
        assertTrue("Должна отображаться главная страница",
                mainPage.isMainPageVisible());
    }

    @Test
    @DisplayName("Переход по клику на логотип")
    @Description("Проверка перехода на главную по клику на логотип")
    public void testNavigateByLogoClick() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        mainPage.clickLoginAccountButton();
        loginPage.waitForLoginPageLoad();
        loginPage.fillLoginForm(TestData.getTestEmail(), TestData.getTestPassword());
        loginPage.clickLoginButton();

        assertTrue("Пользователь должен быть авторизован",
                mainPage.isUserLoggedIn());

        mainPage.clickPersonalAccountButton();
        profilePage.waitForProfilePageLoad();
        assertTrue("Должна отображаться страница профиля",
                profilePage.isProfilePageVisible());

        mainPage.clickLogo();
        assertTrue("Должна отображаться главная страница",
                mainPage.isMainPageVisible());
    }
}