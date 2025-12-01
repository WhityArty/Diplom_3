package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pages.MainPage;

import static org.junit.Assert.assertTrue;

@DisplayName("Тесты раздела Конструктор")
public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка перехода к разделу с булками")
    public void testNavigateToBunsSection() {
        MainPage mainPage = new MainPage(driver);

        // Кликаем на другой раздел, затем возвращаемся к булкам для проверки
        mainPage.clickSaucesSection();
        mainPage.clickBunsSection();

        assertTrue("Раздел 'Булки' должен быть активен",
                mainPage.isBunsSectionActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка перехода к разделу с соусами")
    public void testNavigateToSaucesSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesSection();
        assertTrue("Раздел 'Соусы' должен быть активен",
                mainPage.isSaucesSectionActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка перехода к разделу с начинками")
    public void testNavigateToFillingsSection() {
        MainPage mainPage = new MainPage(driver);

        mainPage.clickFillingsSection();
        assertTrue("Раздел 'Начинки' должен быть активен",
                mainPage.isFillingsSectionActive());
    }
}