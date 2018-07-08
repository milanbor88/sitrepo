package com.example.dto;

import com.example.model.VozacNezgodaManja;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.io.Serializable;

public class UgovaracOsiguranjaBDTO implements Serializable {

    private Long id;

    private byte[] fotografijaPoliseUO;

    @Length(min = 1, max = 20, message = "Polje može sadržati min 1 a max 20 karaktera")
    private String imeUO;

    @Length(min = 1, max = 20, message = "Polje može sadržati min 1 a max 20 karaktera")
    private String prezimeUO;

    @Length(max = 20, message = "Maksimalni broj karaktera je 20")
    private String adresaUO;

    @Length(max = 7, message = "Maksimalni broj karaktera je 7")
    private String postanskiBrojUO;

    @Length(max = 20, message = "Maksimalni broj karaktera je 20")
    private String telefonUO;

    private String mailUO;

    private String status;

    private VozacNezgodaManjaDTO vozacNezgodaManja;

    private long version;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFotografijaPoliseUO() {
        return fotografijaPoliseUO;
    }

    public void setFotografijaPoliseUO(byte[] fotografijaPoliseUO) {
        this.fotografijaPoliseUO = fotografijaPoliseUO;
    }

    public String getImeUO() {
        return imeUO;
    }

    public void setImeUO(String imeUO) {
        this.imeUO = imeUO;
    }

    public String getPrezimeUO() {
        return prezimeUO;
    }

    public void setPrezimeUO(String prezimeUO) {
        this.prezimeUO = prezimeUO;
    }

    public String getAdresaUO() {
        return adresaUO;
    }

    public void setAdresaUO(String adresaUO) {
        this.adresaUO = adresaUO;
    }

    public String getPostanskiBrojUO() {
        return postanskiBrojUO;
    }

    public void setPostanskiBrojUO(String postanskiBrojUO) {
        this.postanskiBrojUO = postanskiBrojUO;
    }

    public String getTelefonUO() {
        return telefonUO;
    }

    public void setTelefonUO(String telefonUO) {
        this.telefonUO = telefonUO;
    }

    public String getMailUO() {
        return mailUO;
    }

    public void setMailUO(String mailUO) {
        this.mailUO = mailUO;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VozacNezgodaManjaDTO getVozacNezgodaManja() {
        return vozacNezgodaManja;
    }

    public void setVozacNezgodaManja(VozacNezgodaManjaDTO vozacNezgodaManja) {
        this.vozacNezgodaManja = vozacNezgodaManja;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
