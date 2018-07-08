package com.example.dto;

import com.example.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.io.Serializable;

public class VoziloDTO implements Serializable {

    Long id;

    @Length(min = 1, max = 10, message = "Polje može sadržati od 1 do 10 karaktera")
    String marka;

    @Length(min = 1, max = 10, message = "Polje može sadržati od 1 do 10 karaktera")
    String tip;

    private byte[] slikaVozila;

    @Length(min = 7, max = 8, message = "Polje može sadržati 7 ili 8 karaktera")
    String registarskaOznakaVO;

    String drzavaUKojojJeVoziloRegistrovano;

    private long version;

    @JsonIgnore
    String status;

    private String fotoVozila;

    public String getFotoVozila() {
        return fotoVozila;
    }

    public void setFotoVozila(String fotoVozila) {
        this.fotoVozila = fotoVozila;
    }

    @JsonIgnore
    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public byte[] getSlikaVozila() {
        return slikaVozila;
    }

    public void setSlikaVozila(byte[] slikaVozila) {
        this.slikaVozila = slikaVozila;
    }

    public String getRegistarskaOznakaVO() {
        return registarskaOznakaVO;
    }

    public void setRegistarskaOznakaVO(String registarskaOznakaVO) {
        this.registarskaOznakaVO = registarskaOznakaVO;
    }

    public String getDrzavaUKojojJeVoziloRegistrovano() {
        return drzavaUKojojJeVoziloRegistrovano;
    }

    public void setDrzavaUKojojJeVoziloRegistrovano(String drzavaUKojojJeVoziloRegistrovano) {
        this.drzavaUKojojJeVoziloRegistrovano = drzavaUKojojJeVoziloRegistrovano;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
