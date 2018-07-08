package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

public class VozacNezgodaManjaDTO extends VozacNezgodaIzvestajDTO implements Serializable {

    @JsonIgnore
    private VozacNezgodaDTO vozacNezgoda;

    List<EvropskiIzvestajIDokaziDTO> evropskiIzvestajIDokazis;

    @JsonIgnore
    List<UgovaracOsiguranjaBDTO> ugovaracOsiguranjaB;
    @JsonIgnore
    List<UcesnikDTO> ucesniks;
    @JsonIgnore
    List<OsiguranjeBDTO> osiguranjeB;
    @JsonIgnore
    List<VoziloBDTO> voziloB;
    @JsonIgnore
    List<PrikolicaBDTO> prikolicaB;
    @JsonIgnore
    List<OkolnostiNezgodeDTO> okolnostiNezgodes;
    @JsonIgnore
    List<OkolnostiNezgodeBDTO> okolnostiNezgodesb;

    public List<EvropskiIzvestajIDokaziDTO> getEvropskiIzvestajIDokazis() {
        return evropskiIzvestajIDokazis;
    }

    public void setEvropskiIzvestajIDokazis(List<EvropskiIzvestajIDokaziDTO> evropskiIzvestajIDokazis) {
        this.evropskiIzvestajIDokazis = evropskiIzvestajIDokazis;
    }

    public List<UgovaracOsiguranjaBDTO> getUgovaracOsiguranjaB() {
        return ugovaracOsiguranjaB;
    }

    public void setUgovaracOsiguranjaB(List<UgovaracOsiguranjaBDTO> ugovaracOsiguranjaB) {
        this.ugovaracOsiguranjaB = ugovaracOsiguranjaB;
    }

    public List<UcesnikDTO> getUcesniks() {
        return ucesniks;
    }

    public void setUcesniks(List<UcesnikDTO> ucesniks) {
        this.ucesniks = ucesniks;
    }

    public List<OsiguranjeBDTO> getOsiguranjeB() {
        return osiguranjeB;
    }

    public void setOsiguranjeB(List<OsiguranjeBDTO> osiguranjeB) {
        this.osiguranjeB = osiguranjeB;
    }

    public List<VoziloBDTO> getVoziloB() {
        return voziloB;
    }

    public void setVoziloB(List<VoziloBDTO> voziloB) {
        this.voziloB = voziloB;
    }

    public List<PrikolicaBDTO> getPrikolicaB() {
        return prikolicaB;
    }

    public void setPrikolicaB(List<PrikolicaBDTO> prikolicaB) {
        this.prikolicaB = prikolicaB;
    }

    public List<OkolnostiNezgodeDTO> getOkolnostiNezgodes() {
        return okolnostiNezgodes;
    }

    public void setOkolnostiNezgodes(List<OkolnostiNezgodeDTO> okolnostiNezgodes) {
        this.okolnostiNezgodes = okolnostiNezgodes;
    }

    public List<OkolnostiNezgodeBDTO> getOkolnostiNezgodesb() {
        return okolnostiNezgodesb;
    }

    public void setOkolnostiNezgodesb(List<OkolnostiNezgodeBDTO> okolnostiNezgodesb) {
        this.okolnostiNezgodesb = okolnostiNezgodesb;
    }

    public List<ZvanicniOrganiDTO> getZvanicniOrganis() {
        return zvanicniOrganis;
    }

    public void setZvanicniOrganis(List<ZvanicniOrganiDTO> zvanicniOrganis) {
        this.zvanicniOrganis = zvanicniOrganis;
    }

    @JsonIgnore
    List<ZvanicniOrganiDTO> zvanicniOrganis;


    public VozacNezgodaDTO getVozacNezgoda() {
        return vozacNezgoda;
    }

    public void setVozacNezgoda(VozacNezgodaDTO vozacNezgoda) {
        this.vozacNezgoda = vozacNezgoda;
    }

}
