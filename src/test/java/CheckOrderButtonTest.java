import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.ru.WebDriverFactory;
import ru.yandex.praktikum.ru.pageobject.MainPage;
import ru.yandex.praktikum.ru.pageobject.OrderNameForm;
import ru.yandex.praktikum.ru.pageobject.OrderRentForm;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class CheckOrderButtonTest {

    private final String BROWSER = "chrome";
    private WebDriverFactory webDriverFactory= new WebDriverFactory();
    private WebDriver driver = webDriverFactory.getWebDriver(BROWSER);;

    @Before
    public void startUp() {
        webDriverFactory.setuodriver(BROWSER);
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    MainPage mainPage = new MainPage(driver);
    OrderNameForm order = new OrderNameForm(driver);
    OrderRentForm rent = new OrderRentForm(driver);
    private String expectedHead = "Для кого самокат";
    private String expectedUrl = "https://qa-scooter.praktikum-services.ru/order";



    @Test
    public void UpperButtonOrderTest(){
        mainPage.waitOpenBrowser();
        mainPage.clickUpperOrder();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.urlMatches(expectedUrl));
        assertEquals(expectedHead, driver.findElement(By.className("Order_Header__BZXOb")).getText());
    }

    @Test
    public void DownButtonOrderTest(){
        mainPage.waitOpenBrowser();
        mainPage.clickDownOrder();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.urlMatches(expectedUrl));
        assertEquals(expectedHead, driver.findElement(By.className("Order_Header__BZXOb")).getText());
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
