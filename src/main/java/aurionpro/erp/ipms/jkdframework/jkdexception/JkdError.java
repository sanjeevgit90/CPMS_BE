package aurionpro.erp.ipms.jkdframework.jkdexception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

public class JkdError {

    private HttpStatus statusCode;
    private String errorMessage;
    private List<String> errorDetail;
    private String pathUrl;
    private long errorTimeStamp;

    public JkdError() {
    }

    public JkdError(HttpStatus statusCode, List<String> errorDetail) {
        this.statusCode = statusCode;
        this.errorDetail = errorDetail;
    }

    public JkdError(HttpStatus statusCode, String errorMessage, String errorDetail, String pathUrl) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.errorDetail = new ArrayList<String>();
        this.errorDetail.add(errorDetail);
        this.pathUrl = pathUrl;
        this.errorTimeStamp = System.currentTimeMillis();
    }

    public JkdError(HttpStatus statusCode, String errorMessage, List<String> errorDetail, String pathUrl) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.errorDetail = errorDetail;
        this.pathUrl = pathUrl;
        this.errorTimeStamp = System.currentTimeMillis();
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(List<String> errorDetail) {
        this.errorDetail = errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail.add(errorDetail);
    }

    public String getPathUrl() {
        return pathUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    public long getErrorTimeStamp() {
        return errorTimeStamp;
    }

    public void setErrorTimeStamp(long errorTimeStamp) {
        this.errorTimeStamp = errorTimeStamp;
    }

        
}