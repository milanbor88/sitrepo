package com.example.util;

import java.io.Serializable;

public class ApiResult implements Serializable {

    private String methodType;
    private String requestPath;
    private int responseCode;
    private String errorCode;

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ApiResult(String methodType, String requestPath, int responseCode, String errorCode) {
        this.methodType = methodType;
        this.requestPath = requestPath;
        this.responseCode = responseCode;
        this.errorCode = errorCode;
    }



}
