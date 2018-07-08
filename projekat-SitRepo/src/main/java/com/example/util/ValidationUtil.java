package com.example.util;

import com.example.exception.ErrorCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class ValidationUtil {

    public boolean checkMediaType(String media) {
        if (!(media.equals(MediaType.IMAGE_GIF_VALUE) || media.equals(MediaType.IMAGE_JPEG_VALUE)
                || media.equals(MediaType.IMAGE_PNG_VALUE))){
            return true;
        }
        return false;
    }

        public StringBuilder getValidateErrors(BindingResult result) {
        StringBuilder error = new StringBuilder();
        if (!result.getFieldErrors().isEmpty()) {
            result.getFieldErrors().stream().filter(fe ->
                    fe != null).forEach(fieldError -> error.append(fieldError.getDefaultMessage()));
        } else {
            error.append(ErrorCode.ERR_USR_002.getMessageKey());
        }
        return  error;
    }


}
