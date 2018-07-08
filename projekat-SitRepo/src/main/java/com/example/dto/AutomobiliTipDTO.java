package com.example.dto;

import com.example.model.Automobili;

import java.io.Serializable;

public class AutomobiliTipDTO implements Serializable {

    long id;

    String model;

    private long version;



    private AutomobiliDTO automobiliDTO;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public AutomobiliDTO getAutomobiliDTO() {
        return automobiliDTO;
    }

    public void setAutomobiliDTO(AutomobiliDTO automobiliDTO) {
        this.automobiliDTO = automobiliDTO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public AutomobiliDTO getAutomobili() {
        return automobiliDTO;
    }

    public void setAutomobili(AutomobiliDTO automobiliDTO) {
        this.automobiliDTO = automobiliDTO;
    }

}
