package ru.yandex.praktikum.mytest;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.pom.MainPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import java.util.List; // Импорт для работы с List
import java.util.concurrent.TimeUnit;
import static org.hamcrest.CoreMatchers.containsString;
import org.openqa.selenium.remote.Augmenter;

@RunWith(Parameterized.class)
public class FaqTest {

    public static WebDriver driver;
    public static MainPage objMainPage;
    public static List<WebElement> faqElements;
    private final int index;
    private final String checkedText;
    private static boolean isDebuging;

    public FaqTest(int index, String checkedText) {
        this.index = index;
        this.checkedText = checkedText;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},

        };
    }
    @BeforeClass
    public static void initialSetup() {

        isDebuging = false;

        // Открываем страничку
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver = new Augmenter().augment(driver);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        objMainPage = new MainPage(driver);
        objMainPage.waitForLoadFaq();

        // найдем все вопросы
        faqElements = objMainPage.getFaqItems();

        if (isDebuging)
            System.out.println("Количество вопросов: "+faqElements.size());
    }

    @Test
    public void myFaqTest() {

        WebElement faqElement = faqElements.get(index);

        boolean buttonClickable = objMainPage.isButtonClickable(faqElement);
        assertTrue("Элемент "+index+" не кликабелен", buttonClickable);

        if (buttonClickable != true) return;

        faqElement.click();

        String faqQuestion;
        faqQuestion = objMainPage.getQuestion(faqElement);
        String faqAnswer;
        faqAnswer = objMainPage.getAnswer(faqElement);

        if (isDebuging) {
            System.out.println(faqQuestion);
            System.out.println(faqAnswer);
        }

        MatcherAssert.assertThat("Текст не совпадает: ", faqAnswer, containsString(checkedText));
    }

    @AfterClass
    public static void teardown() {
        if (driver!=null)
            driver.quit();
    }
}
