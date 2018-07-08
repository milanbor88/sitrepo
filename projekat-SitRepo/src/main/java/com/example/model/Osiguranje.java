package com.example.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Osiguranje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "naziv")
    private String naziv;

    @Length(min = 1, max = 10, message = "Polje mora sadržati od 1  do 10 karaktera")
    @Column(name = "broj_ugovora")
    private String brojUgovora;

    @Length(max = 15, message = "Maksimalni broj karaktera je 15")
    @Column(name = "broj_zelenog_kartona")
    private String brojZelenogKartona;

    @Column(name = "polisa_vazi_od")
    private String polisaVaziOd;

    @Column(name = "polisa_vazi_do")
    private String polisaVaziDo;

    @Column(name = "zeleni_karton_vazi_od")
    private String zeleniKartonVaziOd;

    @Column(name = "zeleni_karton_vazi_do")
    private String zeleniKartonVaziDo;

    @Length(max = 20, message = "Maksimalni broj karaktera je 20")
    @Column(name = "filijala")
    private String filijala;

    @Column(name = "drzava")
    private String drzava;

    @Column(name = "materijlna_steta_osigurana_ugovorom")
    private String materijalnaStetaOsiguranaUgovorom;

    @Column(name = "status")
    private String status;

    @Version
    private long version;

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getBrojUgovora() {
        return brojUgovora;
    }

    public void setBrojUgovora(String brojUgovora) {
        this.brojUgovora = brojUgovora;
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

    public String getMaterijalnaStetaOsiguranaUgovorom() {
        return materijalnaStetaOsiguranaUgovorom;
    }

    public void setMaterijalnaStetaOsiguranaUgovorom(String materijalnaStetaOsiguranaUgovorom) {
        this.materijalnaStetaOsiguranaUgovorom = materijalnaStetaOsiguranaUgovorom;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Osiguranje that = (Osiguranje) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }
}
