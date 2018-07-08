package com.example.dto;

import com.example.model.User;

import java.io.Serializable;

public class MestoPregledaVozilaDTO implements Serializable{

    Long id;

    String mesto;

    String adresa;

    String imobilisanoVozilo;

    private UserDTO userDTO;


    private long version;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public UserDTO getUser() {
        return userDTO;
    }

    public void setUser(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getImobilisanoVozilo() {
        return imobilisanoVozilo;
    }

    public void setImobilisanoVozilo(String imobilisanoVozilo) {
        this.imobilisanoVozilo = imobilisanoVozilo;
    }
}
