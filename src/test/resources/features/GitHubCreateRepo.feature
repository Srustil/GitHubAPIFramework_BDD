#Author: Chandra

Feature: GitHub Create an organization repository.
  This feature file has all +ve and -ve scenarios for Create Repo Endpoint

  Scenario Outline: GitHub Create valid public repo
    Given path param "org" with value "orgchandrab119"
    And Authorization with value "Bearer "
    And body param "name" with value "<name>"
    And body param "description" with value "<description>"
    And body param "private" with value "<private>"
    When I do a POST request with url "orgs/{org}/repos"
    Then validate Status Code is 201
    And validate "name" is "<name>"
    And validate "description" is "<description>"
    And validate "private" is "<private>"
  
  Examples:
  	|name|description|private|
  	|repo_feb_04_02|This is a repo|false|
  
  
  Scenario: GitHub Create valid private repo from json file
    Given path param "org" with value "orgchandrab119"
    And Authorization with value "Bearer "
    And body param from file "createrepo.json"
    When I do a POST request with url "orgs/{org}/repos"
    Then validate Status Code is 201
   
  @custom 
  Scenario: GitHub Create valid public repo from json file without token
    Given path param "org" with value "orgchandrab119"
    And body param from file "createrepo.json"
    When I do a POST request with url "orgs/{org}/repos"
    Then validate Status Code is 401
  