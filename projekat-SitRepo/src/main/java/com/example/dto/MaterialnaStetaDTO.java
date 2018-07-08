package com.example.dto;

import java.io.Serializable;

public class MaterialnaStetaDTO implements Serializable {

    Long id;
    String drugimVozilima;
    String drugimStvarima;


    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrugimVozilima() {
        return drugimVozilima;
    }

    public void setDrugimVozilima(String drugimVozilima) {
        this.drugimVozilima = drugimVozilima;
    }

    public String getDrugimStvarima() {
        return drugimStvarima;
    }

    public void setDrugimStvarima(String drugimStvarima) {
        this.drugimStvarima = drugimStvarima;
    }


}
