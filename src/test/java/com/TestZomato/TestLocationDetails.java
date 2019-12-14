package com.TestZomato;

import LocationDetails.RequestDTO;
import com.API;
import com.restAsssured.HttpMethod;
import com.restAsssured.RestAssuredClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class TestLocationDetails {
    private RestAssuredClient client = new RestAssuredClient();

    @DataProvider(name = "dpForLocationDetails")
    public Object[][] dpForCategory() {
        return new Object[][]{
                {new RequestDTO(API.API_KEY, "36932", "group", "VALID_INPUTS", "Calling the api  by passing valid entity id and entity type")},
                {new RequestDTO(API.API_KEY, "36932111", "group", "INVALID_ENTITY_ID", "Calling the api by passing invalid entity id")},
                {new RequestDTO(API.API_KEY, "36932", "group1", "INVALID_ENTITY_TYPE", "Calling the api by passing invalid entity type")},

        };
    }

    @Test(dataProvider = "dpForLocationDetails", description = "Validate the category list")
    public void testCategoryList(RequestDTO request) {
        System.out.print("Calling the category api..");
        String uri = API.HOST + API.GET_LOCATION_DETAILS;
        Map<String, Object> headers = new HashMap<>();
        headers.put("user-key", request.getApiKey());
        headers.put("Accept", "application/json");
        Map<String, Object> queryParams = new HashMap<>();
        queryParams.put("entity_id", request.getEntityId());
        queryParams.put("entity_type", request.getEntityType());

        Response apiResponse = client.call(HttpMethod.GET, uri, headers, null, queryParams, null);
        System.out.println(apiResponse.getBody().asString());
        testCaseValidator(request, apiResponse);


    }

    private void testCaseValidator(RequestDTO request, Response apiResponse){

        switch(request.getTestCase()){
            case "VALID_INPUTS":
                Assert.assertEquals(apiResponse.getStatusCode(), 200, "Status code is not correct.");
                Assert.assertNotNull(apiResponse.getBody());
                break;
            case "INVALID_ENTITY_ID":
            case "INVALID_ENTITY_TYPE":
                Assert.assertEquals(apiResponse.getStatusCode(), 403, "Status code is not correct.");
                break;
        }
    }

}
