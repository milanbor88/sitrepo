package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class KlijentiDTO implements Serializable{

    private Long id;

    @Length(min=1, max = 30, message = "Polje mora sadržati izmedju 1 i 30 karaktera")
    private String name;

    @Length(max = 30, message = "Polje može sadržati do 30 karaktera")
    private String tipKlijenta;

    @Length(max = 25, message = "Polje može sadržati do 25 karaktera")
    private String adresa;

    @Length(max = 12, message = "Polje može sadržati do 12 karaktera")
    private String brojTelefona;

    @Length(min = 1, message = "Polje može sadržati do 12 karaktera")
    @NotNull
    private String email;

    private String drzava;

    private String latitude;

    private String longitude;

    public String getPromoKod() {
        return promoKod;
    }

    public void setPromoKod(String promoKod) {
        this.promoKod = promoKod;
    }

    @Length(min=6, max=6, message="poruka")
    private String promoKod;

    @JsonIgnore
    private byte[] slikaKlijenta;

    private String slika;

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }


    public byte[] getSlikaKlijenta() {
        return slikaKlijenta;
    }

    public void setSlikaKlijenta(byte[] slikaKlijenta) {
        this.slikaKlijenta = slikaKlijenta;
    }

    public String getDodatneInfo() {
        return dodatneInfo;
    }

    public void setDodatneInfo(String dodatneInfo) {
        this.dodatneInfo = dodatneInfo;
    }

    @Column(name = "dodatne_info")
    private String dodatneInfo;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipKlijenta() {
        return tipKlijenta;
    }

    public void setTipKlijenta(String tipKlijenta) {
        this.tipKlijenta = tipKlijenta;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
