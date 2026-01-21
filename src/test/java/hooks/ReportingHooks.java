package hooks;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import support.BaseController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ReportingHooks extends BaseController {
    @AfterStep
    public void captureScreenshot(Scenario scenario) throws IOException {

        if (scenario.isFailed() && driver != null) {
            try {
                // 1. Capture screenshot as file
                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                // 2. Ensure screenshot folder exists
                File destDir = new File("target/screenshots/");
                if (!destDir.exists()) {
                    destDir.mkdirs();
                }

                // 3. Clean filename
                String screenshotName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_") + ".png";
                File destFile = new File(destDir, screenshotName);

                // 4. Save screenshot to disk
                Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // 5. Attach to Spark (file-based)
                //ExtentCucumberAdapter.addTestStepScreenCaptureFromPath("../screenshots/" + screenshotName);

                // 6. Attach to PDF (byte-based)
                byte[] screenshotBytes = Files.readAllBytes(destFile.toPath());
                scenario.attach(screenshotBytes, "image/png", "Failure Screenshot");



            } catch (IOException e) {
                System.err.println("Screenshot capture failed: " + e.getMessage());
            }
        }
    }
}
