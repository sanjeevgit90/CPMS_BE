package aurionpro.erp.ipms.jkdframework.jkdexception;

import java.util.ArrayList;
import java.util.List;

public class EntityValidationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private List<String> errorDetails;

    public EntityValidationException(String message, List<String> detailMessage) {
        super(message);
        this.errorDetails = detailMessage;
    }

    public EntityValidationException(String message, String detailMessage) {
        super(message);
        this.errorDetails = new ArrayList<String>();
        this.errorDetails.add(detailMessage);
    }

    public List<String> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(List<String> errorDetails) {
        this.errorDetails = errorDetails;
    }
    

}