import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class SampleTest {
    public WebDriver driver;

    @Test
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
    }
}