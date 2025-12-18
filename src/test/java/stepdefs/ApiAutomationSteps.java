package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import utilities.LoggerUtility;

import static io.restassured.RestAssured.*;

public class ApiAutomationSteps {
    private Response response;
    private static final Logger logger = LoggerUtility.getLogger(RegistrationSteps.class);

    @Given("I set the base URI")
    public void i_set_the_base_uri() {
        RestAssured.baseURI = "https://library-api.postmanlabs.com";

    }
    @When("I send a GET request")
    public void i_send_a_get_request() {
        response = RestAssured
                .given()
                .when()
                .get("/books");
//                .then()
//                .log().all() // logs everything
//                .extract().response(); // converts to Response

        // if not storing it in response then use below.
//            given()
//                .when()
//                .get("/books")
//                .then()
//                .log().all();
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer int1) {
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Then("the response should contain {string}")
    public void the_response_should_contain(String string) {
//        logger.info(response.getBody().prettyPrint());
//        logger.info(response.prettyPrint());
//        logger.info(response.getHeaders());
    }
}
