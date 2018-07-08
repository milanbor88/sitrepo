package com.example.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "vozac_nezgoda_izvestaj")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Nesreca_Tip", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = VozacNezgodaVeca.class, name = "VozacVeca"),
        @JsonSubTypes.Type(value = VozacNezgodaManja.class, name = "VozacManja")
})
public class VozacNezgodaIzvestaj implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vozacnezgodaIzv_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, optional = true)
    private User user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "vozacNezgodaIzvestaj")
    private List<VozacSvedok> vozacSvedok ;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vozacNezgodaIzvestaj")
    List<PovredjeniDrugoVozilo> povredjeniDrugoVozilo ;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vozacNezgodaIzvestaj")
    List<PovredjeniVanVozila> povredjeniVanVozila ;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vozacNezgodaIzvestaj")
    List<PovredjeniVaseVozilo> povredjeniVaseVozilo ;


    @Column(name = "naziv_osiguranja")
    private String nazivOsiguranja;

    @Length(min = 1, max = 10, message = "Polje mora sadržati od 1  do 10 karaktera")
    @Column(name = "broj_ugovora_osiguranja")
    private String brojUgovoraOsiguranja;

    @Length(max = 15, message = "Maksimalni broj karaktera je 15")
    @Column(name = "broj_zelenog_kartona_osiguranje")
    private String brojZelenogKartona;

    @Column(name = "polisa_vazi_od_osiguranje")
    private String polisaVaziOd;

    @Column(name = "polisa_vazi_do_osiguranje")
    private String polisaVaziDo;

    @Column(name = "zeleni_karton_vazi_od_osiguranje")
    private String zeleniKartonVaziOd;

    @Column(name = "zeleni_karton_vazi_do_osiguranje")
    private String zeleniKartonVaziDo;

    @Length(max = 20, message = "Maksimalni broj karaktera je 20")
    @Column(name = "filijala_osiguranja")
    private String filijala;

    @Column(name = "drzava_osiguranja")
    private String drzava;

    @Length(min = 1, max = 20, message = "Polje može sadržati min 1 a max 20 karaktera")
    @Column(name = "ime_prezime_uo")
    private String imePrezimeUO;

    @Length(max = 20, message = "Maksimalni broj karaktera je 20")
    @Column(name = "adresa_uo")
    private String adresaUO;

    @Length(max = 7, message = "Maksimalni broj karaktera je 7")
    @Column(name = "postanski_broj_uo")
    private String postanskiBrojUO;

    @Length(max = 20, message = "Maksimalni broj karaktera je 20")
    @Column(name = "telefon_uo")
    private String telefonUO;

    @Column(name = "mail_uo")
    private String mailUO;


    @Length(min = 1, max = 10, message = "Polje može sadržati od 1 do 10 karaktera")
    @Column(name = "marka_vozila")
    String markaVozila;

    @Length(min = 1, max = 10, message = "Polje može sadržati od 1 do 10 karaktera")
    @Column(name = "tip_vozila")
    String tipVozila;

    @Length(min = 7, max = 8, message = "Polje može sadržati 7 ili 8 karaktera")
    @Column(name = "registarska_oznaka_vozila")
    String regOznakaVoz;

    @Column(name = "drzava_reg_vozila")
    String drzavaRegVozila;

    @Length(min = 7, max = 9, message = "Polje mora sadržati od 7  do 9 karaktera")
    @Column(name = "reg_oznaka_pr")
    private String regOznakaPr;

    @Length(max = 20,  message = "Polje može imati maksimum 20 karaktera")
    @Column(name = "drzava_reg_prikolica")
    private String drRegPrikolica;

    @Length(min = 1, message = "Polje mora sadržati minimum 1 karakter")
    @Column(name = "max_dozvoljena_tezina_prikolica")
    private String maxTezinaPr;

    @Column(name = "materijlna_steta_osigurana_ugovorom")
    private String materijalnaStetaOsiguranaUgovorom;

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

    public String getMaterijalnaStetaOsiguranaUgovorom() {
        return materijalnaStetaOsiguranaUgovorom;
    }

    public void setMaterijalnaStetaOsiguranaUgovorom(String materijalnaStetaOsiguranaUgovorom) {
        this.materijalnaStetaOsiguranaUgovorom = materijalnaStetaOsiguranaUgovorom;
    }

    @Column(name = "poslat")
    private boolean poslat;


    @Version
    private long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<VozacSvedok> getVozacSvedok() {
        return vozacSvedok;
    }

    public void setVozacSvedok(List<VozacSvedok> vozacSvedok) {
        this.vozacSvedok = vozacSvedok;
    }

    public List<PovredjeniDrugoVozilo> getPovredjeniDrugoVozilo() {
        return povredjeniDrugoVozilo;
    }

    public void setPovredjeniDrugoVozilo(List<PovredjeniDrugoVozilo> povredjeniDrugoVozilo) {
        this.povredjeniDrugoVozilo = povredjeniDrugoVozilo;
    }

    public List<PovredjeniVanVozila> getPovredjeniVanVozila() {
        return povredjeniVanVozila;
    }

    public void setPovredjeniVanVozila(List<PovredjeniVanVozila> povredjeniVanVozila) {
        this.povredjeniVanVozila = povredjeniVanVozila;
    }

    public List<PovredjeniVaseVozilo> getPovredjeniVaseVozilo() {
        return povredjeniVaseVozilo;
    }

    public void setPovredjeniVaseVozilo(List<PovredjeniVaseVozilo> povredjeniVaseVozilo) {
        this.povredjeniVaseVozilo = povredjeniVaseVozilo;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public boolean isPoslat() {
        return poslat;
    }

    public void setPoslat(boolean poslat) {
        this.poslat = poslat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        VozacNezgodaIzvestaj that = (VozacNezgodaIzvestaj) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
