import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.ru.WebDriverFactory;
import ru.yandex.praktikum.ru.pageobject.OrderNameForm;
import ru.yandex.praktikum.ru.pageobject.OrderRentForm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class FillFormTest {
    private final String name;
    private final String surname;
    private final String address;
    private final String metrostation;
    private final String phone;
    private final String date;
    private final String duration;

    public FillFormTest(String name, String surname, String address, String metrostation, String phone, String date, String duration){
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metrostation = metrostation;
        this.phone = phone;
        this.date = date;
        this.duration = duration;
    }

    @Parameterized.Parameters(name = "{index}. rightphone{4}, wrongPhoneNumber{4}")
    public static Object[][] getOuterListText(){
        return new Object[][]{
                {"Анна", "Карпова", "Киевская 15", "ВДНХ", "79211801818", "11.11.2024","семеро суток"},
                {"Максим", "Огаров", "Тверская-Ямская 15", "Белорусская", "150150", "10/09/2017","сутки"},
        };
    }

    private final String BROWSER = "chrome";
    private WebDriverFactory webDriverFactory= new WebDriverFactory();
    private WebDriver driver = webDriverFactory.getWebDriver(BROWSER);;

    @Before
    public void startUp() {
        webDriverFactory.setuodriver(BROWSER);
        driver.get("https://qa-scooter.praktikum-services.ru/order");
    }

    OrderNameForm order = new OrderNameForm(driver);
    OrderRentForm rent = new OrderRentForm(driver);

    @Test
    public void FillNameForm(){
        order.closeCookie();
        order.fillData(name, surname, address, metrostation, phone);
        order.clickNextButton();
        rent.fillOrderRentForm(date, duration);
        rent.orderClick();
        boolean result = rent.yesClick();
        assertTrue(result);
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
