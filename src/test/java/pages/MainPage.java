package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private WebElement loginAccountButton;

    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    private WebElement personalAccountButton;

    @FindBy(how = How.CLASS_NAME, using = "BurgerIngredients_ingredients__1N8v2")
    private WebElement mainContent;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Булки']/..")
    private WebElement bunsSection;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Соусы']/..")
    private WebElement saucesSection;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Начинки']/..")
    private WebElement fillingsSection;

    @FindBy(how = How.XPATH, using = "//p[text()='Конструктор']")
    private WebElement constructorLink;

    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    private WebElement logo;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик на кнопку 'Войти в аккаунт'")
    public void clickLoginAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginAccountButton)).click();
    }

    @Step("Клик на кнопку 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    @Step("Клик на раздел 'Булки' через JavaScript")
    public void clickBunsSection() {
        // Используем JavaScript для клика, чтобы обойти проблемы с перекрытием
        wait.until(ExpectedConditions.elementToBeClickable(bunsSection));
        js.executeScript("arguments[0].click();", bunsSection);
    }

    @Step("Клик на раздел 'Соусы' через JavaScript")
    public void clickSaucesSection() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesSection));
        js.executeScript("arguments[0].click();", saucesSection);
    }

    @Step("Клик на раздел 'Начинки' через JavaScript")
    public void clickFillingsSection() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsSection));
        js.executeScript("arguments[0].click();", fillingsSection);
    }

    @Step("Клик на 'Конструктор'")
    public void clickConstructorLink() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorLink)).click();
    }

    @Step("Клик на логотип")
    public void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
    }

    @Step("Проверка видимости главной страницы")
    public boolean isMainPageVisible() {
        try {
            waitForPageLoad();
            return wait.until(ExpectedConditions.visibilityOf(mainContent)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка что пользователь авторизован")
    public boolean isUserLoggedIn() {
        try {
            WebElement orderButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//button[contains(text(), 'Оформить заказ')]")));
            return orderButton.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка активности раздела 'Булки'")
    public boolean isBunsSectionActive() {
        try {
            WebElement activeSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[text()='Булки']")));
            return activeSection.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка активности раздела 'Соусы'")
    public boolean isSaucesSectionActive() {
        try {
            WebElement activeSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[text()='Соусы']")));
            return activeSection.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверка активности раздела 'Начинки'")
    public boolean isFillingsSectionActive() {
        try {
            WebElement activeSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]//span[text()='Начинки']")));
            return activeSection.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}