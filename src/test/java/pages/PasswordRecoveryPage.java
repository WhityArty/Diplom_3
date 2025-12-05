package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PasswordRecoveryPage extends BasePage {

    @FindBy(how = How.XPATH, using = "//a[text()='Войти']")
    private WebElement loginLink;

    @FindBy(how = How.XPATH, using = "//h2[text()='Восстановление пароля']")
    private WebElement recoveryHeader;

    public PasswordRecoveryPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик на ссылку 'Войти' на странице восстановления пароля")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Проверка видимости страницы восстановления пароля")
    public boolean isRecoveryPageVisible() {
        try {
            waitForPageLoad();
            return wait.until(ExpectedConditions.visibilityOf(recoveryHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Ожидание загрузки страницы восстановления пароля")
    public void waitForRecoveryPageLoad() {
        wait.until(ExpectedConditions.visibilityOf(recoveryHeader));
    }
}