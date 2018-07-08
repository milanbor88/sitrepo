package com.example.util;

import java.io.Serializable;

public class JsonPdfResult implements Serializable{

    private ApiResult apiResult;

    private String pdfString;

    public ApiResult getApiResult() {
        return apiResult;
    }

    public void setApiResult(ApiResult apiResult) {
        this.apiResult = apiResult;
    }

    public String getPdfString() {
        return pdfString;
    }

    public void setPdfString(String pdfString) {
        this.pdfString = pdfString;
    }
}
