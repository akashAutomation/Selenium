package support;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightTest {
    public static void main(String[] args) throws InterruptedException {
        Playwright playwright = Playwright.create();
//        BrowserType.LaunchOptions lp = new BrowserType.LaunchOptions();
//        lp.setChannel("chrome");
//        lp.setHeadless(false);
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("http://demo.automationtesting.in/Register.html", new Page.NavigateOptions().setTimeout(60000));
        System.out.println(page.title());
        page.fill("//input[@placeholder='First Name']", "abc");
        page.fill("//input[@placeholder='Last Name']", "xyz");
        page.click("//button[text()='Refresh']");
        browser.close();
        playwright.close();
    }
}
