package LocationDetails;

public class RequestDTO {
    private String apiKey;

    public RequestDTO(String apiKey, String entityId, String entityType, String testCase, String description) {
        this.apiKey = apiKey;
        this.entityId = entityId;
        this.entityType = entityType;
        this.testCase = testCase;
        this.description = description;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
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

    private String entityId;
    private String entityType;
    private String testCase;
    private String description;

}
