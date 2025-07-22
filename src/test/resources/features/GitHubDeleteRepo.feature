#Author: Aksharatraining

Feature: GitHub Delete Repo scenarios
  All +ve and -ve API Scenarios for Delete Repo endpoint
	
	@smoke
  Scenario: Delete public repo with valid data
  	Given path param "owner" with value "orgchandrab119"
  	And path param "repo" with value "repo_feb_04_02"
  	And Authorization with value "Bearer "
  	When I do a DELETE request with url "repos/{owner}/{repo}"
  	Then validate Status Code is 204