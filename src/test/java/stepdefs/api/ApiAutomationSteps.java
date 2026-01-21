package stepdefs.api;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import pages.api.ApiTestPage;
import stepdefs.web.RegistrationSteps;
import utilities.LoggerUtility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ApiAutomationSteps {
    private Response response;
    private static final Logger logger = LoggerUtility.getLogger(RegistrationSteps.class);
    ApiTestPage apitestpage = new ApiTestPage();
    public String id;

    @Given("I set the base URI")
    public void i_set_the_base_uri(DataTable dataTable) {
        logger.info("I set the base URI");
        List<String> list = dataTable.asList(String.class);
        RestAssured.baseURI = apitestpage.setBaseUri(list.get(0));

    }
    @When("I send a GET request with endpoint {string}")
    public void i_send_a_get_request_with_endpoint(String endpoint) {
        logger.info("I send a GET request");
        response = apitestpage.sendGetRequest(endpoint);
//      response.then().log().all(); // logs everything

        // if not storing it in response then use below.
//            given()
//                .when()
//                .get("/books")
//                .then()
//                .log().all();
    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer int1) {
        logger.info("the response status code should be {}", int1);
        int statusCode = response.getStatusCode();
        String errorMessage = response.getBody().asPrettyString();
        Assert.assertEquals(statusCode, int1, errorMessage);
    }

    @Then("the response should contain {string}")
    public void the_response_should_contain(String string) {
//        logger.info(response.getBody().prettyPrint());
//        logger.info(response.prettyPrint());
//        logger.info(response.getHeaders());
    }

    @When("I send a POST request")
    public void i_send_a_post_request() {
        logger.info("I send a POST request");
        String requestBody;
        //using map
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put("title", "Trading with Ak");
        map.put("author", "Ak");
        map.put("genre", "finance");
        map.put("yearPublished", 2025);

        //using external .json file
        try {
            requestBody = new String(Files.readAllBytes(Paths.get("src/test/resources/data/apiData/postData.json")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Using Map
        //response = apitestpage.sendPostRequestUsingMap("/books", map);

        //Using external json file
        response = apitestpage.sendPostRequestUsingJsonFile("/books", requestBody);
        logger.info(response.getBody().asPrettyString());
        id = response.jsonPath().getString("id");
        //System.out.println("id = "+id);
    }

    @When("I send a PATCH request")
    public void i_send_a_patch_request() {
        logger.info("I send a PATCH request");
        Map<Object, Object> map = new HashMap<Object, Object>();
        //map.put("title", "Trading with Ak");
        //map.put("author", "Ak");
        //map.put("genre", "finance");
        map.put("yearPublished", 2026);
        response = apitestpage.sendPatchRequest("/books", map, id);
        logger.info(response.getBody().asPrettyString());
    }

    @When("I send a DELETE request")
    public void i_send_a_delete_request() {
        logger.info("I send a DELETE request");
        response = apitestpage.sendDeleteRequest("/books", id);

    }

    @When("I send a GET request using path and query parameter")
    public void i_send_a_get_request_using_path_and_query_parameter() {
        logger.info("I send a GET request using path and query parameter");
        response = apitestpage.sendGetRequestUsingPathQueryParam("/books","id", "b3cc770f-3fab-48f6-a6e7-e282075ef766");
        logger.info(response.getBody().asPrettyString());
    }

    @Then("get cookie")
    public void get_cookie() {
        logger.info("get cookie");
        // 1. log all cookies with details
        //response.then().log().cookies();

        //2. get all cookies with details
        Cookies cookies = response.detailedCookies();
        for(Cookie cookie: cookies.asList()){
            System.out.println("Name     : " + cookie.getName());
            System.out.println("Value    : " + cookie.getValue());
            System.out.println("Domain   : " + cookie.getDomain());
            System.out.println("Path     : " + cookie.getPath());
            System.out.println("Expiry   : " + cookie.getExpiryDate());
            System.out.println("Secure   : " + cookie.isSecured());
            System.out.println("HttpOnly : " + cookie.isHttpOnly());
            System.out.println("-----------------------------------");
        }

        //3. get single cookie with details
        //System.out.println(response.detailedCookie("NID"));

        //4. get all cookie only key and value
//        Map<String, String> map = response.getCookies();
//        System.out.println(map);

        //5. get single cookie only key and value
        //System.out.println(response.getCookie("AEC"));

        List<String> expectedCookies = Arrays.asList("AEC", "NID");
        List<String> missingCookies = new ArrayList<>();

        for(String s: expectedCookies){
            if (!cookies.hasCookieWithName(s)) {
                missingCookies.add(s);
            }
        }
        if (!missingCookies.isEmpty()) {
            Assert.fail("Missing cookies: " + missingCookies);
        } else {
            System.out.println("All expected cookies are present!");
        }
    }

    @Then("get headers")
    public void get_headers() {
        logger.info("get headers");

        // log all headers
        //response.then().log().headers();

        //get all headers
        Headers headers = response.getHeaders();
        for(Header header: headers.asList()){
            System.out.println(header.getName()+" --> "+header.getValue());
        }

        //validate some or all headers are there in response
        List<String> expectedHeaders = Arrays.asList("Expires", "Cache-Control", "Content-Type", "Date");
        List<String> missingHeaders = new ArrayList<>();

        for(String s: expectedHeaders){
            if (!headers.hasHeaderWithName(s)) {
                missingHeaders.add(s);
            }
        }
        if (!missingHeaders.isEmpty()) {
            Assert.fail("Missing headers: " + missingHeaders);
        } else {
            System.out.println("All expected headers are present!");
        }


    }
}
