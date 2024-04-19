package ru.yandex.praktikum.ru.pageobject;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    //Текст Самокат на главной странице
    //$x(".//[div[@class = 'Home_Header__iJKdX']")
    private By SamokatText = By.className("Home_Header__iJKdX");
    //Кнопка "Заказть" наверху
    //$x(".//button[@class = 'Button_Button__ra12g']")
    private By UpperOrder = By.xpath(".//div[@class = 'Header_Nav__AGCXC']/button[text() = 'Заказать']");

    //Кнопка "Заказать" снизу
    //$x(.//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']")
    private By DownOrder = By.xpath(".//button[contains(@class, 'Button_Middle__1CSJM')]");

    //кнопка согласия куки X$(".//button[@id = 'rcc-confirm-button']")
    private By CookieButton = By.id("rcc-confirm-button");
    //Список вопросов
    //$x(".//div[@class = 'accordion__item'])
    private By Questions = By.xpath(".//div[contains(@class, 'accordion__item')]");
    //Список ответов
    //$x(".//div[@class = 'accordion__panel']/p")
    private String InnerListText = ".//div[contains(@id, 'accordion__panel')][.='%s']";

    public void closeCookie(){
        driver.findElement(CookieButton).click();
    }

    public void scroll(){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(DownOrder));
    }
    public void waitOpenBrowser(){
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(SamokatText));
    }
    public void clickUpperOrder(){
        driver.findElement(UpperOrder).click();
    }

    public void clickDownOrder(){
        scroll();
        driver.findElement(DownOrder).click();
    }

    public void openQuestion(int order){
        List<WebElement> questions = driver.findElements(Questions);
        questions.get(order).click();
    }

    public boolean visibleAnswer(String ExpectAnswer){
        WebElement lists = driver.findElement(By.xpath(String.format(InnerListText, ExpectAnswer)));
        return lists.isDisplayed();
    }
}
