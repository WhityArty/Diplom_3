package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import utils.TestData;

import static org.junit.Assert.assertTrue;

@DisplayName("Тесты выхода из системы")
public class LogoutTest extends BaseTest {

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка выхода по кнопке 'Выйти' в личном кабинете")
    public void testLogout() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        // Вход в систему
        mainPage.clickLoginAccountButton();
        loginPage.waitForLoginPageLoad();
        loginPage.fillLoginForm(TestData.getTestEmail(), TestData.getTestPassword());
        loginPage.clickLoginButton();

        // Переход в личный кабинет
        mainPage.clickPersonalAccountButton();
        profilePage.waitForProfilePageLoad();
        assertTrue("Должна отображаться страница профиля",
                profilePage.isProfilePageVisible());

        // Выход из системы
        profilePage.clickLogoutButton();

        // Проверяем, что вернулись на страницу входа
        loginPage.waitForLoginPageLoad();
        assertTrue("Должна отображаться страница входа после выхода",
                loginPage.isLoginPageVisible());
    }
}