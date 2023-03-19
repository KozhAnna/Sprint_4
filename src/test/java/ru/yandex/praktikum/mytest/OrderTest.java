package ru.yandex.praktikum.mytest;

import org.junit.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import ru.yandex.praktikum.pom.MainPage;
import ru.yandex.praktikum.pom.OrderPage;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@RunWith(Parameterized.class)
public class OrderTest {

    private WebDriver driver;
    public MainPage objMainPage;
    public OrderPage objOrderPage;
    private final By buttonCheckOrder = By.xpath(".//button[text() = 'Посмотреть статус']");

    private final int indexButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String dateOrder;
    private final String period;
    private final String comment;

    public OrderTest(int indexButton, String name, String surname, String address, String metro,
                            String phone, String dateOrder, String period, String comment) {
        this.indexButton = indexButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.dateOrder = dateOrder;
        this.period = period;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {0, "Аня", "Богданова", "Москва", "Кунцевская", "+7901123456", "10.03.2023", "трое суток", "Проверка 1"},
                {1, "Ваня", "Богданов", "Москва", "Солнцево", "+790234567", "20.03.2023", "сутки", "Проверка 2"}
        };
    }

    @BeforeClass
    public void initialOrder() {

        driver = new ChromeDriver();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver = new Augmenter().augment(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        objMainPage = new MainPage(driver);
        objMainPage.waitForLoadFaq();
        objOrderPage = new OrderPage(driver);
    }
    @Test
    public void testOrder() {

    }

    @AfterClass
    public void tearDown() {
        if (driver!=null)
            driver.quit();
    }
}
