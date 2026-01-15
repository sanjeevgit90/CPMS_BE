package aurionpro.erp.ipms.ordermgmt.prs.dto;

public class ApiResponse {

    private boolean success;
    private String message;
    private Long entityId;

    public ApiResponse(boolean success, String message, Long entityId) {
        this.success = success;
        this.message = message;
        this.entityId = entityId;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Long getEntityId() {
        return entityId;
    }
}

