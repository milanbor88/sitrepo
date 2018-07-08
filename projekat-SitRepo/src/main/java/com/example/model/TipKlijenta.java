package com.example.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table
public class TipKlijenta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(min=1, max = 20, message = "Polje mora sadržati izmedju 1 i 20 karaktera")
    @Column(name = "naziv")
    private String naziv;

    @Version
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

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
