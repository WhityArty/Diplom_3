package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Зарегистрироваться']")
    private WebElement registerLink;

    @FindBy(how = How.XPATH, using = "//a[text()='Восстановить пароль']")
    private WebElement recoverPasswordLink;

    @FindBy(how = How.XPATH, using = "//h2[text()='Вход']")
    private WebElement loginHeader;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод email: {email}")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(emailInput)).clear();
        emailInput.sendKeys(email);
    }

    @Step("Ввод пароля: {password}")
    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).clear();
        passwordInput.sendKeys(password);
    }

    @Step("Клик на кнопку 'Войти'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Клик на ссылку 'Зарегистрироваться'")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Клик на ссылку 'Восстановить пароль'")
    public void clickRecoverPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(recoverPasswordLink)).click();
    }

    @Step("Заполнение формы входа")
    public void fillLoginForm(String email, String password) {
        enterEmail(email);
        enterPassword(password);
    }

    @Step("Проверка видимости страницы входа")
    public boolean isLoginPageVisible() {
        try {
            waitForPageLoad();
            return wait.until(ExpectedConditions.visibilityOf(loginHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Ожидание загрузки страницы входа")
    public void waitForLoginPageLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOf(loginHeader));
        } catch (Exception e) {
            // Альтернативная проверка по URL
            wait.until(ExpectedConditions.urlContains("/login"));
        }
    }
}