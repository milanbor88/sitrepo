package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class PutnikSvedokDTO implements Serializable{

    private Long id;

    @JsonIgnore
    private byte[] fotografijaLicneKarte;

    private String fotoLicneKarte;

    public String getFotoLicneKarte() {
        return fotoLicneKarte;
    }

    public void setFotoLicneKarte(String fotoLicneKarte) {
        this.fotoLicneKarte = fotoLicneKarte;
    }

    @Length(min=1, max= 12, message = "Polje mora da sadrzi minimalno 1 a maksimalno 12 karaktera")
    private String ime;

    @Length(min=1, max= 12, message = "Polje mora da sadrzi minimalno 1 a maksimalno 12 karaktera")
    private String prezime;

    @Length(min=1, max= 25, message = "Polje mora da sadrzi minimalno 1 a maksimalno 25 karaktera")
    private String adresa;

    @Length(max= 12, message = "Polje mora da sadrzi do 12 karaktera")
    private String telefon;

    @Length(max= 12, message = "Polje mora da sadrzi do 12 karaktera")
    private String brLicneKarte;

    private PutnikIzvestajDTO putnikIzvestajDTO;

    public PutnikIzvestajDTO getPutnikIzvestajDTO() {
        return putnikIzvestajDTO;
    }

    public void setPutnikIzvestajDTO(PutnikIzvestajDTO putnikIzvestajDTO) {
        this.putnikIzvestajDTO = putnikIzvestajDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[]  getFotografijaLicneKarte() {
        return fotografijaLicneKarte;
    }

    public void setFotografijaLicneKarte(byte[]  fotografijaLicneKarte) {
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

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    private long version;
}
