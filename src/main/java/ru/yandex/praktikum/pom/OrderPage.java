package ru.yandex.praktikum.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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
    private By inputDay = By.xpath(".//div[@class='Dropdown-menu']/div[text()='сутки']");
    private By checkBoxColor = By.id("grey");
    private By InputComment = By.xpath("//input[@placeholder ='Комментарий для курьера']");
    private By buttonNext = By.cssSelector(".//div[starts-with(@class,'Order_NextButton')]/button");
    private By buttonOrder = By.xpath(".//div[starts-with(@class,'Order_Buttons')]/button[contains(text(), 'Заказать')]");
    private By buttonYes = By.xpath("//button[contains(text(), 'Да')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
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

    public WebElement getStation() {
        return driver.findElement(inputMetro);
    }

    public WebElement getCalendar() {
        return driver.findElement(inputCalendar);
    }

    public WebElement getDate() {
        return driver.findElement(inputDate);
    }

    public WebElement getPeriod() {
        return driver.findElement(inputPeriod);
    }

    public WebElement getDays() {
        return driver.findElement(inputDay);
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
