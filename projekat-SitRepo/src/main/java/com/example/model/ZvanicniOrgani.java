package com.example.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "zvanicni_organi")
public class ZvanicniOrgani {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "zvanicni_izvestaj")
    String daLiJeSacinjenZvanicniIzvestaj;

    @Column(name = "kada_je_sacinjen_evropski_izvestaj")
    String odKogaJeSacinjenZvanicniIzvestaj;

    @Length(max = 15, message = "Polje može sadržati do 15 karaktera")
    @Column(name = "broj_zvanisnog_izvestaja")
    String brojZvanicnogIzvestaja;

    @Column(name = "alko_test")
    String daLiJeVozacPodvrgnutTestiranjuAlko;

    @Column(name = "narko_test")
    String daLiJeVozacPodvrgnutTestiranjuNarko;

    @Column(name = "odbijen_test")
    String daLiJeVozacOdbioTest;

    @Column(name = "namena_upotrebe_vozila")
    String namenaUpotrebeMV;

    @Length(min = 1, max = 25, message = "Polje može sadržati do 25 karaktera")
    @Column(name = "mest_pregleda_vozila")
    String mestoPregledaVozila;

    @Column(name = "imobilizovano_vozilo")
    String imobilisanoVozilo;


    @Version
    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private VozacNezgodaManja vozacNezgodaManja;


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

    public String getNamenaUpotrebeMV() {
        return namenaUpotrebeMV;
    }

    public void setNamenaUpotrebeMV(String namenaUpotrebeMV) {
        this.namenaUpotrebeMV = namenaUpotrebeMV;
    }


    public VozacNezgodaManja getVozacNezgodaManja() {
        return vozacNezgodaManja;
    }

    public void setVozacNezgodaManja(VozacNezgodaManja vozacNezgodaManja) {
        this.vozacNezgodaManja = vozacNezgodaManja;
    }

    public ZvanicniOrgani() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ZvanicniOrgani that = (ZvanicniOrgani) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }

}
