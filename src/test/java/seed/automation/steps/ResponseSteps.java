package seed.automation.steps;


import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.runtime.java.guice.ScenarioScoped;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import seed.automation.HttpMethod;
import seed.automation.Request;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.IsEqual.equalTo;



@ScenarioScoped
public class ResponseSteps {


    public List<io.restassured.http.Header> headerList ;
    public Dictionary<String,String> parameters;
    Response response;
    Storage storageHeader;
    String url;
    Map<String, String> params = new HashMap<>();
    @Inject
    public ResponseSteps(HeaderSteps headerSteps) {
        this.headerList=headerSteps.headerList;
        storageHeader =new Storage();
    }

    @Given("^I set the url \"([^\"]*)\"$")
    public void iSetTheUrl(String url) {
        this.url=url;
    }

    @Given("^I get the storage value \"([^\"]*)\" and put in the header property \"([^\"]*)\"$")
    public void iGetTheStorageValueAndPutInTheHeaderProperty(String storageValue, String header) {
        headerList.add(new Header(header,storageValue));

    }
    public ArrayList<io.restassured.http.Header> getHeaders(){
        return (ArrayList<io.restassured.http.Header>) headerList;
    }

    @Given("^I storage the property \"([^\"]*)\" with the value \"([^\"]*)\" of the response body$")
    public void iStorageThePropertyWithTheValueOfTheResponseBody(String property, String value) {
        storageHeader.set(value ,response.body().jsonPath().get(property));
    }

    @Given("^I call the METHOD \"([^\"]*)\" RESPONSE BODY \"([^\"]*)\" PATH \"([^\"]*)\" PATH PARAMETER \"([^\"]*)\" BODY PARAMETER \"([^\"]*)\"$")
    public void iCallTheMETHODRESPONSEBODYPATHPATHPARAMETERBODYPARAMETER(HttpMethod method, String body, String path, String pathParameter
            , String bodyParameter) {
        Request request=new Request();
        this.response=
                request.makeRequest(method,body,path,url,this.headerList, storageHeader.get(pathParameter),storageHeader.get(pathParameter));

    }

    @Then("^The status code is equals to \"([^\"]*)\"$")
    public void theResponseStatusIsEqualsTo(int responseCode) {
        response.then().log ().ifError ().assertThat ().statusCode (responseCode);
    }

    @Then("^The Get node \"([^\"]*)\"  with the property \"([^\"]*)\" and value \"([^\"]*)\" is displayed$")
    public void theGetNodeWithThePropertyAndValueIsDisplayed(String node, String property, String value) {
        final List<Map<String, ?>> values = JsonPath.with(response.body().asString()).param("nombre", value)
                .get(node+".findAll { "+property +"->"+ property +"== nombre}");
        response.then().body("[0].data.id", equalTo(1));
        org.junit.Assert.assertTrue(values.isEmpty()!=false);

    }

    @Then("^The property from response body  \"([^\"]*)\" is equals to \"([^\"]*)\"$")
    public void getThePropertyIsEqualsTo(String node, String value) {
        response.then().log().ifValidationFails().assertThat().body(node, equalTo(value) );
    }

    @Then("^The status is equals to \"([^\"]*)\"$")
    public void theStatusIsEqualsTo(String status) {
        // Write code here that turns the phrase above into concrete actions
        response.then().log().ifValidationFails().assertThat().statusLine(status);
    }

    @Then("^The response time should be less than \"([^\"]*)\" seconds$")
    public void theResponseTimeShouldBeLessThanSeconds(long time) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        response.then().log().ifValidationFails().assertThat().time(lessThan(time), TimeUnit.SECONDS);
    }
}