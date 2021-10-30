package seed.automation.steps;

import cucumber.api.java.en.Given;
import cucumber.runtime.java.guice.ScenarioScoped;
import io.restassured.http.Header;

import java.util.ArrayList;
import java.util.List;


@ScenarioScoped
public class HeaderSteps {

     List<Header> headerList = new ArrayList<>();
    @Given("^I set the header property \"([^\"]*)\" value \"([^\"]*)\"$")
    public void iSetTheHeaderPropertyValue(String property, String value) {
        storageHeader(property, value);
    }

    void storageHeader(String property, String value) {
        headerList.add(new Header(property,value));
    }

    @Given("^I clean all the headers$")
    public void iCleanAllTheHeaders() {
        headerList.clear();
    }
}