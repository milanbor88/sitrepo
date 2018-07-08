package com.example.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "automobil_tip")
public class AutomobiliTip {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String model;

    @Version
    private long version;

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Automobili automobili;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Automobili getAutomobili() {
        return automobili;
    }

    public void setAutomobili(Automobili automobili) {
        this.automobili = automobili;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AutomobiliTip that = (AutomobiliTip) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }
}
