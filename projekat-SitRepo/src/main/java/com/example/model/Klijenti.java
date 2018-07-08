package com.example.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Klijenti {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min=1, max = 30, message = "Polje mora sadržati izmedju 1 i 30 karaktera")
    @Column(name = "name")
    private String name;

    @Length(max = 30, message = "Polje može sadržati do 30 karaktera")
    @Column(name = "tip_klijenta")
    private String tipKlijenta;

    @Length(max = 25, message = "Polje može sadržati do 25 karaktera")
    @Column(name = "adresa")
    private String adresa;

    @Length(max = 12, message = "Polje može sadržati do 12 karaktera")
    @Column(name = "broj_telefona")
    private String brojTelefona;

    @Column(name = "drzava")
    private String drzava;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "email")
    private String email;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Length(min=6, max=6, message="poruka")
    @Column(name = "promoKod")
    private String promoKod;

    @Column(name = "slika_klijenta", columnDefinition = "LONGBLOB")
    private byte[] slikaKlijenta;

    @OneToMany(orphanRemoval = false, fetch = FetchType.LAZY, mappedBy = "klijenti")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getPromoKod() {
        return promoKod;
    }

    public void setPromoKod(String promoKod) {
        this.promoKod = promoKod;
    }

    public byte[] getSlikaKlijenta() {
        return slikaKlijenta;
    }

    public void setSlikaKlijenta(byte[] slikaKlijenta) {
        this.slikaKlijenta = slikaKlijenta;
    }

    public String getDodatneInfo() {
        return dodatneInfo;
    }

    public void setDodatneInfo(String dodatneInfo) {
        this.dodatneInfo = dodatneInfo;
    }

    @Column(name = "dodatne_info")
    private String dodatneInfo;


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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipKlijenta() {
        return tipKlijenta;
    }

    public void setTipKlijenta(String tipKlijenta) {
        this.tipKlijenta = tipKlijenta;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Klijenti() {

    }

    public Klijenti(String name, String tipKlijenta) {
        this.name = name;
        this.tipKlijenta = tipKlijenta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Klijenti that = (Klijenti) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }
}
