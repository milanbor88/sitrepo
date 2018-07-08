package com.example.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PrikolicaB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "slika_prikolice", columnDefinition = "LONGBLOB")
    private byte[] slikaPrikolice;

    @Length(min = 7, max = 9, message = "Polje mora sadržati od 7  do 9 karaktera")
    @Column(name = "registarska_oznaka")
    private String registarskaOznaka;

    @Length(max = 20,  message = "Polje može imati maksimum 20 karaktera")
    @Column(name = "drzava_u_kojoj_je_registrovana")
    private String drzavaUKojojJeRegistrovana;

    @Length(min = 1, message = "Polje mora sadržati minimum 1 karakter")
    @Column(name = "maksimalna_dozvoljena_tezina")
    private String maksimalnaDozvoljenaTezina;

    private String status;

    @Version
    private long version;


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

    public byte[] getSlikaPrikolice() {
        return slikaPrikolice;
    }

    public void setSlikaPrikolice(byte[] slikaPrikolice) {
        this.slikaPrikolice = slikaPrikolice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistarskaOznaka() {
        return registarskaOznaka;
    }

    public void setRegistarskaOznaka(String registarskaOznaka) {
        this.registarskaOznaka = registarskaOznaka;
    }

    public String getDrzavaUKojojJeRegistrovana() {
        return drzavaUKojojJeRegistrovana;
    }

    public void setDrzavaUKojojJeRegistrovana(String drzavaUKojojJeRegistrovana) {
        this.drzavaUKojojJeRegistrovana = drzavaUKojojJeRegistrovana;
    }

    public String getMaksimalnaDozvoljenaTezina() {
        return maksimalnaDozvoljenaTezina;
    }

    public void setMaksimalnaDozvoljenaTezina(String maksimalnaDozvoljenaTezina) {
        this.maksimalnaDozvoljenaTezina = maksimalnaDozvoljenaTezina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        PrikolicaB that = (PrikolicaB) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }
}
