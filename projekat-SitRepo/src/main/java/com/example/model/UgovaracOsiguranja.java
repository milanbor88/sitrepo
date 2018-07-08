package com.example.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Ugovarac_osiguranja")
public class UgovaracOsiguranja {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fotografija_polise", columnDefinition = "LONGBLOB")
    private byte[] fotografijaPoliseUO;

    @Length(min = 1, max = 20, message = "Polje može sadržati min 1 a max 20 karaktera")
    @Column(name = "ime")
    private String imePrezimeUO;

//    @Length(min = 1, max = 20, message = "Polje može sadržati min 1 a max 20 karaktera")
//    @Column(name = "prezime")
//    private String prezimeUO;

    @Length(max = 20, message = "Maksimalni broj karaktera je 20")
    @Column(name = "adresa")
    private String adresaUO;

    @Length(max = 7, message = "Maksimalni broj karaktera je 7")
    @Column(name = "postanski_broj")
    private String postanskiBrojUO;

    @Length(max = 20, message = "Maksimalni broj karaktera je 20")
    @Column(name = "telefon")
    private String telefonUO;

    @Column(name = "mail")
    private String mailUO;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private User user;

    @Version
    private long version;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getFotografijaPoliseUO() {
        return fotografijaPoliseUO;
    }

    public void setFotografijaPoliseUO(byte[] fotografijaPoliseUO) {
        this.fotografijaPoliseUO = fotografijaPoliseUO;
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

        UgovaracOsiguranja that = (UgovaracOsiguranja) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }
}
