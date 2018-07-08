package com.example.controller.rest;

import com.example.model.*;
import com.example.service.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "NEZGODA CONTROLLER",description = "kontorler za nezgode")
public class NezgodaController {

    @Autowired
    UserService userService;

    @Autowired
    VozacSvedokService vozacSvedokService;

    @Autowired
    PutnikSvedokService putnikSvedokService;

    @Autowired
    VozacNezgodaService vozacNezgodaService;

    @Autowired
    PovredjeniVaseVoziloService povredjeniVaseVoziloService;

    @Autowired
    PovredjeniDrugoVoziloService povredjeniDrugoVoziloService;

    @Autowired
    PovredjeniVanVozilaService povredjeniVanVozilaService;

    @Autowired
    EvropskiIzvestajIDokaziService evropskiIzvestajIDokaziService;


    @Autowired
    ZvanicniOrganiService zvanicniOrganiService;

    @Autowired
    OkolnostiNezgodeService okolnostiNezgodeService;


    @PostMapping(value = "/nezgodavozacsvedok")
    public ResponseEntity<RequestWrapper> nezgodaVozacSvedok(@RequestBody RequestWrapper requestWrapper){

        User u = userService.findUserByEmail(requestWrapper.getUser().getEmail());
        VozacSvedok vs = new VozacSvedok();
        vs.setIme(requestWrapper.getVozacSvedok().get(0).getIme());
        vs.setPrezime(requestWrapper.getVozacSvedok().get(0).getPrezime());
        vs.setAdresa(requestWrapper.getVozacSvedok().get(0).getAdresa());
        vs.setBrLicneKarte(requestWrapper.getVozacSvedok().get(0).getBrLicneKarte());
        vs.setTelefon(requestWrapper.getVozacSvedok().get(0).getTelefon());

        //  vs.setUser(u);
        vozacSvedokService.saveVozacSvedok(vs);

        return  new ResponseEntity<>(requestWrapper, HttpStatus.OK);

    }

    @PostMapping(value = "/nezgodaputniksvedok")
    public ResponseEntity<RequestWrapper> nezgodaPutnikSvedok(@RequestBody RequestWrapper requestWrapper){

        User u = userService.findUserByEmail(requestWrapper.getUser().getEmail());

        PutnikSvedok ps = new PutnikSvedok();
        ps.setIme(requestWrapper.getPutnikSvedok().get(0).getIme());
        ps.setPrezime(requestWrapper.getPutnikSvedok().get(0).getPrezime());
        ps.setAdresa(requestWrapper.getPutnikSvedok().get(0).getAdresa());
        ps.setBrLicneKarte(requestWrapper.getPutnikSvedok().get(0).getBrLicneKarte());
        ps.setTelefon(requestWrapper.getPutnikSvedok().get(0).getTelefon());
        //ps.setUser(u);

        putnikSvedokService.savePutnikSvedok(ps);
        return  new ResponseEntity<>(requestWrapper, HttpStatus.OK);
    }

    @PostMapping(value = "/vozacnezgoda")
    public ResponseEntity<RequestWrapper> vozacNezgoda(@RequestBody RequestWrapper requestWrapper){

        User u = userService.findUserByEmail(requestWrapper.getUser().getEmail());


        VozacNezgoda vn = new VozacNezgoda();
        System.out.println("PARAMETAR " + requestWrapper.getVozacNezgoda().get(0).getDatumNezgode());
        vn.setVremeNezgode(requestWrapper.getVozacNezgoda().get(0).getVremeNezgode());
        vn.setMestoNezgode(requestWrapper.getVozacNezgoda().get(0).getMestoNezgode());
        vn.setDatumNezgode(requestWrapper.getVozacNezgoda().get(0).getDatumNezgode());

        // vn.setUser(u);
        vozacNezgodaService.saveVozacNezgoda(vn);

        return  new ResponseEntity<>(requestWrapper, HttpStatus.OK);
    }


    @PostMapping(value = "/povredjenidrugovozilo")
    public ResponseEntity<RequestWrapper> povredjeniVaseVozilo(@RequestBody RequestWrapper requestWrapper){

        User u = userService.findUserByEmail(requestWrapper.getUser().getEmail());


        PovredjeniDrugoVozilo pdv = new PovredjeniDrugoVozilo();

        pdv.setIme(requestWrapper.getPovredjeniDrugoVozilo().get(0).getIme());
        pdv.setPrezime(requestWrapper.getPovredjeniDrugoVozilo().get(0).getPrezime());
        pdv.setAdresa(requestWrapper.getPovredjeniDrugoVozilo().get(0).getAdresa());
        pdv.setTelefon(requestWrapper.getPovredjeniDrugoVozilo().get(0).getTelefon());

        //  pdv.setUser(u);
        povredjeniDrugoVoziloService.savePovredjeniDrugoVozilo(pdv);

        return  new ResponseEntity<>(requestWrapper, HttpStatus.OK);
    }

