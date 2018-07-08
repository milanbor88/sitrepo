package com.example.dto;

import java.io.Serializable;

public class VozacNezgodaVecaDTO extends VozacNezgodaIzvestajDTO implements Serializable {

    private VozacNezgodaDTO vozacNezgoda;

    public VozacNezgodaDTO getVozacNezgoda() {
        return vozacNezgoda;
    }

    public void setVozacNezgoda(VozacNezgodaDTO vozacNezgoda) {
        this.vozacNezgoda = vozacNezgoda;
    }
}
