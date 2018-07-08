package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class PovredjeniDrugoVoziloDTO implements Serializable {
    Long id;
    String ime;
    String prezime;
    String adresa;
    String telefon;


    public VozacNezgodaIzvestajDTO getVozacNezgodaIzvestaj() {
        return vozacNezgodaIzvestaj;
    }

    public void setVozacNezgodaIzvestaj(VozacNezgodaIzvestajDTO vozacNezgodaIzvestaj) {
        this.vozacNezgodaIzvestaj = vozacNezgodaIzvestaj;
    }

    @JsonIgnore
    private VozacNezgodaIzvestajDTO vozacNezgodaIzvestaj;

    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
