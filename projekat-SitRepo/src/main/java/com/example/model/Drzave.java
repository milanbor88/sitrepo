package com.example.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Drzave {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String drzava;

    @Version
    private long version;

    public Drzave() {
    }


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

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Drzave that = (Drzave) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }
}
