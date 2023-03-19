package ru.yandex.praktikum.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class OrderPage {

    private WebDriver driver;

    // Используемые элементы на странице заказа
    private By orderHeader = By.xpath("//div[(text()= 'Для кого самокат')]");
    private By rentHeader = By.xpath("//div[(text()= 'Про аренду')]");
    private By inputName = By.xpath("//input[@placeholder ='* Имя']");
    private By inputSurame = By.xpath("//input[@placeholder ='* Фамилия']");
    private By inputAddress = By.xpath("//input[@placeholder ='* Адрес: куда привезти заказ']");
    private By inputPhone = By.xpath("//input[@placeholder ='* Телефон: на него позвонит курьер']");
    private By inputMetro = By.xpath("//input[@placeholder ='* Станция метро']");
    private By inputCalendar = By.xpath("//input[@placeholder ='* Когда привезти самокат']");
    private By inputDate = By.xpath("//div[contains(@class, 'day--today')]");
    private By inputPeriod = By.className("Dropdown-placeholder");
    private By checkBoxColor = By.id("grey");
    private By InputComment = By.xpath("//input[@placeholder ='Комментарий для курьера']");
    private By buttonNext = By.xpath(".//div[starts-with(@class,'Order_NextButton')]//button[contains(text(), 'Далее')]");
    private By buttonOrder = By.xpath(".//div[starts-with(@class,'Order_Buttons')]//button[contains(text(), 'Заказать')]");
    private By buttonYes = By.xpath("//button[contains(text(), 'Да')]");
    public By orderPlaced = By.xpath("//div[(text()= 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadOrderPage() {
        //new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(buttonNext).isDisplayed()));
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(getTitleOrder()));
    }

    public void setDataFieldsAndClickNext(String valueName, String valueSurname, String valueAddress,
                                          String valueMetro, String valuePhone) {
        getName().sendKeys(valueName);
        getSurname().sendKeys(valueSurname);
        getAddress().sendKeys(valueAddress);
        getMetro().sendKeys(valueMetro, Keys.ARROW_DOWN, Keys.ENTER);
        getPhoneNumber().sendKeys(valuePhone);
        getButtonNext().click();
    }

    public void setOtherFieldsAndClickOrder(String valueDateOrder, String valuePeriod, String valueComment) {
        //getCalendar().click();
        getCalendar().sendKeys(valueDateOrder, Keys.ARROW_DOWN, Keys.ENTER);
        getPeriod().click();
        getDays(valuePeriod).click();
        getColor().click();
        getComment().sendKeys(valueComment);
        getButtonOrder().click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(driver -> (getButtonYes().isDisplayed()));
        getButtonYes().click();
    }

    public By getTitleOrder() {
        return orderHeader;
    }

    public By getTitleRent() {
        return rentHeader;
    }

    public WebElement getName() {
        return driver.findElement(inputName);
    }

    public WebElement getSurname() {
        return driver.findElement(inputSurame);
    }

    public WebElement getAddress() {
        return driver.findElement(inputAddress);
    }

    public WebElement getPhoneNumber() {
        return driver.findElement(inputPhone);
    }

    public WebElement getMetro() {
        return driver.findElement(inputMetro);
    }

    public WebElement getCalendar() {
        return driver.findElement(inputCalendar);
    }

    public WebElement getPeriod() {
        return driver.findElement(inputPeriod);
    }

    public WebElement getDays(String valueDays) {
        return driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[text()='"+valueDays+"']"));
    }

    public WebElement getColor() {
        return driver.findElement(checkBoxColor);
    }

    public WebElement getComment() {
        return driver.findElement(InputComment);
    }

    public WebElement getButtonNext() {
        return driver.findElement(buttonNext);
    }

    public WebElement getButtonOrder() {
        return driver.findElement(buttonOrder);
    }

    public WebElement getButtonYes() {
        return driver.findElement(buttonYes);
    }

}
