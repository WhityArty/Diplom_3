package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/following-sibling::input")
    private WebElement nameInput;

    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private WebElement emailInput;

    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    private WebElement passwordInput;

    @FindBy(how = How.XPATH, using = "//button[text()='Зарегистрироваться']")
    private WebElement registerButton;

    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private WebElement loginLink;

    @FindBy(how = How.CLASS_NAME, using = "input__error")
    private WebElement errorMessage;

    @FindBy(how = How.XPATH, using = "//h2[text()='Регистрация']")
    private WebElement registerHeader;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод имени: {name}")
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOf(nameInput)).clear();
        nameInput.sendKeys(name);
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

    @Step("Клик на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Клик на ссылку 'Войти'")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Заполнение формы регистрации")
    public void fillRegistrationForm(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
    }

    @Step("Проверка видимости ошибки пароля")
    public boolean isPasswordErrorVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Получение текста ошибки")
    public String getErrorMessageText() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
        } catch (Exception e) {
            return "";
        }
    }

    @Step("Проверка видимости страницы регистрации")
    public boolean isRegistrationPageVisible() {
        try {
            waitForPageLoad();
            return wait.until(ExpectedConditions.visibilityOf(registerHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Ожидание загрузки страницы регистрации")
    public void waitForRegistrationPageLoad() {
        try {
            wait.until(ExpectedConditions.visibilityOf(registerHeader));
        } catch (Exception e) {
            wait.until(ExpectedConditions.urlContains("/register"));
        }
    }
}