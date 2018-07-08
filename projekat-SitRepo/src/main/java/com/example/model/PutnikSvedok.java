package com.example.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "putnikSvedok")
@Table(name = "putnik_svedok")
public class PutnikSvedok {

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

    @Length(min=1, max= 25, message = "Polje mora da sadrzi minimalno 1 a maksimalno 25 karaktera")
    @Column(name = "adresa")
    private String adresa;

    @Length(max= 12, message = "Polje mora da sadrzi do 12 karaktera")
    @Column(name = "telefon")
    private String telefon;

    @Length(max= 12, message = "Polje mora da sadrzi do 12 karaktera")
    @Column(name = "broj_licne_karte")
    private String brLicneKarte;

/*    @ManyToOne(fetch = FetchType.LAZY)
    private User user;*/

/*    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "putnikSvedok")
    private List<Izvestaj> izvestaj;*/


    @Version
    @Column(name = "version")
    public long getVersion() {
        return version;
    }

    @Version
    private long version;

    @ManyToOne(fetch = FetchType.LAZY)
    private PutnikIzvestaj putnikIzvestaj;

    public PutnikIzvestaj getPutnikIzvestaj() {
        return putnikIzvestaj;
    }

    public void setPutnikIzvestaj(PutnikIzvestaj putnikIzvestaj) {
        this.putnikIzvestaj = putnikIzvestaj;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public PutnikSvedok() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFotografijaLicneKarte() {
        return fotografijaLicneKarte;
    }

    public void setFotografijaLicneKarte(byte[] fotografijaLicneKarte) {
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

/*    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public PutnikSvedok(byte[] fotografijaLicneKarte, String ime, String prezime, String adresa, String telefon, String brLicneKarte, User user) {
        this.fotografijaLicneKarte = fotografijaLicneKarte;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.telefon = telefon;
        this.brLicneKarte = brLicneKarte;
       /* this.user = user;*/
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        PutnikSvedok that = (PutnikSvedok) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }
}
