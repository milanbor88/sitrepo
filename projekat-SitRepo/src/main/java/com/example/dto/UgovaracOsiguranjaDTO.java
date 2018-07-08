package com.example.dto;

import com.example.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.io.Serializable;

public class UgovaracOsiguranjaDTO implements Serializable {

    private Long id;

    @Column(name = "fotografija_polise", columnDefinition = "LONGBLOB")
    private byte[] fotografijaPoliseUO;

    @Length(min = 1, max = 30, message = "Polje može sadržati min 1 a max 30 karaktera")
    private String imePrezimeUO;

//    @Length(min = 1, max = 20, message = "Polje može sadržati min 1 a max 20 karaktera")
//    private String prezimeUO;

    @Length(max = 20, message = "Maksimalni broj karaktera je 20")
    private String adresaUO;

    @Length(max = 7, message = "Maksimalni broj karaktera je 7")
    private String postanskiBrojUO;

    @Length(max = 20, message = "Maksimalni broj karaktera je 20")
    private String telefonUO;

    private String mailUO;

    @JsonIgnore
    private String status;

    @JsonIgnore
    private UserDTO user;

    private long version;

    private String fotoPolise;

    public String getFotoPolise() {
        return fotoPolise;
    }

    public void setFotoPolise(String fotoPolise) {
        this.fotoPolise = fotoPolise;
    }

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

    public String getImePrezimeUO() {
        return imePrezimeUO;
    }

    public void setImePrezimeUO(String imePrezimeUO) {
        this.imePrezimeUO = imePrezimeUO;
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
