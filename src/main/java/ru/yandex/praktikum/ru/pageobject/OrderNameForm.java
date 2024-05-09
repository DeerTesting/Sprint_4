package ru.yandex.praktikum.ru.pageobject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class OrderNameForm {
    private WebDriver driver;

    public OrderNameForm(WebDriver driver){
        this.driver = driver;
    }

    //кнопка согласия куки X$(".//button[@id = 'rcc-confirm-button']")
    private By cookiButton = By.id("rcc-confirm-button");

    //Поле для ввода имени $x(".//input[contains(@placeholder ,'* Имя')]")
    private By nameInput = By.xpath(".//input[contains(@placeholder ,'Имя')]");

    //Поле для ввода фамилии $x(".//input[@placeholder = 'Фамилия']")
    private By surenameInput = By.xpath(".//input[contains(@placeholder ,'Фамилия')]");

    //Поле для ввода фдреса $x(".//input[@placeholder = 'Адрес']")
    private By addressInput = By.xpath(".//input[contains(@placeholder ,'Адрес')]");

    //Поле для ввода номера телефона $x(".//input[@placeholder = 'Телефон']")
    private By phoneInput = By.xpath(".//input[contains(@placeholder ,'Телефон')]");

    //Поле для ввода станции $x(".//input[@placeholder = 'Станция метро']")
    private By metroStationInput = By.xpath(".//input[contains(@placeholder ,'Станция метро')]");
    //Поле выбора станции метро
    private String metroStationName = ".//div[text() ='%s']";
    //Кнопка перехрда на следющую форму
    private By nextButton = By.xpath(".//button[text() = 'Далее']");

    public void fillData(String name, String surname, String address, String nameStation, String phone){
        driver.findElement(nameInput).sendKeys(name);
        driver.findElement(surenameInput).sendKeys(surname);
        driver.findElement(addressInput).sendKeys(address);
        setMetroStationInput(nameStation);
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void closeCookie(){
        driver.findElement(cookiButton).click();
    }

    public void setMetroStationInput(String nameStation){
        driver.findElement(metroStationInput).sendKeys(nameStation);
        driver.findElement(By.xpath(String.format(metroStationName, nameStation))).click();
    }

    public void clickNextButton(){
        driver.findElement(nextButton).click();
    }


}