    @PostMapping(value = "/povredjenivasevozilo")
    public ResponseEntity<RequestWrapper> povredjeniDrugoVozilo(@RequestBody RequestWrapper requestWrapper){

        User u = userService.findUserByEmail(requestWrapper.getUser().getEmail());


        PovredjeniVaseVozilo pvv = new PovredjeniVaseVozilo();
        pvv.setIme(requestWrapper.getPovredjeniVaseVozilo().get(0).getIme());
        pvv.setPrezime(requestWrapper.getPovredjeniVaseVozilo().get(0).getPrezime());
        pvv.setAdresa(requestWrapper.getPovredjeniVaseVozilo().get(0).getAdresa());
        pvv.setTelefon(requestWrapper.getPovredjeniVaseVozilo().get(0).getTelefon());

        // pvv.setUser(u);
        povredjeniVaseVoziloService.savePovredjeniVaseVozilo(pvv);

        return  new ResponseEntity<>(requestWrapper, HttpStatus.OK);
    }

    @PostMapping(value = "/povredjenivanvozila")
    public ResponseEntity<RequestWrapper> povredjeniVanVozila(@RequestBody RequestWrapper requestWrapper){

        User u = userService.findUserByEmail(requestWrapper.getUser().getEmail());


        PovredjeniVanVozila pvvo = new PovredjeniVanVozila();
        pvvo.setIme(requestWrapper.getPovredjeniVanVozila().get(0).getIme());
        pvvo.setPrezime(requestWrapper.getPovredjeniVanVozila().get(0).getPrezime());
        pvvo.setAdresa(requestWrapper.getPovredjeniVanVozila().get(0).getAdresa());
        pvvo.setTelefon(requestWrapper.getPovredjeniVanVozila().get(0).getTelefon());

        //   pvvo.setUser(u);
        povredjeniVanVozilaService.savePovredjeniVanVozila(pvvo);

        return  new ResponseEntity<>(requestWrapper, HttpStatus.OK);
    }

//    @PostMapping(value = "/evropskiizvestajidokazi")
//    public ResponseEntity<RequestWrapper> evropskiIzvestajIDokazi(@RequestBody RequestWrapper requestWrapper){
//
//        User u = userService.findUserByEmail(requestWrapper.getUser().getEmail());
//
//
//        EvropskiIzvestajIDokazi eiid = new EvropskiIzvestajIDokazi();
//        eiid.setOstecenjaDrugoVozilo(requestWrapper.getEvropskiIzvestajIDokazi().get(0).getOstecenjaDrugoVozilo());
//        eiid.setOstecenjeVaseVozilo(requestWrapper.getEvropskiIzvestajIDokazi().get(0).getOstecenjeVaseVozilo());
//        eiid.setPolisaOsiguranja(requestWrapper.getEvropskiIzvestajIDokazi().get(0).getPolisaOsiguranja());
//        eiid.setRegistarskaTablicaDrugoVozilo(requestWrapper.getEvropskiIzvestajIDokazi().get(0).getRegistarskaTablicaDrugoVozilo());
//        eiid.setVozackaDozvolaDrugogVozila(requestWrapper.getEvropskiIzvestajIDokazi().get(0).getVozackaDozvolaDrugogVozila());
//        eiid.setEvropskiIzvestaj(requestWrapper.getEvropskiIzvestajIDokazi().get(0).getEvropskiIzvestaj());
//        eiid.setUser(u);
//        evropskiIzvestajIDokaziService.saveEvropskiIzvestajIDokazi(eiid);
//
//        return  new ResponseEntity<>(requestWrapper, HttpStatus.OK);
//    }



