package com.example.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "povredjeni_vase_vozilo")
public class PovredjeniVaseVozilo {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String ime;
    private String prezime;
    private String adresa;
    private String telefon;
    @Column(name = "status_povredjenog_vase_vozilo")
    private String status;

    @Version
    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private VozacNezgodaIzvestaj vozacNezgodaIzvestaj;


    public PovredjeniVaseVozilo(String ime, String prezime, String adresa, String telefon, String status) {
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.telefon = telefon;
        this.status = status;
    }

    public VozacNezgodaIzvestaj getVozacNezgodaIzvestaj() {
        return vozacNezgodaIzvestaj;
    }

    public void setVozacNezgodaIzvestaj(VozacNezgodaIzvestaj vozacNezgodaIzvestaj) {
        this.vozacNezgodaIzvestaj = vozacNezgodaIzvestaj;
    }

    public PovredjeniVaseVozilo() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        PovredjeniVaseVozilo that = (PovredjeniVaseVozilo) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }

}
