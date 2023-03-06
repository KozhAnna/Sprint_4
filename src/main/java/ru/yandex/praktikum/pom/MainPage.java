package ru.yandex.praktikum.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration; // Импорт для работы со временем
import java.util.List; // Импорт для работы с List

public class MainPage {

    private WebDriver driver;

    // локатор ссылки лого
    private By headerLogo = By.xpath(".//a[starts-with(@class,'Header_LogoScooter')]");
    // локатор кнопки заказа верхней
    private By buttonOrderTop = By.xpath(".//div[starts-with(@class,'Header_Nav')]//button[text()='Заказать']");
    // локатор кнопки заказа нижней
    private By buttonOrderBottom = By.xpath(".//div[contains(@class,'FinishButton')]//button[text()='Заказать']");
    // секция Вопросы о важном
    private By sectionFaq = By.xpath(".//div[starts-with(@class,'Home_FAQ')]");
    // элемент секции
    private By accordionItem = By.className("accordion__item");
    // кнопка с вопросом
    private By accordionButton = By.className("accordion__button");
    // панель с ответом
    private By accordionPanel = By.className("accordion__panel");

    // конструктор класса
    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    // ждем пока прогрузятся вопросы о важном
    public void waitForLoadFaq() {
        WebElement faqElement = driver.findElement(sectionFaq);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(sectionFaq));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", faqElement);
    }

    // возвращает список всех вопросов-ответов
    public List<WebElement> getFaqItems(){
       return driver.findElements(accordionItem);
    }
}
