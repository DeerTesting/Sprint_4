package ru.yandex.praktikum.ru;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class WebDriverFactory {

    public WebDriver getWebDriver(String browserType){
        if(browserType.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        else {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
    }

    public void setuodriver(String driver){
        if(driver.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
        }
        else {
            WebDriverManager.firefoxdriver().setup();
        }

    }
}
