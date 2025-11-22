package support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Set;

public class multiTabWindowHandle {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\akash\\Downloads\\driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://www.google.com/");


        // Store the original window handle
        String originalWindow = driver.getWindowHandle();

        // open a new tab
        //driver.switchTo().newWindow(WindowType.TAB);

        // open a new window
        driver.switchTo().newWindow(WindowType.WINDOW);

        driver.get("http://demo.automationtesting.in/Register.html");

        // Wait and switch to the new window
        Set<String> allWindows = driver.getWindowHandles();
        for (String windowHandle : allWindows) {
            driver.switchTo().window(windowHandle);
            if (driver.getTitle().contains("Register")) {
                System.out.println("New tab title: " + driver.getTitle());
                break;
           }

        }


        // Closes the current tab
        //driver.close();

        // Switch back to original window
        driver.switchTo().window(originalWindow);
        System.out.println("Back to original window: " + driver.getTitle());

        //Closes all browser windows and ends the WebDriver session
        driver.quit();
    }

}
