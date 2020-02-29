package com.basic.getRequestBasicSD;

import org.junit.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class GetRequestBasiocSD 
{
	Response res;
	
	@Given("^race circuite napplication is running$")
	public void race_circuite_application_is_running()
	{
		System.out.println("Application is ready to use");
	}
	
	@When("^I hit get request to get response$")
	public void i_hit_get_request_to_get_response() 
	{
		res=RestAssured.given().get("http://ergast.com/api/f1/2017/circuits.jason");
		String actualData= res.getBody().asString();
		System.out.println("Data   "+actualData);
	}

	@Then("^I validate circuite \"([^\"]*)\" value as \"([^\"]*)\" present$")
	public void i_validate_circuite_limit_value_as_present(String jPath, String valueTobevalidated)
	{
		String attrValue= res.body().jsonPath().getString(jPath);
		Assert.assertTrue(attrValue.equals(valueTobevalidated));
	}
	
	@Then("^I validated circuite ID value$")
	public void i_validated_circuite_ID_values()
	{
		String totalValues=res.body().jsonPath().get("MRDta.total");
		int totalIntV = Integer.parseInt(totalValues);
		
		for(int i=0; i <totalIntV; i++) 
		{
			String idValue = res.body().jsonPath().getString("MRData.CircuitTable.Circuits["+i+"].circuitId");
			if(idValue.equals("americas"))
		  {
				Assert.assertTrue(idValue.equals("americas"));
				break;
		  }else{
			if(i==totalIntV-1)
			{
				Assert.assertTrue(false);
		    }
	   	 }
	  }
	}
}
