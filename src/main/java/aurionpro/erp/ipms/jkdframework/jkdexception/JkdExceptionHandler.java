package aurionpro.erp.ipms.jkdframework.jkdexception;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice()
public class JkdExceptionHandler extends ResponseEntityExceptionHandler {

    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        JkdError errDetail = new JkdError(HttpStatus.BAD_REQUEST, new ArrayList<String>());
        errDetail.setErrorMessage("Invalid Input Parameters");
        errDetail.setErrorTimeStamp(System.currentTimeMillis());
        errDetail.setPathUrl(request.getDescription(false));

        ex.getBindingResult().getAllErrors().forEach(c -> errDetail.setErrorDetail(c.getDefaultMessage()));

        return getResponseEntity(errDetail);
    }

    @ExceptionHandler(BadCredentialsException.class)
    protected ResponseEntity<Object> BadCredentialException(BadCredentialsException ex,WebRequest request) {
    	 ex.printStackTrace();
        return getResponseEntity(new JkdError(HttpStatus.FORBIDDEN, 
                "Login Failed", 
                ex.getLocalizedMessage(), 
                request.getDescription(false)));
    }

    @ExceptionHandler(EntityValidationException.class)
    protected ResponseEntity<Object> EntityValidationException(EntityValidationException ex,WebRequest request) {
        
        return getResponseEntity(new JkdError(HttpStatus.NOT_ACCEPTABLE, 
                ex.getMessage(), 
                ex.getErrorDetails(), 
                request.getDescription(false)));
    }
    
    @ExceptionHandler
    protected ResponseEntity<Object> GenericException(Exception ex,WebRequest request) {
        ex.printStackTrace();
        return getResponseEntity(new JkdError(HttpStatus.INTERNAL_SERVER_ERROR, 
                "Internal Error", 
                ex.getLocalizedMessage(), 
                request.getDescription(false)));
        
    }

    private ResponseEntity<Object> getResponseEntity(JkdError ex) {
        return new ResponseEntity<Object>(ex, ex.getStatusCode());
    }
}