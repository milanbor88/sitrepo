package com.example.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;


@Entity(name = "vozacveca")
@DiscriminatorValue(value = "VozacVeca" )
public class VozacNezgodaVeca extends  VozacNezgodaIzvestaj implements Serializable {

    public VozacNezgoda getVozacNezgoda() {
        return vozacNezgoda;
    }

    public void setVozacNezgoda(VozacNezgoda vozacNezgoda) {
        this.vozacNezgoda = vozacNezgoda;
    }

    @ManyToOne(cascade = CascadeType.MERGE, optional = true)
    private VozacNezgoda vozacNezgoda;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        VozacNezgodaVeca that = (VozacNezgodaVeca) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

}
