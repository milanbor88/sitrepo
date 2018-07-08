package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@ApiModel(value = "Korisnik", description = "Korisnik model")
public class UserDTO implements Serializable {

    private long id;

    @ApiModelProperty(notes = "email korisnika", dataType = "String")
    @NotBlank(message = "Ovo polje je obavezno")
    @Length(min = 1, max = 40,message = "Polje može sadržati od {min} do {min} karaktera")

    @Email(message = "{email.badtype}")
    private String email;

    @NotBlank(message = "Ovo polje je obavezno")
    @ApiModelProperty(notes = "password", dataType = "String")
    private String password;

    @ApiModelProperty(notes = "Ime korisnika ovo polje je obavezno", dataType = "String")
    @NotBlank(message = "Ovo polje je obavezno")
    @Length( min = 1, max = 12, message = "Polje može sadržati do 12 karaktera")
    private String name;

    @ApiModelProperty(notes = "Prezime korisnika, polje je obavezno", dataType = "String")
    @NotBlank(message = "Ovo polje je obavezno")
    @Length(max = 15, message = "Polje može sadržati do 15 karaktera")
    private String lastName;

    @ApiModelProperty(notes = "adresa korisnika ", dataType = "String")
    @Length(max = 25, message = "Polje može sadržati do 25 karaktera")
    private String adresa;

    @ApiModelProperty(notes = "postanski broj adrese korisnika", dataType = "String")
    @Length(max = 5, message = "Polje mora da sadrži 5 brojeva")
    private String postanskiBroj;

    @ApiModelProperty(notes = "drzava rodjenja korisnika", dataType = "String")
    @Length(max = 20, message = "Polje može sadržati do 20 karaktera")
    private String drzava;

    @ApiModelProperty(notes = "kontakt telefon korisnika", dataType = "String")
    @Length(max = 20, message = "Polje može sadržati do 20 karaktera")
    private String telefon;

    @ApiModelProperty(notes = "broj vozacke dozvole korisnika", dataType = "String")
    @Length(max = 20, message = "Polje može sadržati do 20 karaktera")
    private String brVozackeDozvole;

    @ApiModelProperty(notes = "kategorija dozvole", dataType = "String")
    private String kategorijaDozvole;

    private String vozackaDozvolaVaziDo;

    @JsonIgnore
    private boolean active;

    @ApiModelProperty(notes = "status ucesnika ", dataType = "String")
    private String status;

    @JsonIgnore
    private byte[] slikaUser;

    @JsonIgnore
    private RoleDTO roleDTOList;

    public KlijentiDTO getKlijentiDTO() {
        return klijentiDTO;
    }

    public void setKlijentiDTO(KlijentiDTO klijentiDTO) {
        this.klijentiDTO = klijentiDTO;
    }

    @JsonIgnore
    private KlijentiDTO klijentiDTO;

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    private String slika;

    public boolean isActive() {
        return active;
    }

    public byte[] getSlikaUser() {
        return slikaUser;
    }

    public void setSlikaUser(byte[] slikaUser) {
        this.slikaUser = slikaUser;
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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RoleDTO getRoleDTOList() {
        return roleDTOList;
    }

    public void setRoleDTOList(RoleDTO roleDTOList) {
        this.roleDTOList = roleDTOList;
    }

    private Long version;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
