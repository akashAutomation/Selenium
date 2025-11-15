package pages;

import com.aventstack.extentreports.util.Assert;
import org.openqa.selenium.By;
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
        driver.findElement(By.xpath(Locators.FIRSTNAME_TEXT)).sendKeys(firstname);
        driver.findElement(By.xpath(Locators.LASTNAME_TEXT)).sendKeys(lastname);
    }

}
