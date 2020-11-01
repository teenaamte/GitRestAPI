Feature: validating place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being Successfully addes using AddPlaceAPI

	Given Add Place Payload with "<name>"  "<language>" "<address>"
	When user calls "addPlaceAPI" with "Post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP" 
	And verify place_Id created maps to "<name>" using "getPlaceAPI"
	

Examples:
    |name       | language  | address                     |
    |David      | French    | 12, The Mead, Wallington    |
   #|Robert     | English   | 8, Hunters Way, Croydon     |
   #|Steve		| German	| 3, Waterer Rise, Wallington |
   
@DeletePlace @Regression
Scenario: Verify if Delete Place functionality is working

	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"

