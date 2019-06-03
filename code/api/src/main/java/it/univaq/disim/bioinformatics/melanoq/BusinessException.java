package it.univaq.disim.bioinformatics.melanoq;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

public class BusinessException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(HttpStatus httpStatus, String message){
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;

    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

