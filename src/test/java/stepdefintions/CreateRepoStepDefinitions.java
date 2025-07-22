package stepdefintions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.GitHubCreateRepoPojo;
import pojo.GitHubCreateRepoResponseRootPojo;

public class CreateRepoStepDefinitions{

	RequestSpecification reqSpec;
	BaseUtil baseUtil;
	Response res;
	GitHubCreateRepoPojo obj = new GitHubCreateRepoPojo();
	
	public CreateRepoStepDefinitions(BaseUtil baseUtil) {
		System.out.println(baseUtil);
		this.reqSpec = baseUtil.reqSpec;
		this.baseUtil = baseUtil;
		System.out.println("------ Executing from CreateRepoStepDefinitions.java-------");
		System.out.println(this.baseUtil);
	}
	
	@Given("body param from file {string}")
	public void bodyParamFromFile(String fileName) {
	    // Write code here that turns the phrase above into concrete actions
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/inputjson/"+fileName);
			baseUtil.reqSpec.body(fis)
			.contentType(ContentType.JSON);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Given("body param {string} with value {string}")
	public void bodyParamWithValue(String keys, String values) {
	    // Write code here that turns the phrase above into concrete actions
	    //GitHubCreateRepoPojo obj = new GitHubCreateRepoPojo();
	    
	    if(keys.equals("name")) {
	    	obj.setName(values);
	    }
	    else if(keys.equals("description")) {
	    	obj.setDescription(values);
	    }
	    else if(keys.equals("private")) {
	    	if(values.equals("true")) {
	    		obj.setPrivateVal(true);
	    	}
	    	else {
	    		obj.setPrivateVal(false);
	    	}
	    }
	    
	    baseUtil.reqSpec.body(obj)
	    		.contentType(ContentType.JSON);
	}
	
	@Then("validate {string} is {string}")
	public void validateIs(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
		GitHubCreateRepoResponseRootPojo resObj = baseUtil.res
												.then()
												.extract()
												.as(GitHubCreateRepoResponseRootPojo.class);
		
		if(key.equals("name")) {
			String actual = resObj.getName();
			MatcherAssert.assertThat(actual, Matchers.equalTo(value));
		}
		else if(key.equals("description")) {
			String actual = resObj.getDescription();
			MatcherAssert.assertThat(actual, Matchers.equalTo(value));
		}
		else if(key.equals("private")) {
			String actual = resObj.getPrivateVal();
			MatcherAssert.assertThat(actual, Matchers.equalTo(value));
		}
	}
	
	@When("I do a POST request with url {string}")
	public void iDoAPOSTRequestWithUrl(String url) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Executing When");
		baseUtil.res = reqSpec.when()
				.post(url);
	}
}
