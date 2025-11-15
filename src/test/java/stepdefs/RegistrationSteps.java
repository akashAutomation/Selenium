package stepdefs;


import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.RegistrationPage;
import support.ConfigPropertiesReader;
import support.ExcelUtils;
import utilities.LoggerUtility;

import java.io.IOException;


public class RegistrationSteps {
    private static final Logger logger = LoggerUtility.getLogger(RegistrationSteps.class);

    RegistrationPage registrationPage = new RegistrationPage();
    String exceldataPath = "src/test/resources/data/testdata.xlsx";

    @Given("user is on registration page {string}")
    public void userIsOnRegistrationPage(String s) {
        logger.info("-------user is on registration page-------");
        //System.out.println("-------user is on registration page-------");
        registrationPage.navigateToUrl(s);
    }

    @When("validate current page {string}")
    public void validateCurrentPageTitle(String s) {
        Assert.assertEquals(registrationPage.validateTitle(), s);
    }

    @Then("user enters {string} and {string}")
    public void userEntersFirstnameAndLastname(String firstname, String lastname) throws IOException {
        System.out.println("-------userEntersFirstnameAndLastname-------");
        System.out.println("fullname = "+firstname+" "+lastname);
        registrationPage.enterFullName(firstname, lastname);
    }

    @Then("read value from properties file")
    public void readValueFromPropertiesFile() {
        System.out.println(ConfigPropertiesReader.get("name"));
    }

    @Then("read cell value from excel")
    public void readCellValueFromExcel() {
        System.out.println("Username --> "+ExcelUtils.readCellValue(exceldataPath,"Login",0,0 ));
        System.out.println("Password --> "+ExcelUtils.readCellValue(exceldataPath,"Login",0,1 ));
        System.out.println("Username --> "+ExcelUtils.readCellValue(exceldataPath,"Login",1,0 ));
        System.out.println("Password --> "+ExcelUtils.readCellValue(exceldataPath,"Login",1,1 ));
    }

    @Then("write cell value in excel")
    public void writeCellValueInExcel() {
        ExcelUtils.writeCellValue(exceldataPath,"Login",2,0, "written value by using poi");
    }

    @Then("create .xlsx file and sheet")
    public void createXlsxFileAndSheet() {
        ExcelUtils.createExcelFileAndSheet("src/test/resources/data/newcreated/created_excel.xlsx","Test",0,0, "written value by using poi");
    }
}
