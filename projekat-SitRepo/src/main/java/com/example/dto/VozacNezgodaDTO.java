package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class VozacNezgodaDTO implements Serializable{

    private Long id;

    private String datumNezgode;
    private String vremeNezgode;
    private String mestoNezgode;

    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatumNezgode() {
        return datumNezgode;
    }

    public void setDatumNezgode(String datumNezgode) {
        this.datumNezgode = datumNezgode;
    }

    public String getVremeNezgode() {
        return vremeNezgode;
    }

    public void setVremeNezgode(String vremeNezgode) {
        this.vremeNezgode = vremeNezgode;
    }

    public String getMestoNezgode() {
        return mestoNezgode;
    }

    public void setMestoNezgode(String mestoNezgode) {
        this.mestoNezgode = mestoNezgode;
    }


    @JsonIgnore
    private UserDTO user;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
