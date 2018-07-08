package com.example.dto;

import java.io.Serializable;

public class OstecenjaNaVozilimaDTO implements Serializable {


    Long id;
    String materijalnaStetaNaDrugimVozilima;
    String materijalnaStetaNaDrugimStvarima;
    String parkiranZaustavljen;

    private long version;

    private UserDTO userDTO;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterijalnaStetaNaDrugimVozilima() {
        return materijalnaStetaNaDrugimVozilima;
    }

    public void setMaterijalnaStetaNaDrugimVozilima(String materijalnaStetaNaDrugimVozilima) {
        this.materijalnaStetaNaDrugimVozilima = materijalnaStetaNaDrugimVozilima;
    }

    public String getMaterijalnaStetaNaDrugimStvarima() {
        return materijalnaStetaNaDrugimStvarima;
    }

    public void setMaterijalnaStetaNaDrugimStvarima(String materijalnaStetaNaDrugimStvarima) {
        this.materijalnaStetaNaDrugimStvarima = materijalnaStetaNaDrugimStvarima;
    }

    public String getParkiranZaustavljen() {
        return parkiranZaustavljen;
    }

    public void setParkiranZaustavljen(String parkiranZaustavljen) {
        this.parkiranZaustavljen = parkiranZaustavljen;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
