package support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class WebTableHandle {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\akash\\Downloads\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.w3schools.com/html/html_tables.asp");

        List<WebElement> rows = driver.findElements(By.xpath("//table[@id='customers']//tr"));

        for(int j=2;j<rows.size();j++){
            String col = "//table[@id='customers']//tr["+j+"]/td";
            List<WebElement> c = driver.findElements(By.xpath(col));
            for(int i=0;i<c.size();i++){
                System.out.println(c.get(i).getText());
            }
        }
        driver.close();
    }
}
