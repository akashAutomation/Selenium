package pages;

import com.aventstack.extentreports.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import support.BaseController;
import support.Locators;

import java.time.Duration;

public class RegistrationPage extends BaseController {

    public void navigateToUrl(String url) {
        driver.get(url);
    }

    public String validateTitle() {
        return driver.getTitle();
    }

    public void enterFullName(String firstname, String lastname) {
        WebElement firstnameel = driver.findElement(By.xpath(Locators.FIRSTNAME_TEXT));
        firstnameel.clear();
        firstnameel.sendKeys(firstname);
        WebElement lastnameel = driver.findElement(By.xpath(Locators.LASTNAME_TEXT));
        lastnameel.clear();
        lastnameel.sendKeys(lastname);
//        try{
//            Thread.sleep(2000);
//        }
//        catch(Exception e){
//
//        }

    }

}
