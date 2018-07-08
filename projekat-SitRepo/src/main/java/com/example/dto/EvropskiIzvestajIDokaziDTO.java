package com.example.dto;

import com.example.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "EvropskiIzvestajIDokaziDTO", description = "sadrzi sliku za dokaze kod manje nezgode")
public class EvropskiIzvestajIDokaziDTO implements Serializable {

    Long id;

    byte[] slike;

    private String status;

    private VozacNezgodaManjaDTO vozacNezgodaManjaDTO;

    public VozacNezgodaManjaDTO getVozacNezgodaManjaDTO() {
        return vozacNezgodaManjaDTO;
    }

    public void setVozacNezgodaManjaDTO(VozacNezgodaManjaDTO vozacNezgodaManjaDTO) {
        this.vozacNezgodaManjaDTO = vozacNezgodaManjaDTO;
    }

    private String dokazi;

    public String getDokazi() {
        return dokazi;
    }

    public void setDokazi(String dokazi) {
        this.dokazi = dokazi;
    }

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

    public byte[] getSlike() {
        return slike;
    }

    public void setSlike(byte[] slike) {
        this.slike = slike;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
