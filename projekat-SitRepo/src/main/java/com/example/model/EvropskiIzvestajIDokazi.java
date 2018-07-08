package com.example.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "evropski_izvestaj_dokazi")
public class EvropskiIzvestajIDokazi {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(name = "slike_dokazi_evropski_izvestaj", columnDefinition = "LONGBLOB")
    byte[] slike;

    private String status;

    @Version
    private long version;


    public VozacNezgodaManja getVozacNezgodaManja() {
        return vozacNezgodaManja;
    }

    public void setVozacNezgodaManja(VozacNezgodaManja vozacNezgodaManja) {
        this.vozacNezgodaManja = vozacNezgodaManja;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private VozacNezgodaManja vozacNezgodaManja;


    public EvropskiIzvestajIDokazi() {
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

    public byte[] getSlike() {
        return slike;
    }

    public void setSlike(byte[] slike) {
        this.slike = slike;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        EvropskiIzvestajIDokazi that = (EvropskiIzvestajIDokazi) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }
}
