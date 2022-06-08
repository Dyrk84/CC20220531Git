import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;

public class SampleTest {
    public WebDriver driver;

    @Test
    //https://www.seleniumeasy.com/selenium-tutorials/allure-report-example-with-annotations
    @Epic("Telex")
    @Story("Telex Landing Page")
    @Description("Navigation to the landing page")
    @Severity(SeverityLevel.CRITICAL)

    public void testNavigation() {
        WebDriverManager.chromedriver().setup(); //a webdriveren keresztül tudjuk állítani a chromedriver kapcsolóit a feltelepített chromehoz
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox"); //veszélyesnek ítélt JS scripteket nem futtatja le, ha sandboxban van, ezt tudjuk ezzel kikapcsolni, hogy mindent lefuttasson
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--headless"); //nem jön fel a gui, hanem létrehoz egy virtuális gépet a háttérben, azon fut a chrome, és ott futtatja a teszteket. nem mutatja, nem lehet látni. Ha látni szeretném, ki kell ezt kapcsolni
        options.addArguments("--window-size=1920,1080"); //ez beállítja a teszteléshez használni kívánt méretét a browsernek.
        options.addArguments("start-maximized"); //kiteszi maximális méretre a browsert. az előző fontos, ez kevésbé.
        driver = new ChromeDriver(options); //nem kell hozzá letölteni chromedriver.exe-t, mert megoldja azt a webdriver().chromedriver()
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.navigate().to("https://telex.hu");
        //https://stackoverflow.com/questions/58728908/is-it-possible-to-have-screenshots-of-allure-report-between-steps-like-extent-re#:~:text=All%20%40Step%20s%20with%20screenshot,get%20screenshot%20as%20you%20need.&text=But%20it's%20not%20possible%20to%20have%20it%20for%20Allure.
        Allure.addAttachment("Any text", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES))); //screenshotot csinál!!!
    }
}