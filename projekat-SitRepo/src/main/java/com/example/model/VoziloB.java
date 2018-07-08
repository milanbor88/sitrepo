package com.example.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class VoziloB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Length(min = 1, max = 10, message = "Polje može sadržati od 1 do 10 karaktera")
    @Column(name = "marka")
    String marka;

    @Length(min = 1, max = 10, message = "Polje može sadržati od 1 do 10 karaktera")
    @Column(name = "tip")
    String tip;

    @Column(name = "slika_vozila", columnDefinition = "LONGBLOB")
    private byte[] slikaVozila;

    @Length(min = 7, max = 8, message = "Polje može sadržati 7 ili 8 karaktera")
    @Column(name = "registarska_oznaka")
    String registarskaOznakaVO;

    @Column(name = "drzava_u_kojoj_je_vozilo_reg.")
    String drzavaUKojojJeVoziloRegistrovano;


    @Version
    private long version;

    String status;


    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private VozacNezgodaManja vozacNezgodaManja;

    public VozacNezgodaManja getVozacNezgodaManja() {
        return vozacNezgodaManja;
    }

    public void setVozacNezgodaManja(VozacNezgodaManja vozacNezgodaManja) {
        this.vozacNezgodaManja = vozacNezgodaManja;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] getSlikaVozila() {
        return slikaVozila;
    }

    public void setSlikaVozila(byte[] slikaVozila) {
        this.slikaVozila = slikaVozila;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public String getRegistarskaOznakaVO() {
        return registarskaOznakaVO;
    }

    public void setRegistarskaOznakaVO(String registarskaOznakaVO) {
        this.registarskaOznakaVO = registarskaOznakaVO;
    }

    public String getDrzavaUKojojJeVoziloRegistrovano() {
        return drzavaUKojojJeVoziloRegistrovano;
    }

    public void setDrzavaUKojojJeVoziloRegistrovano(String drzavaUKojojJeVoziloRegistrovano) {
        this.drzavaUKojojJeVoziloRegistrovano = drzavaUKojojJeVoziloRegistrovano;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        VoziloB that = (VoziloB) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }
}
