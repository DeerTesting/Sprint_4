package ru.yandex.praktikum.ru.pageobject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class OrderRentForm {
    private WebDriver driver;
    public OrderRentForm(WebDriver driver){
        this.driver = driver;
    }
    //Поле ввода даты
    private By orderDate = By.xpath(".//input[contains(@placeholder, 'Когда привезти самокат') ]");
    //Поле выбора срока
    private By rentLength = By.xpath(".//div[contains(text(), 'Срок аренды') ]");
    //Кнопка заказа
    private By orderButton = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text() = 'Заказать' ]");
    //Кнопка подтверждения заказа
    private By yesButton = By.xpath(".//button[text() = 'Да' ]");
    //Выпадающий список доступного срока аренды
    private String rentDurationList = ".//div[text() = '%s']";

    public void scroll(String length){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(String.format(rentDurationList, length))));
    }
    public void fillOrderRentForm(String date, String length){
        driver.findElement(orderDate).sendKeys(date);
        driver.findElement(orderDate).sendKeys(Keys.ENTER);
        driver.findElement(rentLength).click();
        scroll(length);
        driver.findElement(By.xpath(String.format(rentDurationList, length))).click();
    }

    public void orderClick(){
        driver.findElement(orderButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(yesButton));
    }

    public boolean yesClick(){
        driver.findElement(yesButton).click();
        return driver.findElement(By.xpath(".//div[contains(text(), 'Заказ оформлен')]")).isDisplayed();
    }
}
