#Author: Aksharatraining

Feature: GitHub Get Repo scenarios
  All +ve and -ve API Scenarios for Get Repo endpoint
	
	@smoke
  Scenario: Get public repo with valid data
  	Given path param "owner" with value "orgchandrab119"
  	And path param "repo" with value "repo_dec_05_02"
  	When I do a GET request with url "repos/{owner}/{repo}"
  	Then validate Status Code is 200
  	And validate inline "name" is "repo_dec_05_02"
  	And validate inline "owner.login" is "orgchandrab119"
  	
  @smoke
  Scenario: Get private repo with valid data
  	Given path param "owner" with value "orgchandrab119"
  	And path param "repo" with value "repo_jan_07_05"
  	And Authorization with value "Bearer "
  	When I do a GET request with url "repos/{owner}/{repo}"
  	Then validate Status Code is 200
  
  @p2
  Scenario Outline: Negative Scenarios
  	Given path param "owner" with value "<owner>"
  	And path param "repo" with value "<repo>"
  	When I do a GET request with url "repos/{owner}/{repo}"
  	Then validate Status Code is <statuscode>
  	
  Examples:
	|owner|repo|statuscode|
	|asdasdfasdfasd|repo_dec_05_02|404|
	|orgchandrab119|repo_dec_05_02_01|404|
	|orgchandrab119|repo_jan_07_05|404|