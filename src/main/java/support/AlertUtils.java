package support;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertUtils extends BaseController{
    private WebDriverWait wait;

    public AlertUtils(WebDriver driver, int timeoutInSeconds) {
        BaseController.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    public void handleAlert(String action, String inputText) {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();

        System.out.println("Alert Text: " + alert.getText());

        switch (action.toLowerCase()) {
            case "accept":
                alert.accept();
                break;
            case "dismiss":
                alert.dismiss();
                break;
            case "sendkeys":
                alert.sendKeys(inputText);
                alert.accept();
                break;
            default:
                throw new IllegalArgumentException("Unsupported alert action: " + action);
        }
    }

}
