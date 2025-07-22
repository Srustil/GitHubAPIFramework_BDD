package stepdefintions;

import io.cucumber.java.en.When;

public class DeleteRepoStepDefinitions {

	BaseUtil baseUtil;
	
	public DeleteRepoStepDefinitions(BaseUtil baseUtil) {
		this.baseUtil = baseUtil;
	}
	
	
	@When("I do a DELETE request with url {string}")
	public void iDoADELETERequestWithUrl(String url) {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("Executing When");
		baseUtil.res = baseUtil.reqSpec.when()
				.delete(url);
	}
}
