package api.colourloverspatterns;

import com.shaft.api.RestActions;
import com.shaft.api.RestActions.RequestType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class ColourLoversPatterns {
	
	private RestActions apiObject;
	
    // Base URL
    //public static final String BASE_URL = System.getProperty("colourloversAPIURL");
    public static final String BASE_URL = "http://www.colourlovers.com/api/";
	
    // Services Names
    private String colourLoverApiServiceName = "patterns";
    
    // Constructor
    public ColourLoversPatterns(RestActions apiObject) {
    	this.apiObject = apiObject;
    }

    public String getServiceName() {
    	return colourLoverApiServiceName;
    }
    
    public Response colourLoversPatternsResponse() {
    	return 
    	apiObject
    	.buildNewRequest(colourLoverApiServiceName, RequestType.GET)
		.addHeader("User-Agent", "PostmanRuntime/7.28.0")
		.addHeader("Content-Type", "application/xml")
		.addHeader("Content-Encoding", "gzip")
		.setContentType(ContentType.XML)
		.performRequest();
    }
}