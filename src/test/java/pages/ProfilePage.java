package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Профиль')]")
    private WebElement profileLink;

    @FindBy(how = How.XPATH, using = "//button[contains(text(), 'Выход')]")
    private WebElement logoutButton;

    @FindBy(how = How.XPATH, using = "//p[contains(text(), 'Конструктор')]")
    private WebElement constructorButton;

    @FindBy(how = How.XPATH, using = "//a[contains(@href, '/account/profile')]")
    private WebElement profileNavLink;

    @FindBy(how = How.XPATH, using = "//h2[contains(text(), 'В этом разделе')]")
    private WebElement profileSectionHeader;

    // Добавим поле для имени пользователя для более надежной проверки
    @FindBy(how = How.XPATH, using = "//input[@name='name']")
    private WebElement nameInput;

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик на кнопку 'Выход'")
    public void clickLogoutButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    @Step("Клик на 'Конструктор'")
    public void clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

//    @Step("Проверка видимости страницы профиля")
//    public boolean isProfilePageVisible() {
//        waitForPageLoad();
//        try {
//            // Пробуем разные способы проверки
//            if (waitForElement(profileSectionHeader, 5)) {
//                return true;
//            }
//            if (waitForElement(profileNavLink, 5)) {
//                return true;
//            }
//            if (waitForElement(nameInput, 5)) {
//                return true;
//            }
//            return false;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    @Step("Ожидание загрузки страницы профиля")
    public void waitForProfilePageLoad() {
        try {
            // Ждем загрузки по URL
            waitForUrl("/account/profile");
            waitForPageLoad();

            // Ждем появления ЛЮБОГО из элементов профиля
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOf(profileSectionHeader),
                    ExpectedConditions.visibilityOf(profileNavLink),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='name']")),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='text']")),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(), 'Профиль')]"))
            ));
        } catch (Exception e) {
            // Логируем ошибку для отладки
            System.out.println("Ошибка загрузки страницы профиля: " + e.getMessage());
            throw e;
        }
    }

    @Step("Проверка видимости страницы профиля")
    public boolean isProfilePageVisible() {
        try {
            // Проверяем несколько возможных индикаторов
            return driver.getCurrentUrl().contains("/account/profile") ||
                    waitForElement(profileSectionHeader, 5) ||
                    waitForElement(profileNavLink, 5) ||
                    driver.findElements(By.xpath("//input[@name='name']")).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}