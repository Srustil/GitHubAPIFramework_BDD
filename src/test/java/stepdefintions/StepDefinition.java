package stepdefintions;

import org.hamcrest.Matchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefinition {

	Response res;
	RequestSpecification reqSpec;
	BaseUtil baseUtil;
	
	
	public StepDefinition(BaseUtil baseUtil) {
		this.baseUtil=baseUtil;
		this.reqSpec = baseUtil.reqSpec;
		System.out.println("------ Executing from stepdefinition.java-------");
	}
	
	@Given("path param {string} with value {string}")
	public void pathParamWithValue(String paramName, String paramValue) {
	    // Write code here that turns the phrase above into concrete actions
	   // System.out.println("Executing Given -- "+paramName+"--"+paramValue);
//	    baseUtil.reqSpec.pathParam(paramName, paramValue)
//					.log().all();
	    SerenityRest.rest().pathParam(paramName, paramValue);
	}

	}
	
	@Given("Authorization with value {string}")
	public void authorizationWithValue(String token) {
	    // Write code here that turns the phrase above into concrete actions
		baseUtil.reqSpec.header("Authorization",token);
	}
	
	@When("I do a GET request with url {string}")
	public void iDoARequestWithUrl(String url) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Executing When");
		baseUtil.res = baseUtil.reqSpec.when()
				.get(url);
	}
	
	@Then("validate Status Code is {int}")
	public void validateStatusCodeIs(Integer statusCode) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Executing Then");
		baseUtil.res.then()
		.log().all()
		.statusCode(statusCode);
	}
	
	@Then("validate inline {string} is {string}")
	public void validateInlineIs(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
		baseUtil.res.then()
	    	.body(key, Matchers.equalTo(value));
	}
}
