package com.example.dto;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class TipKlijentaDTO {

    private Long id;

    @Length(min=1, max = 20, message = "Polje mora sadržati izmedju 1 i 20 karaktera")
    @NotNull
    private String naziv;

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
