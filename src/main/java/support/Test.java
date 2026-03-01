package support;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.*;


public class Test {
    public static void main(String[] args) throws IOException, InterruptedException {


        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\akash\\Downloads\\driver\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("chrome://settings/appearance");

//       WebElement el1 =  driver.findElement(By.cssSelector("settings-ui")).getShadowRoot()
//                .findElement(By.cssSelector("settings-main#main")).getShadowRoot()
//                .findElement(By.cssSelector("#switcher"))
//                .findElement(By.cssSelector("settings-appearance-page-index")).getShadowRoot()
//                .findElement(By.cssSelector("cr-view-manager#viewManager"))
//                .findElement(By.cssSelector("settings-appearance-page#parent")).getShadowRoot()
//                .findElement(By.cssSelector("settings-section[page-title='Appearance']"))
//                .findElement(By.cssSelector("settings-toggle-button")).getShadowRoot()
//                .findElement(By.cssSelector("cr-toggle[aria-label='Show home button']"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement el = (WebElement)js.executeScript("return document.querySelector(\"body > settings-ui\").shadowRoot.querySelector(\"#main\").shadowRoot.querySelector(\"#appearance > settings-appearance-page-index\").shadowRoot.querySelector(\"#parent\").shadowRoot.querySelector(\"settings-section > div > settings-toggle-button:nth-child(5)\").shadowRoot.querySelector(\"#control\");");
el.click();


    }

}
