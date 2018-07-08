package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class PutnikNezgodaDTO  implements Serializable{

    private Long id;

    private String datumNezgodePU;
    private String vremeNezgodePU;
    private String mestoNezgodePU;


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

    public String getDatumNezgodePU() {
        return datumNezgodePU;
    }

    public void setDatumNezgodePU(String datumNezgodePU) {
        this.datumNezgodePU = datumNezgodePU;
    }

    public String getVremeNezgodePU() {
        return vremeNezgodePU;
    }

    public void setVremeNezgodePU(String vremeNezgodePU) {
        this.vremeNezgodePU = vremeNezgodePU;
    }

    public String getMestoNezgodePU() {
        return mestoNezgodePU;
    }

    public void setMestoNezgodePU(String mestoNezgodePU) {
        this.mestoNezgodePU = mestoNezgodePU;
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
