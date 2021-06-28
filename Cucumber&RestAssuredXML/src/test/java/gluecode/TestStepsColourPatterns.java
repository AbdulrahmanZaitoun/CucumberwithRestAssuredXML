package gluecode;

import org.testng.annotations.Test;

import com.shaft.api.RestActions;
import com.shaft.driver.DriverFactory;
import com.shaft.validation.Verifications;

import api.colourloverspatterns.ColourLoversPatterns;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class TestStepsColourPatterns {
	
	private ColourLoversPatterns colourLoversPatternsobj;
    private RestActions apiObject;
    private Response getPatterns;
    private String patterns;
	private XmlPath xml;
	private int numResults;
	
	@SuppressWarnings("static-access")
	@Given("^I want to execute get color lovers patterns$")
	@Test
	public void executeColourLoversPatternsAPI()
	{	
		apiObject = DriverFactory.getAPIDriver(colourLoversPatternsobj.BASE_URL);
		colourLoversPatternsobj = new ColourLoversPatterns(apiObject);
	}
	
	@When("^I submit the Get request$")
	@Test
	public void submitColoursPatternsAPI()
	{
		getPatterns = colourLoversPatternsobj.colourLoversPatternsResponse();

		// Get the body response of the API
		
		patterns = getPatterns.body().asString();
		xml = new XmlPath(patterns);
		
		// Get the number of returned patterns 
		
		numResults = xml.getInt("patterns.@numResults");

		//System.out.println("Print all the response: --> "+patterns);
	}

	@Then("^I should get all the patterns numViews greater than 4000$")
	@Test
	public void getColoursPatters()
	{

		// Create loop to check each pattern against the numViews value
		
		for(int i = 0 ; i<numResults ; ++i) {
			System.out.println("The numViews value of pattern number " + (i+1) + " is -->" + xml.getInt("patterns.pattern["+ i +"].numViews"));
			Verifications.verifyComparativeRelation(4000,xml.getInt("patterns.pattern["+ i +"].numViews"), 
					Verifications.ComparativeRelationType.GREATER_THAN,"The newViews value of pattern"+i+" is greater than 4000");
		}  	
	}
}
