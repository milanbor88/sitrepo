package com.example.dto;

import com.example.model.VozacNezgodaManja;
import com.fasterxml.jackson.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.List;

import static org.codehaus.groovy.tools.shell.util.Logger.io;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = VozacNezgodaVecaDTO.class, name = "VozacVeca"),
        @JsonSubTypes.Type(value = VozacNezgodaManjaDTO.class, name = "VozacManja")
})
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
public class VozacNezgodaIzvestajDTO implements Serializable {

    private Long id;

    @JsonIgnore
    private UserDTO user;

    private List<VozacSvedokDTO> vozacSvedok;

    private List<PovredjeniDrugoVoziloDTO> povredjeniDrugoVozilo;

    private List<PovredjeniVanVozilaDTO> povredjeniVanVozila;

    private List<PovredjeniVaseVoziloDTO> povredjeniVaseVozilo;

    public String getNazivOsiguranja() {
        return nazivOsiguranja;
    }

    public void setNazivOsiguranja(String nazivOsiguranja) {
        this.nazivOsiguranja = nazivOsiguranja;
    }

    public String getBrojUgovoraOsiguranja() {
        return brojUgovoraOsiguranja;
    }

    public void setBrojUgovoraOsiguranja(String brojUgovoraOsiguranja) {
        this.brojUgovoraOsiguranja = brojUgovoraOsiguranja;
    }

    public String getBrojZelenogKartona() {
        return brojZelenogKartona;
    }

    public void setBrojZelenogKartona(String brojZelenogKartona) {
        this.brojZelenogKartona = brojZelenogKartona;
    }

    public String getPolisaVaziOd() {
        return polisaVaziOd;
    }

    public void setPolisaVaziOd(String polisaVaziOd) {
        this.polisaVaziOd = polisaVaziOd;
    }

    public String getPolisaVaziDo() {
        return polisaVaziDo;
    }

    public void setPolisaVaziDo(String polisaVaziDo) {
        this.polisaVaziDo = polisaVaziDo;
    }

    public String getZeleniKartonVaziOd() {
        return zeleniKartonVaziOd;
    }

    public void setZeleniKartonVaziOd(String zeleniKartonVaziOd) {
        this.zeleniKartonVaziOd = zeleniKartonVaziOd;
    }

    public String getZeleniKartonVaziDo() {
        return zeleniKartonVaziDo;
    }

    public void setZeleniKartonVaziDo(String zeleniKartonVaziDo) {
        this.zeleniKartonVaziDo = zeleniKartonVaziDo;
    }

    public String getFilijala() {
        return filijala;
    }

