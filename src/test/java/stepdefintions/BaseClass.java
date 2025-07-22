package stepdefintions;

import org.hamcrest.Matchers;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import utils.GenericUtils;

public class BaseClass 
{

	RequestSpecification reqSpec;
	BaseUtil baseUtil;
	static String url;
	static String environment;
	
	public BaseClass(BaseUtil baseUtil)
	{
		this.baseUtil=baseUtil;
	}
	
	@BeforeAll
	public static void beforeAllScenarios()
	{
		System.out.println("------ BeforeAll ----------");
		environment = GenericUtils.getPropertyValue("global", "ENV");
		url = GenericUtils.getPropertyValue(environment, "URL");
	}
	
	@Before
	public void beforeScenario() 
	{
		
		System.out.println("---- Executes Before Scenario -------");
		RestAssured.baseURI = url;
		ResponseSpecBuilder builder = new ResponseSpecBuilder();
		builder.expectHeader("Server", Matchers.equalTo("github.com"));
		RestAssured.responseSpecification = builder.build();
		
		baseUtil.reqSpec = RestAssured.given();
	
	}
}
