package com.example.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "okolnosti_nezgode")
public class OkolnostiNezgode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String materijalnaSteta;
    String mestoInicijalnogUdara;
    String vidljivaOstecenja;
    String parkiranZaustavljen;

    String napustioParkingOtvaraoVrata;

    String parkirao;

    String napustioParkingPrivatniposedPut;

    String poceodaSkreceParkingPrivatniposedPut;

    String upravoUlaziUKruzniTok;

    String prolaziKruzniTok;
    String naleteoNaZadnjiDeoVozila;
    String vozioUistomSemeruAuDrugojTraci;
    String meanjaoTraku;
    String preticao;
    String skretaoUdesno;
    String skretaoUlevo;
    String vozioUnazad;
    String presaoUtrakuSuprotnogSmera;
    String dolaziSaDesneStraneNaRaskrsnici;
    String nijePostovaoZnak;
    String vlastiteNapomene;

    @Version
    private long version;

    @ManyToOne(fetch = FetchType.LAZY)
    private VozacNezgodaManja vozacNezgodaManja;


    public String getMaterijalnaSteta() {
        return materijalnaSteta;
    }

    public void setMaterijalnaSteta(String materijalnaSteta) {
        this.materijalnaSteta = materijalnaSteta;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public String getVlastiteNapomene() {
        return vlastiteNapomene;
    }

    public void setVlastiteNapomene(String vlastiteNapomene) {
        this.vlastiteNapomene = vlastiteNapomene;
    }

    public VozacNezgodaManja getVozacNezgodaManja() {
        return vozacNezgodaManja;
    }

    public void setVozacNezgodaManja(VozacNezgodaManja vozacNezgodaManja) {
        this.vozacNezgodaManja = vozacNezgodaManja;
    }

    public OkolnostiNezgode() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getMestoInicijalnogUdara() {
        return mestoInicijalnogUdara;
    }

    public void setMestoInicijalnogUdara(String mestoInicijalnogUdara) {
        this.mestoInicijalnogUdara = mestoInicijalnogUdara;
    }

    public String getVidljivaOstecenja() {
        return vidljivaOstecenja;
    }

    public void setVidljivaOstecenja(String vidljivaOstecenja) {
        this.vidljivaOstecenja = vidljivaOstecenja;
    }

    public String getParkiranZaustavljen() {
        return parkiranZaustavljen;
    }

    public void setParkiranZaustavljen(String parkiranZaustavljen) {
        this.parkiranZaustavljen = parkiranZaustavljen;
    }

    public String getNapustioParkingOtvaraoVrata() {
        return napustioParkingOtvaraoVrata;
    }

    public void setNapustioParkingOtvaraoVrata(String napustioParkingOtvaraoVrata) {
        this.napustioParkingOtvaraoVrata = napustioParkingOtvaraoVrata;
    }

    public String getParkirao() {
        return parkirao;
    }

    public void setParkirao(String parkirao) {
        this.parkirao = parkirao;
    }

    public String getNapustioParkingPrivatniposedPut() {
        return napustioParkingPrivatniposedPut;
    }

    public void setNapustioParkingPrivatniposedPut(String napustioParkingPrivatniposedPut) {
        this.napustioParkingPrivatniposedPut = napustioParkingPrivatniposedPut;
    }

    public String getPoceodaSkreceParkingPrivatniposedPut() {
        return poceodaSkreceParkingPrivatniposedPut;
    }

    public void setPoceodaSkreceParkingPrivatniposedPut(String poceodaSkreceParkingPrivatniposedPut) {
        this.poceodaSkreceParkingPrivatniposedPut = poceodaSkreceParkingPrivatniposedPut;
    }

    public String getUpravoUlaziUKruzniTok() {
        return upravoUlaziUKruzniTok;
    }

    public void setUpravoUlaziUKruzniTok(String upravoUlaziUKruzniTok) {
        this.upravoUlaziUKruzniTok = upravoUlaziUKruzniTok;
    }

    public String getProlaziKruzniTok() {
        return prolaziKruzniTok;
    }

    public void setProlaziKruzniTok(String prolaziKruzniTok) {
        this.prolaziKruzniTok = prolaziKruzniTok;
    }

    public String getNaleteoNaZadnjiDeoVozila() {
        return naleteoNaZadnjiDeoVozila;
    }

    public void setNaleteoNaZadnjiDeoVozila(String naleteoNaZadnjiDeoVozila) {
        this.naleteoNaZadnjiDeoVozila = naleteoNaZadnjiDeoVozila;
    }

    public String getVozioUistomSemeruAuDrugojTraci() {
        return vozioUistomSemeruAuDrugojTraci;
    }

    public void setVozioUistomSemeruAuDrugojTraci(String vozioUistomSemeruAuDrugojTraci) {
        this.vozioUistomSemeruAuDrugojTraci = vozioUistomSemeruAuDrugojTraci;
    }

    public String getMeanjaoTraku() {
        return meanjaoTraku;
    }

    public void setMeanjaoTraku(String meanjaoTraku) {
        this.meanjaoTraku = meanjaoTraku;
    }

    public String getPreticao() {
        return preticao;
    }

    public void setPreticao(String preticao) {
        this.preticao = preticao;
    }

    public String getSkretaoUdesno() {
        return skretaoUdesno;
    }

    public void setSkretaoUdesno(String skretaoUdesno) {
        this.skretaoUdesno = skretaoUdesno;
    }

    public String getSkretaoUlevo() {
        return skretaoUlevo;
    }

    public void setSkretaoUlevo(String skretaoUlevo) {
        this.skretaoUlevo = skretaoUlevo;
    }

    public String getVozioUnazad() {
        return vozioUnazad;
    }

    public void setVozioUnazad(String vozioUnazad) {
        this.vozioUnazad = vozioUnazad;
    }

    public String getPresaoUtrakuSuprotnogSmera() {
        return presaoUtrakuSuprotnogSmera;
    }

    public void setPresaoUtrakuSuprotnogSmera(String presaoUtrakuSuprotnogSmera) {
        this.presaoUtrakuSuprotnogSmera = presaoUtrakuSuprotnogSmera;
    }

    public String getDolaziSaDesneStraneNaRaskrsnici() {
        return dolaziSaDesneStraneNaRaskrsnici;
    }

    public void setDolaziSaDesneStraneNaRaskrsnici(String dolaziSaDesneStraneNaRaskrsnici) {
        this.dolaziSaDesneStraneNaRaskrsnici = dolaziSaDesneStraneNaRaskrsnici;
    }

    public String getNijePostovaoZnak() {
        return nijePostovaoZnak;
    }

    public void setNijePostovaoZnak(String nijePostovaoZnak) {
        this.nijePostovaoZnak = nijePostovaoZnak;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        OkolnostiNezgode that = (OkolnostiNezgode) o;

        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return 31;

    }
}
