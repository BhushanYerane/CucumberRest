package com.basic.HeaderValidationSD;

import org.junit.Assert;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;


public class HeaderValidationSD
{
	Response res;
	
	@Given("^user start the rest assured test$")
	public void user_start_the_rest_assured_test()
	{
		System.out.println("starting of the GET script");
	}

	@When("^user checks headers \"([^\"]*)\" have \"([^\"]*)\"$")
	public void user_checks_headers_have(String headerName, String headerValidation) 
	{
		res = RestAssured.given().relaxedHTTPSValidation().get("https://ergast.com/api/f1/2017/circuits.json");
		String completeResponse = res.asString();
		System.out.println("========="+completeResponse);
		
		// Get Header
		Headers heds = res.getHeaders();
		String actualHeaderValue = heds.getValue(headerName);
		Assert.assertTrue(headerValidation.equals(actualHeaderValue));
	}

}
