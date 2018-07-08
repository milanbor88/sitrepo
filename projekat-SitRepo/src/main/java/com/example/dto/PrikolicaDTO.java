package com.example.dto;

import com.example.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.io.Serializable;

public class PrikolicaDTO implements Serializable {

    private Long id;

    @Column(name = "slika_prikolice", columnDefinition = "LONGBLOB")
    private byte[] slikaPrikolice;

    @Length(min = 7, max = 9, message = "Polje mora sadržati od 7  do 9 karaktera")
    private String registarskaOznaka;

    @Length(max = 20,  message = "Polje može imati maksimum 20 karaktera")
    private String drzavaUKojojJeRegistrovana;

    @Length(min = 1, message = "Polje mora sadržati minimum 1 karakter")
    private String maksimalnaDozvoljenaTezina;

    private String status;

    private long version;

    private String slikaPrikolicebase64;

    @JsonIgnore
    private UserDTO user;

    public String getSlikaPrikolicebase64() {
        return slikaPrikolicebase64;
    }

    public void setSlikaPrikolicebase64(String slikaPrikolicebase64) {
        this.slikaPrikolicebase64 = slikaPrikolicebase64;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getSlikaPrikolice() {
        return slikaPrikolice;
    }

    public void setSlikaPrikolice(byte[] slikaPrikolice) {
        this.slikaPrikolice = slikaPrikolice;
    }

    public String getRegistarskaOznaka() {
        return registarskaOznaka;
    }

    public void setRegistarskaOznaka(String registarskaOznaka) {
        this.registarskaOznaka = registarskaOznaka;
    }

    public String getDrzavaUKojojJeRegistrovana() {
        return drzavaUKojojJeRegistrovana;
    }

    public void setDrzavaUKojojJeRegistrovana(String drzavaUKojojJeRegistrovana) {
        this.drzavaUKojojJeRegistrovana = drzavaUKojojJeRegistrovana;
    }

    public String getMaksimalnaDozvoljenaTezina() {
        return maksimalnaDozvoljenaTezina;
    }

    public void setMaksimalnaDozvoljenaTezina(String maksimalnaDozvoljenaTezina) {
        this.maksimalnaDozvoljenaTezina = maksimalnaDozvoljenaTezina;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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
}
