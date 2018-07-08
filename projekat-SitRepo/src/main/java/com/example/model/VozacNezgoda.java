package com.example.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "vozac_nezgoda")
public class VozacNezgoda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String datumNezgode;
    private String vremeNezgode;
    private String mestoNezgode;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vozacNezgoda")
    private List<VozacNezgodaVeca> vozacNezgodaVeca;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "vozacNezgoda")
    private List<VozacNezgodaManja> vozacNezgodaManja;

    @Version
    private long version;


    public List<VozacNezgodaManja> getVozacNezgodaManja() {
        return vozacNezgodaManja;
    }

    public void setVozacNezgodaManja(List<VozacNezgodaManja> vozacNezgodaManja) {
        this.vozacNezgodaManja = vozacNezgodaManja;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public VozacNezgoda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatumNezgode() {
        return datumNezgode;
    }

    public void setDatumNezgode(String datumNezgode) {
        this.datumNezgode = datumNezgode;
    }

    public String getVremeNezgode() {
        return vremeNezgode;
    }

    public void setVremeNezgode(String vremeNezgode) {
        this.vremeNezgode = vremeNezgode;
    }

    public String getMestoNezgode() {
        return mestoNezgode;
    }

    public void setMestoNezgode(String mestoNezgode) {
        this.mestoNezgode = mestoNezgode;
    }

    public VozacNezgoda(String datumNezgode, String vremeNezgode, String mestoNezgode) {
        this.datumNezgode = datumNezgode;
        this.vremeNezgode = vremeNezgode;
        this.mestoNezgode = mestoNezgode;
    }




    public List<VozacNezgodaVeca> getVozacNezgodaVeca() {
        return vozacNezgodaVeca;
    }

    public void setVozacNezgodaVeca(List<VozacNezgodaVeca> vozacNezgodaVeca) {
        this.vozacNezgodaVeca = vozacNezgodaVeca;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        VozacNezgoda that = (VozacNezgoda) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }
}
