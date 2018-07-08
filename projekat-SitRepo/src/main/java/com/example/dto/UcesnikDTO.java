package com.example.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class UcesnikDTO implements Serializable{

    private Long id;

    @NotBlank(message = "Ovo polje je obavezno")
    @Length(min = 1, message = "Polje može sadržati do 12 karaktera")
    private String email;

    @NotBlank(message = "Ovo polje je obavezno")
    @Length(max = 12, message = "Polje može sadržati do 12 karaktera")
    private String name;

    @NotBlank(message = "Ovo polje je obavezno")
    @Length(max = 15, message = "Polje može sadržati do 15 karaktera")
    private String lastName;

    @Length(max = 30, message = "Polje može sadržati do 30 karaktera")
    private String adresa;

    @Length(max = 5, message = "Polje mora da sadrži 5 brojeva")
    private String postanskiBroj;

    @Length(max = 20, message = "Polje može sadržati do 20 karaktera")
    private String drzava;

    @Length(max = 20, message = "Polje može sadržati do 20 karaktera")
    private String telefon;

    @Length(max = 20, message = "Polje može sadržati do 20 karaktera")
    private String brVozackeDozvole;

    private String kategorijaDozvole;

    private String vozackaDozvolaVaziDo;

    private String datumRodjenja;

    private byte[] slikaUcesnik;

    private VozacNezgodaManjaDTO vozacNezgodaManja;

    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public byte[] getSlikaUcesnik() {
        return slikaUcesnik;
    }

    public void setSlikaUcesnik(byte[] slikaUcesnik) {
        this.slikaUcesnik = slikaUcesnik;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getPostanskiBroj() {
        return postanskiBroj;
    }

    public void setPostanskiBroj(String postanskiBroj) {
        this.postanskiBroj = postanskiBroj;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getBrVozackeDozvole() {
        return brVozackeDozvole;
    }

    public void setBrVozackeDozvole(String brVozackeDozvole) {
        this.brVozackeDozvole = brVozackeDozvole;
    }

    public String getKategorijaDozvole() {
        return kategorijaDozvole;
    }

    public void setKategorijaDozvole(String kategorijaDozvole) {
        this.kategorijaDozvole = kategorijaDozvole;
    }

    public String getVozackaDozvolaVaziDo() {
        return vozackaDozvolaVaziDo;
    }

    public void setVozackaDozvolaVaziDo(String vozackaDozvolaVaziDo) {
        this.vozackaDozvolaVaziDo = vozackaDozvolaVaziDo;
    }

    public VozacNezgodaManjaDTO getVozacNezgodaManja() {
        return vozacNezgodaManja;
    }

    public void setVozacNezgodaManja(VozacNezgodaManjaDTO vozacNezgodaManja) {
        this.vozacNezgodaManja = vozacNezgodaManja;
    }
}
