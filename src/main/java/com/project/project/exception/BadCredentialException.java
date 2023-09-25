package com.project.project.exception;

import org.springframework.stereotype.Component;

@Component
public class BadCredentialException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String errorcode;
    private String errormessage;

    public String getErrorcode() {
        return errorcode;
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public BadCredentialException(String errorcode, String errormessage) {
        this.errorcode = errorcode;
        this.errormessage = errormessage;
    }

    public BadCredentialException(){

    }

}
