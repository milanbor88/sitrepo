package com.example.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "automobil")
public class Automobili {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String marka;

    @Version
    private long version;


    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "automobili")
    private List<AutomobiliTip> tipAutomobila;

    public List<AutomobiliTip> getTipAutomobila() {
        return tipAutomobila;
    }

    public void setTipAutomobila(List<AutomobiliTip> tipAutomobila) {
        this.tipAutomobila = tipAutomobila;
    }

    public Automobili() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Automobili that = (Automobili) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }

}
