package com.example.controller.rest.handler;

import com.example.util.ApiResult;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class RestException {


/*
   @ExceptionHandler({AccessDeniedException.class})
    public ApiResult handleAccessDeniedException(Exception ex, HttpServletRequest request) {
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
               HttpStatus.FORBIDDEN.value(), "s' u kur");
       return apiResult;
    }
*/



    @ExceptionHandler(ClassCastException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public ApiResult classException(ClassCastException ex, HttpServletRequest request) {

        ApiResult apiResult;

        String message = ex.getMessage();
        if (message.equals("java.lang.String cannot be cast to com.example.dto.UserDTO")) {
            apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
        }else  {
            apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
        }
        return apiResult;
    }



   /* @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResult bindException(BindException ex, HttpServletRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        ApiResult result = bindException(bindingResult, request);
        return result;
    }


    private ApiResult bindException(BindingResult bindingResult, HttpServletRequest request) {
        String errorResult = "";
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorResult += fieldError.getField();
                errorResult += " = ";
                errorResult += fieldError.getRejectedValue();
                errorResult += " ";
            }
            if (!StringUtils.isEmpty(errorResult)) {
                errorResult = "Rejected values for binding from url are : " + errorResult;
            }
        }

        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.UNAUTHORIZED.value(), errorResult);
        return apiResult;
    }*/

}
