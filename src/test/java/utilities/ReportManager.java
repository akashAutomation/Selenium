package utilities;

import com.aventstack.extentreports.service.ExtentService;

public class ReportManager {
    static {
        ExtentService.getInstance().setSystemInfo("Browser", "Chrome");
        ExtentService.getInstance().setSystemInfo("Environment", "QA");
        ExtentService.getInstance().setSystemInfo("Author", "Akash");
    }

}
