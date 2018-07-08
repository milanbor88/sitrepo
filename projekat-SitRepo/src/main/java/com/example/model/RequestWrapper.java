package com.example.model;

import java.util.List;

public class RequestWrapper {

    User user;
    List<Vozilo> vozilo;
    List<Prikolica> prikolica;
    List<Osiguranje> osiguranje;
    List<UgovaracOsiguranja> ugovaracOsiguranja;
    List<VozacSvedok> vozacSvedok;
    List<PutnikSvedok> putnikSvedok;
    List<VozacNezgoda> vozacNezgoda;
    List<PovredjeniVaseVozilo> povredjeniVaseVozilo;
    List<PovredjeniDrugoVozilo> povredjeniDrugoVozilo;
    List<PovredjeniVanVozila> povredjeniVanVozila;
    List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazi;
    List<ZvanicniOrgani> zvanicniOrgani;
    List<OkolnostiNezgode> okolnostiNezgode;
    List<Klijenti> klijenti;
    List<Ucesnik> ucesnik;

    public List<Ucesnik> getUcesnik() {
        return ucesnik;
    }

    public void setUcesnik(List<Ucesnik> ucesnik) {
        this.ucesnik = ucesnik;
    }

    public List<Klijenti> getKlijenti() {
        return klijenti;
    }

    public void setKlijenti(List<Klijenti> klijenti) {
        this.klijenti = klijenti;
    }


    public List<OkolnostiNezgode> getOkolnostiNezgode() {
        return okolnostiNezgode;
    }

    public void setOkolnostiNezgode(List<OkolnostiNezgode> okolnostiNezgode) {
        this.okolnostiNezgode = okolnostiNezgode;
    }

    public List<ZvanicniOrgani> getZvanicniOrgani() {
        return zvanicniOrgani;
    }

    public void setZvanicniOrgani(List<ZvanicniOrgani> zvanicniOrgani) {
        this.zvanicniOrgani = zvanicniOrgani;
    }


    public List<EvropskiIzvestajIDokazi> getEvropskiIzvestajIDokazi() {
        return evropskiIzvestajIDokazi;
    }

    public void setEvropskiIzvestajIDokazi(List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazi) {
        this.evropskiIzvestajIDokazi = evropskiIzvestajIDokazi;
    }

    public List<PovredjeniVanVozila> getPovredjeniVanVozila() {
        return povredjeniVanVozila;
    }

    public void setPovredjeniVanVozila(List<PovredjeniVanVozila> povredjeniVanVozila) {
        this.povredjeniVanVozila = povredjeniVanVozila;
    }


    public List<PovredjeniDrugoVozilo> getPovredjeniDrugoVozilo() {
        return povredjeniDrugoVozilo;
    }

    public void setPovredjeniDrugoVozilo(List<PovredjeniDrugoVozilo> povredjeniDrugoVozilo) {
        this.povredjeniDrugoVozilo = povredjeniDrugoVozilo;
    }

    public List<PovredjeniVaseVozilo> getPovredjeniVaseVozilo() {
        return povredjeniVaseVozilo;
    }

    public void setPovredjeniVaseVozilo(List<PovredjeniVaseVozilo> povredjeniVaseVozilo) {
        this.povredjeniVaseVozilo = povredjeniVaseVozilo;
    }

    public List<VozacNezgoda> getVozacNezgoda() {
        return vozacNezgoda;
    }

    public void setVozacNezgoda(List<VozacNezgoda> vozacNezgoda) {
        this.vozacNezgoda = vozacNezgoda;
    }

    public List<PutnikSvedok> getPutnikSvedok() {
        return putnikSvedok;
    }

    public void setPutnikSvedok(List<PutnikSvedok> putnikSvedok) {
        this.putnikSvedok = putnikSvedok;
    }

    public List<VozacSvedok> getVozacSvedok() {
        return vozacSvedok;
    }

    public void setVozacSvedok(List<VozacSvedok> vozacSvedok) {
        this.vozacSvedok = vozacSvedok;
    }

    public List<UgovaracOsiguranja> getUgovaracOsiguranja() {
        return ugovaracOsiguranja;
    }

    public void setUgovaracOsiguranja(List<UgovaracOsiguranja> ugovaracOsiguranja) {
        this.ugovaracOsiguranja = ugovaracOsiguranja;
    }

    public List<Osiguranje> getOsiguranje() {
        return osiguranje;
    }

    public void setOsiguranje(List<Osiguranje> osiguranje) {
        this.osiguranje = osiguranje;
    }

    public List<Prikolica> getPrikolica() {
        return prikolica;
    }

    public void setPrikolica(List<Prikolica> prikolica) {
        this.prikolica = prikolica;
    }

    public RequestWrapper() {
    }

    public RequestWrapper(User user, List<Vozilo> vozilo) {
        this.user = user;
        this.vozilo = vozilo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Vozilo> getVozilo() {
        return vozilo;
    }

    public void setVozilo(List<Vozilo> vozilo) {
        this.vozilo = vozilo;
    }
}
