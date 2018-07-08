package com.example.exception;

public class ResourceAlreadyExistException extends Exception {

    private ErrorCode errorCode;
    private Object[] params;

    public ResourceAlreadyExistException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ResourceAlreadyExistException(ErrorCode errorCode, Exception e) {
        super(e);
        this.errorCode = errorCode;
    }

    public ResourceAlreadyExistException(ErrorCode errorCode, Object... params) {
        this.errorCode = errorCode;
        this.params = params;
    }

    public ResourceAlreadyExistException(String errorMessage, Exception e) {
        super(errorMessage, e);
    }

    public ResourceAlreadyExistException(ErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public ResourceAlreadyExistException(ErrorCode errorCode, String errorMessage, Exception e) {
        super(errorMessage, e);
        this.errorCode = errorCode;
    }

    public ResourceAlreadyExistException(ErrorCode errorCode, String errorMessage, Object... params) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.params = params;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

}
