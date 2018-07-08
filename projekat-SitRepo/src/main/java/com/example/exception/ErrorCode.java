package com.example.exception;

public enum ErrorCode {

/*    //generic errors 5000 -
    ERR_GEN_001(5000, "error.storeFailed"),

    ERR_GEN_002(5001, "error.auditnotinstantiated"),*/

/*    //employee  4001 - 4100
    ERR_EMP_001(4001, "error.employeeNotExist"),*/

    //user 4101 - 4200
    ERR_USR_001(4101, "error.usernameExist"),

    ERR_USR_002(4102, "user dont exist");



    ErrorCode(int responseCode, String messageKey){
        this.responseCode = responseCode;
        this.messageKey = messageKey;
    }

    private final String messageKey;
    private final int responseCode;

    public String getMessageKey() {
        return messageKey;
    }

    public int getResponseCode() {
        return responseCode;
    }

}