    public void setFilijala(String filijala) {
        this.filijala = filijala;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
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

    public String getMarkaVozila() {
        return markaVozila;
    }

    public void setMarkaVozila(String markaVozila) {
        this.markaVozila = markaVozila;
    }

    public String getTipVozila() {
        return tipVozila;
    }

    public void setTipVozila(String tipVozila) {
        this.tipVozila = tipVozila;
    }

    public String getRegOznakaVoz() {
        return regOznakaVoz;
    }

    public void setRegOznakaVoz(String regOznakaVoz) {
        this.regOznakaVoz = regOznakaVoz;
    }

    public String getDrzavaRegVozila() {
        return drzavaRegVozila;
    }

    public void setDrzavaRegVozila(String drzavaRegVozila) {
        this.drzavaRegVozila = drzavaRegVozila;
    }

    public String getRegOznakaPr() {
        return regOznakaPr;
    }

    public void setRegOznakaPr(String regOznakaPr) {
        this.regOznakaPr = regOznakaPr;
    }

    public String getDrRegPrikolica() {
        return drRegPrikolica;
    }

    public void setDrRegPrikolica(String drRegPrikolica) {
        this.drRegPrikolica = drRegPrikolica;
    }

    public String getMaxTezinaPr() {
        return maxTezinaPr;
    }

    public void setMaxTezinaPr(String maxTezinaPr) {
        this.maxTezinaPr = maxTezinaPr;
    }

    private String nazivOsiguranja;

    @Length(min = 1, max = 10, message = "Polje mora sadržati od 1  do 10 karaktera")

    private String brojUgovoraOsiguranja;

    @Length(max = 15, message = "Maksimalni broj karaktera je 15")
    private String brojZelenogKartona;

    private String polisaVaziOd;

    private String polisaVaziDo;

    private String zeleniKartonVaziOd;

    private String zeleniKartonVaziDo;

    @Length(max = 20, message = "Maksimalni broj karaktera je 20")
    private String filijala;

    private String drzava;

    @Length(min = 1, max = 20, message = "Polje može sadržati min 1 a max 20 karaktera")
    private String imePrezimeUO;

    @Length(max = 20, message = "Maksimalni broj karaktera je 20")
    private String adresaUO;

    @Length(max = 7, message = "Maksimalni broj karaktera je 7")
    private String postanskiBrojUO;

    @Length(max = 20, message = "Maksimalni broj karaktera je 20")
    private String telefonUO;

    private String mailUO;


    @Length(min = 1, max = 10, message = "Polje može sadržati od 1 do 10 karaktera")
    String markaVozila;

    @Length(min = 1, max = 10, message = "Polje može sadržati od 1 do 10 karaktera")
    String tipVozila;

    @Length(min = 7, max = 8, message = "Polje može sadržati 7 ili 8 karaktera")
    String regOznakaVoz;

    String drzavaRegVozila;

    @Length(min = 7, max = 9, message = "Polje mora sadržati od 7  do 9 karaktera")
    private String regOznakaPr;

    @Length(max = 20,  message = "Polje može imati maksimum 20 karaktera")
    private String drRegPrikolica;

    @Length(min = 1, message = "Polje mora sadržati minimum 1 karakter")
    private String maxTezinaPr;

    private String materijalnaStetaOsiguranaUgovorom;

    public String getMaterijalnaStetaOsiguranaUgovorom() {
        return materijalnaStetaOsiguranaUgovorom;
    }

    public void setMaterijalnaStetaOsiguranaUgovorom(String materijalnaStetaOsiguranaUgovorom) {
        this.materijalnaStetaOsiguranaUgovorom = materijalnaStetaOsiguranaUgovorom;
    }

    private boolean poslat;

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


    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public List<VozacSvedokDTO> getVozacSvedok() {
        return vozacSvedok;
    }

    public void setVozacSvedok(List<VozacSvedokDTO> vozacSvedok) {
        this.vozacSvedok = vozacSvedok;
    }

    public List<PovredjeniDrugoVoziloDTO> getPovredjeniDrugoVozilo() {
        return povredjeniDrugoVozilo;
    }

    public void setPovredjeniDrugoVozilo(List<PovredjeniDrugoVoziloDTO> povredjeniDrugoVozilo) {
        this.povredjeniDrugoVozilo = povredjeniDrugoVozilo;
    }

    public List<PovredjeniVanVozilaDTO> getPovredjeniVanVozila() {
        return povredjeniVanVozila;
    }

    public void setPovredjeniVanVozila(List<PovredjeniVanVozilaDTO> povredjeniVanVozila) {
        this.povredjeniVanVozila = povredjeniVanVozila;
    }

    public List<PovredjeniVaseVoziloDTO> getPovredjeniVaseVozilo() {
        return povredjeniVaseVozilo;
    }

    public void setPovredjeniVaseVozilo(List<PovredjeniVaseVoziloDTO> povredjeniVaseVozilo) {
        this.povredjeniVaseVozilo = povredjeniVaseVozilo;
    }

    public boolean isPoslat() {
        return poslat;
    }

    public void setPoslat(boolean poslat) {
        this.poslat = poslat;
    }
}
