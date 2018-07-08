package com.example.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "vozac_svedok")
public class VozacSvedok {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fotografija_licne_karte", columnDefinition = "LONGBLOB")
    private byte[] fotografijaLicneKarte;

    @Length(min=1, max= 12, message = "Polje mora da sadrzi minimalno 1 a maksimalno 12 karaktera")
    @Column(name = "ime")
    private String ime;

    @Length(min=1, max= 12, message = "Polje mora da sadrzi minimalno 1 a maksimalno 12 karaktera")
    @Column(name = "prezime")
    private String prezime;

    @Length(min=1, max= 30, message = "Polje mora da sadrzi minimalno 1 a maksimalno 30 karaktera")
    @Column(name = "adresa")
    private String adresa;

    @Length(max= 12, message = "Polje mora da sadrzi do 12 karaktera")
    @Column(name = "telefon")
    private String telefon;

    @Length(max= 12, message = "Polje mora da sadrzi do 12 karaktera")
    @Column(name = "broj_licne_karte")
    private String brLicneKarte;

    @Column(name = "status_nezgode")
    private String status;

    @Version
    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public VozacNezgodaIzvestaj getVozacNezgodaIzvestaj() {
        return vozacNezgodaIzvestaj;
    }

    public void setVozacNezgodaIzvestaj(VozacNezgodaIzvestaj vozacNezgodaIzvestaj) {
        this.vozacNezgodaIzvestaj = vozacNezgodaIzvestaj;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private VozacNezgodaIzvestaj vozacNezgodaIzvestaj;

    public VozacSvedok() {
    }

    public VozacSvedok(byte[] fotografijaLicneKarte, String ime, String prezime, String adresa, String telefon, String brLicneKarte, String status, User user) {
        this.fotografijaLicneKarte = fotografijaLicneKarte;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.telefon = telefon;
        this.brLicneKarte = brLicneKarte;
        this.status = status;
        //  this.user = user;
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

    public byte[] getFotografijaLicneKarte() {
        return fotografijaLicneKarte;
    }

    public void setFotografijaLicneKarte(byte[] fotografijaLicneKarte) {
        this.fotografijaLicneKarte = fotografijaLicneKarte;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        VozacSvedok that = (VozacSvedok) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }
}
