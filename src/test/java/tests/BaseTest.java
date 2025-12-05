package tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ConfigReader;
import utils.TestData;

import java.io.File;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        System.out.println("=== ЗАПУСК ТЕСТОВ В БРАУЗЕРЕ: " + browser.toUpperCase() + " ===");

        if ("yandex".equalsIgnoreCase(browser)) {
            setupYandexBrowser();
        } else {
            setupChromeBrowser();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        String baseUrl = TestData.BASE_URL;
        System.out.println("Переход на страницу: " + baseUrl);
        driver.get(baseUrl);

        String title = driver.getTitle();
        System.out.println("Заголовок страницы: " + title);
        System.out.println("Текущий URL: " + driver.getCurrentUrl());
    }

    private void setupChromeBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-extensions");
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        driver = new ChromeDriver(options);
    }

    private void setupYandexBrowser() {
        try {
            String yandexDriverPath = ConfigReader.getYandexDriverPath();
            String yandexBrowserPath = ConfigReader.getYandexBrowserPath();

            File driverFile = new File(yandexDriverPath);
            if (!driverFile.exists()) {
                throw new RuntimeException("YandexDriver не найден по пути: " + yandexDriverPath);
            }

            if (!driverFile.canExecute()) {
                driverFile.setExecutable(true);
            }

            System.out.println("Используем YandexDriver по пути: " + yandexDriverPath);

            ChromeDriverService service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(driverFile)
                    .build();

            ChromeOptions options = new ChromeOptions();
            options.setBinary(yandexBrowserPath);
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.addArguments("--disable-extensions");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.addArguments("--user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 YaBrowser/24.1.0.0 Safari/537.36");

            driver = new ChromeDriver(service, options);
        } catch (Exception e) {
            throw new RuntimeException("Не удалось запустить YandexBrowser. Убедитесь что: \n" +
                    "1. Создан файл config.properties в src/main/resources/\n" +
                    "2. В файле указаны корректные пути:\n" +
                    "   yandex.driver.path=/ваш/путь/к/yandexdriver\n" +
                    "   yandex.browser.path=/ваш/путь/к/yandex/browser\n" +
                    "Ошибка: " + e.getMessage(), e);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}