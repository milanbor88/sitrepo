package com.example.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@DiscriminatorValue(value = "VozacManja" )
public class VozacNezgodaManja extends  VozacNezgodaIzvestaj implements Serializable {



    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "vozacNezgodaManja")
    List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazis;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "vozacNezgodaManja")
    List<UgovaracOsiguranjaB> ugovaracOsiguranjaB;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "vozacNezgodaManja")
    List<Ucesnik> ucesniks;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "vozacNezgodaManja")
    List<OsiguranjeB> osiguranjeB;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "vozacNezgodaManja")
    List<VoziloB> voziloB;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "vozacNezgodaManja")
    List<PrikolicaB> prikolicaB;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "vozacNezgodaManja")
    List<OkolnostiNezgode> okolnostiNezgodes;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "vozacNezgodaManja")
    List<OkolnostiNezgodeB> okolnostiNezgodesb;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "vozacNezgodaManja")
    List<ZvanicniOrgani> zvanicniOrganis;

    @ManyToOne(cascade = CascadeType.ALL, optional = true)
    private VozacNezgoda vozacNezgoda;


    public List<OkolnostiNezgodeB> getOkolnostiNezgodesb() {
        return okolnostiNezgodesb;
    }

    public void setOkolnostiNezgodesb(List<OkolnostiNezgodeB> okolnostiNezgodesb) {
        this.okolnostiNezgodesb = okolnostiNezgodesb;
    }

    public VozacNezgoda getVozacNezgoda() {
        return vozacNezgoda;
    }

    public void setVozacNezgoda(VozacNezgoda vozacNezgoda) {
        this.vozacNezgoda = vozacNezgoda;
    }

    public List<OsiguranjeB> getOsiguranjeB() {
        return osiguranjeB;
    }

    public void setOsiguranjeB(List<OsiguranjeB> osiguranjeB) {
        this.osiguranjeB = osiguranjeB;
    }

    public List<PrikolicaB> getPrikolicaB() {
        return prikolicaB;
    }

    public void setPrikolicaB(List<PrikolicaB> prikolicaB) {
        this.prikolicaB = prikolicaB;
    }

    public List<OkolnostiNezgode> getOkolnostiNezgodes() {
        return okolnostiNezgodes;
    }

    public void setOkolnostiNezgodes(List<OkolnostiNezgode> okolnostiNezgodes) {
        this.okolnostiNezgodes = okolnostiNezgodes;
    }

    public List<ZvanicniOrgani> getZvanicniOrganis() {
        return zvanicniOrganis;
    }

    public void setZvanicniOrganis(List<ZvanicniOrgani> zvanicniOrganis) {
        this.zvanicniOrganis = zvanicniOrganis;
    }

    public List<UgovaracOsiguranjaB> getUgovaracOsiguranjaB() {
        return ugovaracOsiguranjaB;
    }

    public void setUgovaracOsiguranjaB(List<UgovaracOsiguranjaB> ugovaracOsiguranjaB) {
        this.ugovaracOsiguranjaB = ugovaracOsiguranjaB;
    }

    public List<VoziloB> getVoziloB() {
        return voziloB;
    }

    public void setVoziloB(List<VoziloB> voziloB) {
        this.voziloB = voziloB;
    }

    public List<Ucesnik> getUcesniks() {
        return ucesniks;
    }

    public void setUcesniks(List<Ucesnik> ucesniks) {
        this.ucesniks = ucesniks;
    }

    public List<EvropskiIzvestajIDokazi> getEvropskiIzvestajIDokazis() {
        return evropskiIzvestajIDokazis;
    }

    public void setEvropskiIzvestajIDokazis(List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazis) {
        this.evropskiIzvestajIDokazis = evropskiIzvestajIDokazis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        VozacNezgodaManja that = (VozacNezgodaManja) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
