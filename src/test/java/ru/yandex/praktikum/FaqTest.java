package ru.yandex.praktikum;

import ru.yandex.praktikum.pom.MainPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Test;
import org.junit.After;

// Импорт для работы с List
import java.util.List;

public class FaqTest {

    private WebDriver driver;

    @Test
    public void test() {

        // Открываем страничку
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");


        MainPage objMainPage = new MainPage(driver);

        objMainPage.waitForLoadFaq();

        // найдем все вопросы
        List<WebElement> elements = objMainPage.getFaqItems();

        System.out.println(elements.size());
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
