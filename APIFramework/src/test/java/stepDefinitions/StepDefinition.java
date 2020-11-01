package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefinition extends Utils{

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data =new TestDataBuild();
	static String place_id;
	
	@Given("Add Place Payload with {string}  {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address)  throws IOException {
	    // Write code here that turns the phrase above into concrete actions		
		
		res = given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));
		
	}

	@Given("DeletePlace Payload")
	public void deleteplace_Payload() throws IOException{
	    // Write code here that turns the phrase above into concrete actions
		
	   res = given().spec(requestSpecification()).body(data.deletePlacePayLoad(place_id)); 
	}


	@When("user calls {string} with {string} http request")
	public void user_calls_with_Post_http_request(String resource, String httpMethod) {
	    // Write code here that turns the phrase above into concrete actions
		
		//constructor will be called with value of resource which you pass
		
		APIResources resourceAPI = APIResources.valueOf(resource);
		String resAPI = resourceAPI.getResource();
		System.out.println(resAPI);
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(httpMethod.equalsIgnoreCase("POST"))
			 response = res.when().post(resAPI);
		else if(httpMethod.equalsIgnoreCase("GET"))
			response = res.when().get(resAPI);
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    
		assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
		
	    assertEquals(getJsonPath(response,keyValue).toString(),expectedValue);
	    
	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
		
		place_id = getJsonPath(response,"place_id");
		res = given().spec(requestSpecification()).queryParam("place_id",place_id);
		user_calls_with_Post_http_request(resource, "GET");
		String actualName = getJsonPath(response,"name");
		assertEquals(actualName,expectedName);
	}

}
