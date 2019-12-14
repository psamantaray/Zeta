package categoryApi;

import lombok.*;

public class RequestDTO {
    public RequestDTO(String apiKey, String testCase, String description) {
        this.apiKey = apiKey;
        this.testCase = testCase;
        this.description = description;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getTestCase() {
        return testCase;
    }

    public void setTestCase(String testCase) {
        this.testCase = testCase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String apiKey;
    private String testCase;
    private String description;
}
