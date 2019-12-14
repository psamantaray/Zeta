package com.TestZomato;

import categoryApi.Category;
import categoryApi.CategoryDetails;
import categoryApi.RequestDTO;
import categoryApi.ResponseDTO;
import com.API;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restAsssured.HttpMethod;
import com.restAsssured.RestAssuredClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCategory {

    private RestAssuredClient client = new RestAssuredClient();

    @DataProvider(name = "dpForCategory")
    public Object[][] dpForCategory() {
        return new Object[][]{
                {new RequestDTO(API.API_KEY, "VALID_INPUTS", "Calling the api without passing any category id")},
                {new RequestDTO(API.API_KEY+"123", "INVALID_AUTH_KEY", "Calling the api by passing an invalid api key")}

        };
    }

    @Test(dataProvider = "dpForCategory", description = "Validate the category list")
    public void testCategoryList(RequestDTO request) {
        System.out.print("Calling the category api..");
        String uri = API.HOST + API.GET_CATEGORIES;
        Map<String, Object> headers = new HashMap<>();
        headers.put("user-key", request.getApiKey());
        headers.put("Accept", "application/json");
        Response apiResponse = client.call(HttpMethod.GET, uri, headers, null, null, null);
        System.out.println(apiResponse.getBody().asString());
        testCaseValidator(request, apiResponse);

    }

    private void testCaseValidator(RequestDTO request, Response apiResponse) {
        ObjectMapper mapper = new ObjectMapper();
        ResponseDTO response = null;
        try {
            response = mapper.readValue(apiResponse.getBody().asString(), ResponseDTO.class);

        } catch (Exception e) {
            System.out.println("Error in converting the response body" + e);
        }

        switch (request.getTestCase()) {
            case "VALID_INPUTS":
                Assert.assertEquals(apiResponse.getStatusCode(), 200, "Status code is not correct.");
                Assert.assertNotNull(response.getCategories());
                validateCategoryList(response.getCategories());
                break;
            case "INVALID_AUTH_KEY":
                Assert.assertEquals(apiResponse.getStatusCode(), 403, "Status code is not correct.");
                break;
        }
    }

    private void validateCategoryList(List<Category> categoryList) {
        if (categoryList == null) return;
        for (Category details : categoryList){
            Assert.assertNotNull(details.getCategories());
            Assert.assertNotNull(details.getCategories().getId());
            Assert.assertNotNull(details.getCategories().getName());
        }
    }
}
