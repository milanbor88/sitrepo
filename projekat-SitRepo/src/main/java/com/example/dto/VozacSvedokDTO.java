package com.example.dto;

import com.example.model.VozacNezgodaIzvestaj;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.io.Serializable;

public class VozacSvedokDTO implements Serializable {

    private Long id;

    @JsonIgnore
    private byte[] fotografijaLicneKarte;

    @Length(min=1, max= 12, message = "Polje mora da sadrzi minimalno 1 a maksimalno 12 karaktera")
    private String ime;

    @Length(min=1, max= 12, message = "Polje mora da sadrzi minimalno 1 a maksimalno 12 karaktera")
    private String prezime;

    @Length(min=1, max= 30, message = "Polje mora da sadrzi minimalno 1 a maksimalno 30 karaktera")
    private String adresa;

    @Length(max= 12, message = "Polje mora da sadrzi do 12 karaktera")
    private String telefon;

    @Length(max= 12, message = "Polje mora da sadrzi do 12 karaktera")
    private String brLicneKarte;

    private String status;

    private long version;

    private String fotoLicneKarte;

    @JsonIgnore
    private VozacNezgodaIzvestajDTO vozacNezgodaIzvestaj;

    public String getFotoLicneKarte() {
        return fotoLicneKarte;
    }

    public void setFotoLicneKarte(String fotoLicneKarte) {
        this.fotoLicneKarte = fotoLicneKarte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFotografijaLicneKarte() {
        return fotografijaLicneKarte;
    }

    public void setFotografijaLicneKarte(byte[] fotografijaLicneKarte) {
        this.fotografijaLicneKarte = fotografijaLicneKarte;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getBrLicneKarte() {
        return brLicneKarte;
    }

    public void setBrLicneKarte(String brLicneKarte) {
        this.brLicneKarte = brLicneKarte;
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

    public VozacNezgodaIzvestajDTO getVozacNezgodaIzvestaj() {
        return vozacNezgodaIzvestaj;
    }

    public void setVozacNezgodaIzvestaj(VozacNezgodaIzvestajDTO vozacNezgodaIzvestaj) {
        this.vozacNezgodaIzvestaj = vozacNezgodaIzvestaj;
    }
}
