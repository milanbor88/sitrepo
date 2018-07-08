package com.example.util;

import com.example.model.Klijenti;
import com.example.service.KlijentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PromoKodGen {

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    @Autowired
    private KlijentiService klijentiService;

    public String checkPromoKod() {

        String provera = randomAlphaNumeric();
        Klijenti klijenti = klijentiService.findByPromoKod(provera);

        if (klijenti != null) {
          //  uniqueKod = false;
            return checkPromoKod();
        }

        return provera;


    }

    private String randomAlphaNumeric() {
        int count =6;
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return   builder.toString();

    }

}
