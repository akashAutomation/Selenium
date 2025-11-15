package support;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils extends BaseController{

    public WebElement visibilityOfElementLocated(int sec, String locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
        return element;
    }

    public WebElement elementToBeClickable(int sec, String locator){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        return element;
    }

    public void fluentWait( String locator){
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }
}
