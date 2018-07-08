package com.example.model;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table (name = "putnik_nezgoda")
public class PutnikNezgoda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String datumNezgodePU;
    private String vremeNezgodePU;
    private String mestoNezgodePU;

    @Version
    private long version;


    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public PutnikNezgoda() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatumNezgodePU() {
        return datumNezgodePU;
    }

    public void setDatumNezgodePU(String datumNezgodePU) {
        this.datumNezgodePU = datumNezgodePU;
    }

    public String getVremeNezgodePU() {
        return vremeNezgodePU;
    }

    public void setVremeNezgodePU(String vremeNezgodePU) {
        this.vremeNezgodePU = vremeNezgodePU;
    }

    public String getMestoNezgodePU() {
        return mestoNezgodePU;
    }

    public void setMestoNezgodePU(String mestoNezgodePU) {
        this.mestoNezgodePU = mestoNezgodePU;
    }

    public List<PutnikIzvestaj> getPutnikIzvestaj() {
        return putnikIzvestaj;
    }

    public void setPutnikIzvestaj(List<PutnikIzvestaj> putnikIzvestaj) {
        this.putnikIzvestaj = putnikIzvestaj;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "putnikNezgoda")
    private List<PutnikIzvestaj> putnikIzvestaj;


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        PutnikNezgoda that = (PutnikNezgoda) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }
}
