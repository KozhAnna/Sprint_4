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
    private By accordionPanel = By.className("accordion__panel"); // панель с ответом
    private By imageScooter = By.xpath(".//img[@alt = 'Scooter blueprint']");
    private By buttonAcceptCookie = By.id("rcc-confirm-button");

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

    public void waitForLoadPage() {
        WebElement imageElement = driver.findElement(imageScooter);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(imageScooter));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", imageElement);
    }
    public boolean isElementExist(By locatorBy) {
        try {
            driver.findElement(locatorBy);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
    // возвращает список всех вопросов-ответов
    public List<WebElement> getFaqItems(){
       return driver.findElements(accordionItem);
    }

    public boolean isButtonClickable(WebElement faqElement) {
        return faqElement.findElement(accordionButton).isEnabled();
    }

    public String getQuestion(WebElement faqElement) {
        return faqElement.findElement(accordionButton).getText();
    }

    public String getAnswer(WebElement faqElement) {
        return faqElement.findElement(accordionPanel).getText();
    }

    public void clickOrder(int indexButton) {
        switch (indexButton) {
            case 0:
                driver.findElement(buttonOrderTop).click();
                break;
            case 1:
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                WebElement buttonOrder = driver.findElement(buttonOrderBottom);
                new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (buttonOrder.isDisplayed()));
                buttonOrder.click();
                break;
        }
    }
    public void clickGetCookie() {
        if (isElementExist(buttonAcceptCookie))
            driver.findElement(buttonAcceptCookie).click();
    }

}
