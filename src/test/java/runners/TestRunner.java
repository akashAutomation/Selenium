package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:features",
        glue = {"stepdefs", "hooks"},
        tags = "(@smoke or @regression) and not @ignore",                // Include @smoke, exclude @ignore
        plugin = {"html:target/cucumber-report.html",
                "json:target/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"


        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {}
