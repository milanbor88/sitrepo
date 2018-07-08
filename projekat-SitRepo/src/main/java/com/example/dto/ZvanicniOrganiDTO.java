package com.example.dto;

import com.example.model.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import java.io.Serializable;

public class ZvanicniOrganiDTO implements Serializable {

    Long id;

    String daLiJeSacinjenZvanicniIzvestaj;

    String odKogaJeSacinjenZvanicniIzvestaj;

    @Length(max = 15, message = "Polje može sadržati od 1 do 15 karaktera")
    String brojZvanicnogIzvestaja;

    String daLiJeVozacPodvrgnutTestiranjuAlko;

    String daLiJeVozacPodvrgnutTestiranjuNarko;

    String daLiJeVozacOdbioTest;

    String namenaUpotrebeMV;

    @Length(min = 1, max = 25, message = "Polje može sadržati do 1 do 25 karaktera")
    String mestoPregledaVozila;

    String imobilisanoVozilo;

    private long version;

    private VozacNezgodaManjaDTO vozacNezgodaManja;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDaLiJeSacinjenZvanicniIzvestaj() {
        return daLiJeSacinjenZvanicniIzvestaj;
    }

    public void setDaLiJeSacinjenZvanicniIzvestaj(String daLiJeSacinjenZvanicniIzvestaj) {
        this.daLiJeSacinjenZvanicniIzvestaj = daLiJeSacinjenZvanicniIzvestaj;
    }

    public String getOdKogaJeSacinjenZvanicniIzvestaj() {
        return odKogaJeSacinjenZvanicniIzvestaj;
    }

    public void setOdKogaJeSacinjenZvanicniIzvestaj(String odKogaJeSacinjenZvanicniIzvestaj) {
        this.odKogaJeSacinjenZvanicniIzvestaj = odKogaJeSacinjenZvanicniIzvestaj;
    }

    public String getBrojZvanicnogIzvestaja() {
        return brojZvanicnogIzvestaja;
    }

    public void setBrojZvanicnogIzvestaja(String brojZvanicnogIzvestaja) {
        this.brojZvanicnogIzvestaja = brojZvanicnogIzvestaja;
    }

    public String getDaLiJeVozacPodvrgnutTestiranjuAlko() {
        return daLiJeVozacPodvrgnutTestiranjuAlko;
    }

    public void setDaLiJeVozacPodvrgnutTestiranjuAlko(String daLiJeVozacPodvrgnutTestiranjuAlko) {
        this.daLiJeVozacPodvrgnutTestiranjuAlko = daLiJeVozacPodvrgnutTestiranjuAlko;
    }

    public String getDaLiJeVozacPodvrgnutTestiranjuNarko() {
        return daLiJeVozacPodvrgnutTestiranjuNarko;
    }

    public void setDaLiJeVozacPodvrgnutTestiranjuNarko(String daLiJeVozacPodvrgnutTestiranjuNarko) {
        this.daLiJeVozacPodvrgnutTestiranjuNarko = daLiJeVozacPodvrgnutTestiranjuNarko;
    }

    public String getDaLiJeVozacOdbioTest() {
        return daLiJeVozacOdbioTest;
    }

    public void setDaLiJeVozacOdbioTest(String daLiJeVozacOdbioTest) {
        this.daLiJeVozacOdbioTest = daLiJeVozacOdbioTest;
    }

    public String getNamenaUpotrebeMV() {
        return namenaUpotrebeMV;
    }

    public void setNamenaUpotrebeMV(String namenaUpotrebeMV) {
        this.namenaUpotrebeMV = namenaUpotrebeMV;
    }

    public String getMestoPregledaVozila() {
        return mestoPregledaVozila;
    }

    public void setMestoPregledaVozila(String mestoPregledaVozila) {
        this.mestoPregledaVozila = mestoPregledaVozila;
    }

    public String getImobilisanoVozilo() {
        return imobilisanoVozilo;
    }

    public void setImobilisanoVozilo(String imobilisanoVozilo) {
        this.imobilisanoVozilo = imobilisanoVozilo;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public VozacNezgodaManjaDTO getVozacNezgodaManja() {
        return vozacNezgodaManja;
    }

    public void setVozacNezgodaManja(VozacNezgodaManjaDTO vozacNezgodaManja) {
        this.vozacNezgodaManja = vozacNezgodaManja;
    }
}
