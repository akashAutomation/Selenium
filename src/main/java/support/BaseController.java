package support;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


public class BaseController {
    public static WebDriver driver;

    public void initDriver() {
        System.out.println("------init----------");
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\akash\\Downloads\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public void tearDown() {
        driver.quit();
    }

}
