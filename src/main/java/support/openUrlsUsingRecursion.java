package support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class openUrlsUsingRecursion {
    public static void main(String[] args){

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\akash\\Downloads\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        String[] urls = {
                "https://example.com",
                "https://www.selenium.dev",
                "https://www.google.com"
        };
        multipleUrlRecursively(driver, urls, 0);
        driver.quit();
    }

    public static void multipleUrlRecursively(WebDriver driver, String[] urls, int index){
        if(index>=urls.length){
            return;
        }
        driver.get(urls[index]);
        multipleUrlRecursively(driver, urls, index+1);

    }
}