    @PostMapping(value = "/zvanicniorgani")
    public ResponseEntity<RequestWrapper> zvanicniOrgani(@RequestBody RequestWrapper requestWrapper){

        User u = userService.findUserByEmail(requestWrapper.getUser().getEmail());


        ZvanicniOrgani zo = new ZvanicniOrgani();
        zo.setDaLiJeSacinjenZvanicniIzvestaj(requestWrapper.getZvanicniOrgani().get(0).getDaLiJeSacinjenZvanicniIzvestaj());
        zo.setOdKogaJeSacinjenZvanicniIzvestaj(requestWrapper.getZvanicniOrgani().get(0).getOdKogaJeSacinjenZvanicniIzvestaj());
        zo.setBrojZvanicnogIzvestaja(requestWrapper.getZvanicniOrgani().get(0).getBrojZvanicnogIzvestaja());
        zo.setDaLiJeVozacPodvrgnutTestiranjuAlko(requestWrapper.getZvanicniOrgani().get(0).getDaLiJeVozacPodvrgnutTestiranjuAlko());
        zo.setDaLiJeVozacPodvrgnutTestiranjuNarko(requestWrapper.getZvanicniOrgani().get(0).getDaLiJeVozacPodvrgnutTestiranjuNarko());
        zo.setDaLiJeVozacOdbioTest(requestWrapper.getZvanicniOrgani().get(0).getDaLiJeVozacOdbioTest());
        //zo.setUser(u);
        //zvanicniOrganiService.save(zo);

        return  new ResponseEntity<>(requestWrapper, HttpStatus.OK);
    }

    @PostMapping(value = "/okolnostinezgode")
    public ResponseEntity<RequestWrapper> okolnostiNezgode(@RequestBody RequestWrapper requestWrapper){

        User u = userService.findUserByEmail(requestWrapper.getUser().getEmail());


        OkolnostiNezgode on = new OkolnostiNezgode();

//        on.setParkiran(requestWrapper.getOkolnostiNezgode().get(0).getParkiran());
//        on.setZaustavljen(requestWrapper.getOkolnostiNezgode().get(0).getZaustavljen());
//        on.setNapustioParking(requestWrapper.getOkolnostiNezgode().get(0).getNapustioParking());
//        on.setOtvaraoVrata(requestWrapper.getOkolnostiNezgode().get(0).getOtvaraoVrata());
//        on.setNapustioPrivatniPosed(requestWrapper.getOkolnostiNezgode().get(0).getNapustioPrivatniPosed());
        //on.setPoceoDaSkreceNaParking(requestWrapper.getOkolnostiNezgode().get(0).getPoceoDaSkreceNaParking());
        on.setUpravoUlaziUKruzniTok(requestWrapper.getOkolnostiNezgode().get(0).getUpravoUlaziUKruzniTok());
        on.setProlaziKruzniTok(requestWrapper.getOkolnostiNezgode().get(0).getProlaziKruzniTok());
        on.setNaleteoNaZadnjiDeoVozila(requestWrapper.getOkolnostiNezgode().get(0).getNaleteoNaZadnjiDeoVozila());
        on.setVozioUistomSemeruAuDrugojTraci(requestWrapper.getOkolnostiNezgode().get(0).getVozioUistomSemeruAuDrugojTraci());
        on.setMeanjaoTraku(requestWrapper.getOkolnostiNezgode().get(0).getMeanjaoTraku());
        on.setPreticao(requestWrapper.getOkolnostiNezgode().get(0).getPreticao());
        on.setSkretaoUdesno(requestWrapper.getOkolnostiNezgode().get(0).getSkretaoUdesno());
        on.setSkretaoUlevo(requestWrapper.getOkolnostiNezgode().get(0).getSkretaoUlevo());
        on.setVozioUnazad(requestWrapper.getOkolnostiNezgode().get(0).getVozioUnazad());
        on.setPresaoUtrakuSuprotnogSmera(requestWrapper.getOkolnostiNezgode().get(0).getPresaoUtrakuSuprotnogSmera());
        on.setDolaziSaDesneStraneNaRaskrsnici(requestWrapper.getOkolnostiNezgode().get(0).getDolaziSaDesneStraneNaRaskrsnici());
        on.setNijePostovaoZnak(requestWrapper.getOkolnostiNezgode().get(0).getNijePostovaoZnak());
       // on.setNijePostovaoPrednost(requestWrapper.getOkolnostiNezgode().get(0).getNijePostovaoPrednost());
        //on.setNijePostovaoCrvenoSvetlo(requestWrapper.getOkolnostiNezgode().get(0).getNijePostovaoCrvenoSvetlo());
        //on.setUser(u);
        //  okolnostiNezgodeService.saveOkolnostiNezgode(on);

        return  new ResponseEntity<>(requestWrapper, HttpStatus.OK);
    }



}
