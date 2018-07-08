package com.example.controller;

import com.example.dto.*;
import com.example.mapper.*;
import com.example.model.*;
import com.example.service.*;
import com.example.util.PDFView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class IzvNezgodaManjaController {

    @Autowired
    private UserService userService;

    @Autowired
    VozacSvedokService vozacSvedokService;

    @Autowired
    VozacNezgodaService vozacNezgodaService;

    @Autowired
    EvropskiIzvestajIDokaziService evropskiIzvestajIDokaziService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    VozacNezIzvService vozacNezIzvService;

    @Autowired
    VozacNezgodaVecaMapper vozacNezgodaVecaMapper;

    @Autowired
    VoziloService voziloService;

    @Autowired
    VoziloMapper voziloMapper;

    @Autowired
    PrikolicaService prikolicaService;

    @Autowired
    PrikolicaMapper prikolicaMapper;

    @Autowired
    OsiguranjeService osiguranjeService;

    @Autowired
    OsiguranjeMapper osiguranjeMapper;

    @Autowired
    UgovaracOsiguranjaService ugovaracOsiguranjaService;

    @Autowired
    UgovaracOsiguranjaMapper ugovaracOsiguranjaMapper;

    @Autowired
    VozacSvedokMapper vozacSvedokMapper;

    @Autowired
    VozacNezgodaManjaMapper vozacNezgodaManjaMapper;

    @Autowired
    VozacNezgodaMapper vozacNezgodaMapper;

    @Autowired
    PovredjeniVaseVoziloService povredjeniVaseVoziloService;

    @Autowired
    PovredjeniVaseVoziloMapper povredjeniVaseVoziloMapper;

    @Autowired
    PovredjeniVanVozilaService povredjeniVanVozilaService;

    @Autowired
    PovredjeniVanVozilaMapper povredjeniVanVozilaMapper;

    @Autowired
    PovredjeniDrugoVoziloService povredjeniDrugoVoziloService;

    @Autowired
    PovredjeniDrugoVoziloMapper povredjeniDrugoVoziloMapper;

    @Autowired
    EvropskiIzvestajIDokaziMapper evropskiIzvestajIDokaziMapper;


    @RequestMapping(value = "/user/manjaSteta", method = RequestMethod.GET)
    public ModelAndView manjaSteta() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO =userMapper.convertToDto(user);
        VoziloDTO voziloDTO = null;
        PrikolicaDTO prikolicaDTO = null;
        OsiguranjeDTO osiguranjeDTO = null;
        UgovaracOsiguranjaDTO ugovaracOsiguranjaDTO = null;


        List<VozacNezgodaManja> listaIzvestajaManjaVozac = vozacNezIzvService.findVozacNezgodaManjaByUser(user);
        List<VozacNezgodaManjaDTO> vozacNezgodaManjaVozacDTOS = vozacNezgodaManjaMapper.convertListToDto(listaIzvestajaManjaVozac);


        Osiguranje o = osiguranjeService.findOsiguranjeByUser(user);
        if (o != null) {
            osiguranjeDTO = osiguranjeMapper.convertToDto(o);
        } else {
            osiguranjeDTO = new OsiguranjeDTO();
        }

        Vozilo v = voziloService.findVoziloByUser(user);
        if (v != null) {
            voziloDTO = voziloMapper.convertToDto(v);
        } else {
            voziloDTO = new VoziloDTO();
        }

        Prikolica p = prikolicaService.findPrikolicaByUser(user);
        if (p != null) {
            prikolicaDTO = prikolicaMapper.convertToDto(p);
        } else {
            prikolicaDTO = new PrikolicaDTO();
        }

        UgovaracOsiguranja uo = ugovaracOsiguranjaService.findUgovaracOsiguranjaByUser(user);
        if (uo != null) {
            ugovaracOsiguranjaDTO = ugovaracOsiguranjaMapper.convertToDto(uo);
        } else {
            ugovaracOsiguranjaDTO = new UgovaracOsiguranjaDTO();
        }

//      List<PovredjeniDrugoVozilo> povredjeniDrugoVoziloMS = user.getPovredjeniDrugoVozilo().stream().filter(pdv -> "2".equals(pdv.getStatus())).collect(Collectors.toList());

        modelAndView.addObject("userName",user.getName() + " " + user.getLastName());
        modelAndView.addObject("user", userDTO);
        modelAndView.addObject("osiguranjeDTO", osiguranjeDTO);
        modelAndView.addObject("voziloDTO", voziloDTO);
        modelAndView.addObject("prikolicaDTO",prikolicaDTO);
        modelAndView.addObject("ugovaracOsiguranjaDTO", ugovaracOsiguranjaDTO);
        modelAndView.addObject("listaIzvestajaManjaVozac", vozacNezgodaManjaVozacDTOS);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("user/manjaSteta");
        return modelAndView;

    }

    @RequestMapping(value = "/user/detaljiIzvestajaManjaVozac/{id}", method = RequestMethod.GET)
    public ModelAndView detaljiIzvestajaManjaVozac(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        VozacNezgodaManja vnm = vozacNezIzvService.findManjaById(id);
        boolean poslat = vnm.isPoslat();

        List<PovredjeniVaseVozilo> povredjeniVaseVoziloMS = povredjeniVaseVoziloService.findPvvByVozacNezgodaIzvestajId(id);
        List<PovredjeniVanVozila> povredjeniVanVozilaMS = povredjeniVanVozilaService.findPvanByVozacNezgodaIzvestajId(id);
        List<PovredjeniDrugoVozilo> povredjeniDrugoVoziloMS = povredjeniDrugoVoziloService.findPdvByVozacNezgodaIzvestajId(id);
        List<VozacSvedok> vozacSvedoks = vozacSvedokService.findAllByVozacNezgodaIzvestajId(id);

        List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazisSopstveno = evropskiIzvestajIDokaziService.findEvrDokaziByStatusAndVozacNezgodaManjaId("1",id);
        List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazisTudje = evropskiIzvestajIDokaziService.findEvrDokaziByStatusAndVozacNezgodaManjaId("2",id);
        List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazisEvrIzv = evropskiIzvestajIDokaziService.findEvrDokaziByStatusAndVozacNezgodaManjaId("3",id);

        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.addObject("povredjeniVaseVoziloMS",povredjeniVaseVoziloMS);
        modelAndView.addObject("povredjeniVanVozilaMS", povredjeniVanVozilaMS);
        modelAndView.addObject("povredjeniDrugoVoziloMS", povredjeniDrugoVoziloMS);
        modelAndView.addObject("poslat", poslat);
        modelAndView.addObject("evropskiIzvestajIDokazisSopstveno",evropskiIzvestajIDokazisSopstveno);
        modelAndView.addObject("evropskiIzvestajIDokazisTudje",evropskiIzvestajIDokazisTudje);
        modelAndView.addObject("evropskiIzvestajIDokazisEvrIzv",evropskiIzvestajIDokazisEvrIzv);
        modelAndView.addObject("vozacSvedoks", vozacSvedoks);
        modelAndView.setViewName("user/detaljiIzvestajaManjaVozac");
        modelAndView.addObject("idIzvm", id);

        return modelAndView;
    }

    @RequestMapping(value = "/user/detaljiIzvestajaManjaVozacSO/{idIzvm}", method = RequestMethod.GET)
    public ModelAndView detaljiIzvestajaManjaVozacSO(@PathVariable Long idIzvm) {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        VozacNezgodaManja vnm = vozacNezIzvService.findManjaById(idIzvm);
        boolean poslat = vnm.isPoslat();

        List<PovredjeniVaseVozilo> povredjeniVaseVoziloMS = povredjeniVaseVoziloService.findPvvByVozacNezgodaIzvestajId(idIzvm);
        List<PovredjeniVanVozila> povredjeniVanVozilaMS = povredjeniVanVozilaService.findPvanByVozacNezgodaIzvestajId(idIzvm);
        List<PovredjeniDrugoVozilo> povredjeniDrugoVoziloMS = povredjeniDrugoVoziloService.findPdvByVozacNezgodaIzvestajId(idIzvm);
        List<VozacSvedok> vozacSvedoks = vozacSvedokService.findAllByVozacNezgodaIzvestajId(idIzvm);


        List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazisSopstveno = evropskiIzvestajIDokaziService.findEvrDokaziByStatusAndVozacNezgodaManjaId("1",idIzvm);
        List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazisTudje = evropskiIzvestajIDokaziService.findEvrDokaziByStatusAndVozacNezgodaManjaId("2",idIzvm);
        List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazisEvrIzv = evropskiIzvestajIDokaziService.findEvrDokaziByStatusAndVozacNezgodaManjaId("3",idIzvm);

        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.addObject("povredjeniVaseVoziloMS",povredjeniVaseVoziloMS);
        modelAndView.addObject("povredjeniVanVozilaMS", povredjeniVanVozilaMS);
        modelAndView.addObject("poslat", poslat);
        modelAndView.addObject("povredjeniDrugoVoziloMS", povredjeniDrugoVoziloMS);
        modelAndView.addObject("evropskiIzvestajIDokazisSopstveno",evropskiIzvestajIDokazisSopstveno);
        modelAndView.addObject("evropskiIzvestajIDokazisTudje",evropskiIzvestajIDokazisTudje);
        modelAndView.addObject("evropskiIzvestajIDokazisEvrIzv",evropskiIzvestajIDokazisEvrIzv);
        modelAndView.addObject("vozacSvedoks", vozacSvedoks);
        modelAndView.setViewName("user/detaljiIzvestajaManjaVozacSO");
        modelAndView.addObject("idIzvm", idIzvm);

        return modelAndView;
    }


    @RequestMapping(value = "/user/detaljiIzvestajaManjaVozacTV/{idIzvm}", method = RequestMethod.GET)
    public ModelAndView detaljiIzvestajaManjaVozacTV(@PathVariable Long idIzvm) {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        VozacNezgodaManja vnm = vozacNezIzvService.findManjaById(idIzvm);
        boolean poslat = vnm.isPoslat();

        List<PovredjeniVaseVozilo> povredjeniVaseVoziloMS = povredjeniVaseVoziloService.findPvvByVozacNezgodaIzvestajId(idIzvm);
        List<PovredjeniVanVozila> povredjeniVanVozilaMS = povredjeniVanVozilaService.findPvanByVozacNezgodaIzvestajId(idIzvm);
        List<PovredjeniDrugoVozilo> povredjeniDrugoVoziloMS = povredjeniDrugoVoziloService.findPdvByVozacNezgodaIzvestajId(idIzvm);
        List<VozacSvedok> vozacSvedoks = vozacSvedokService.findAllByVozacNezgodaIzvestajId(idIzvm);


        List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazisSopstveno = evropskiIzvestajIDokaziService.findEvrDokaziByStatusAndVozacNezgodaManjaId("1",idIzvm);
        List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazisTudje = evropskiIzvestajIDokaziService.findEvrDokaziByStatusAndVozacNezgodaManjaId("2",idIzvm);
        List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazisEvrIzv = evropskiIzvestajIDokaziService.findEvrDokaziByStatusAndVozacNezgodaManjaId("3",idIzvm);

        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.addObject("povredjeniVaseVoziloMS",povredjeniVaseVoziloMS);
        modelAndView.addObject("povredjeniVanVozilaMS", povredjeniVanVozilaMS);
        modelAndView.addObject("povredjeniDrugoVoziloMS", povredjeniDrugoVoziloMS);
        modelAndView.addObject("poslat", poslat);
        modelAndView.addObject("evropskiIzvestajIDokazisSopstveno",evropskiIzvestajIDokazisSopstveno);
        modelAndView.addObject("evropskiIzvestajIDokazisTudje",evropskiIzvestajIDokazisTudje);
        modelAndView.addObject("evropskiIzvestajIDokazisEvrIzv",evropskiIzvestajIDokazisEvrIzv);
        modelAndView.addObject("vozacSvedoks", vozacSvedoks);
        modelAndView.setViewName("user/detaljiIzvestajaManjaVozacTV");
        modelAndView.addObject("idIzvm", idIzvm);

        return modelAndView;
    }

    @RequestMapping(value = "/user/detaljiIzvestajaManjaVozacEI/{idIzvm}", method = RequestMethod.GET)
    public ModelAndView detaljiIzvestajaManjaVozacEI(@PathVariable Long idIzvm) {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        VozacNezgodaManja vnm = vozacNezIzvService.findManjaById(idIzvm);
        boolean poslat = vnm.isPoslat();

        List<PovredjeniVaseVozilo> povredjeniVaseVoziloMS = povredjeniVaseVoziloService.findPvvByVozacNezgodaIzvestajId(idIzvm);
        List<PovredjeniVanVozila> povredjeniVanVozilaMS = povredjeniVanVozilaService.findPvanByVozacNezgodaIzvestajId(idIzvm);
        List<PovredjeniDrugoVozilo> povredjeniDrugoVoziloMS = povredjeniDrugoVoziloService.findPdvByVozacNezgodaIzvestajId(idIzvm);
        List<VozacSvedok> vozacSvedoks = vozacSvedokService.findAllByVozacNezgodaIzvestajId(idIzvm);


        List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazisSopstveno = evropskiIzvestajIDokaziService.findEvrDokaziByStatusAndVozacNezgodaManjaId("1",idIzvm);
        List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazisTudje = evropskiIzvestajIDokaziService.findEvrDokaziByStatusAndVozacNezgodaManjaId("2",idIzvm);
        List<EvropskiIzvestajIDokazi> evropskiIzvestajIDokazisEvrIzv = evropskiIzvestajIDokaziService.findEvrDokaziByStatusAndVozacNezgodaManjaId("3",idIzvm);

        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.addObject("povredjeniVaseVoziloMS",povredjeniVaseVoziloMS);
        modelAndView.addObject("povredjeniVanVozilaMS", povredjeniVanVozilaMS);
        modelAndView.addObject("poslat", poslat);
        modelAndView.addObject("povredjeniDrugoVoziloMS", povredjeniDrugoVoziloMS);
        modelAndView.addObject("evropskiIzvestajIDokazisSopstveno",evropskiIzvestajIDokazisSopstveno);
        modelAndView.addObject("evropskiIzvestajIDokazisTudje",evropskiIzvestajIDokazisTudje);
        modelAndView.addObject("evropskiIzvestajIDokazisEvrIzv",evropskiIzvestajIDokazisEvrIzv);
        modelAndView.addObject("vozacSvedoks", vozacSvedoks);
        modelAndView.setViewName("user/detaljiIzvestajaManjaVozacEI");
        modelAndView.addObject("idIzvm", idIzvm);

        return modelAndView;
    }


    @RequestMapping(value = "/user/dodajSvedokaManja/{idIzvm}", method = RequestMethod.GET)
    public ModelAndView dodajSvedokaManja(@PathVariable Long idIzvm) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        VozacSvedok vs = new VozacSvedok();
        VozacSvedokDTO vozacSvedokDTO = vozacSvedokMapper.convertToDto(vs);
        modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("vozacSvedokDTO", vozacSvedokDTO);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.addObject("idIzvm", idIzvm);
        modelAndView.setViewName("user/dodajSvedokaManja");
        return modelAndView;

    }

    @RequestMapping(value = "/user/snimiSvedokaManja/{idIzvm}", method = RequestMethod.POST)
    public ModelAndView snimiPrikolicuB(@Valid @ModelAttribute VozacSvedokDTO vozacSvedokDTO,
                                        BindingResult result,
                                        RedirectAttributes redirectAttributes,
                                        @PathVariable Long idIzvm,
                                        @RequestParam MultipartFile[] fileUpload) throws IOException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        if(!result.hasErrors()){
            ModelAndView m = new ModelAndView("redirect:/user/dodajSvedokaManja/" +idIzvm);
            redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
            redirectAttributes.addFlashAttribute("successMessage", "Svedok uspešno sačuvan možete uneti još svedoka");
            if (fileUpload != null && fileUpload.length > 0) {
                for (MultipartFile aFile : fileUpload) {
                    if (aFile.isEmpty()) break;
                    vozacSvedokDTO.setFotografijaLicneKarte(aFile.getBytes());
                }
            }
            VozacSvedok vozacSvedok = vozacSvedokMapper.convertToEntity(vozacSvedokDTO);
            vozacSvedok.setVozacNezgodaIzvestaj(vozacNezIzvService.findManjaById(idIzvm));
            vozacSvedokService.saveVozacSvedok(vozacSvedok);
            return m;
        }

        if(result.hasErrors()) {
            ModelAndView m = new ModelAndView("redirect:/user/dodajSvedokaManja" + idIzvm);
            m.addObject("userName", userDTO.getName() + " " + userDTO.getLastName());
            m.addObject("vozacSvedokDTO", vozacSvedokDTO);
            m.setViewName("user/dodajSvedokaManja");
            m.addObject("successMessage", "Imate gresku");
            return m;
        }
        return new ModelAndView("redirect:/user/dodajSvedokaManja/" +idIzvm);
    }


    @RequestMapping(value = "/user/obrisiVozacaSvedokaManja/{id}/{idIzvm}", method = RequestMethod.GET)
    public ModelAndView obrisiVozacaSvedoka(@PathVariable Long id,
                                            @PathVariable Long idIzvm,
                                            RedirectAttributes redirectAttributes){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        vozacSvedokService.deleteVozacSvedok(id);

        ModelAndView m = new ModelAndView("redirect:/user/detaljiIzvestajaManjaVozac/" + idIzvm);
        redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
        redirectAttributes.addFlashAttribute("successMessage", "Svedok uspesno obrisan");
        return m;

    }

    @RequestMapping(value = "/user/dokazi/{slika}/{idIzvm}", method = RequestMethod.POST)
    public ModelAndView ubacisliku(@Valid @ModelAttribute EvropskiIzvestajIDokazi evropskiIzvestajIDokazi,
                                   BindingResult result,
                                   RedirectAttributes redirectAttributes,
                                   @PathVariable String slika,
                                   @PathVariable Long idIzvm,
                                   @RequestParam MultipartFile[] fileUpload) throws IOException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        if(!result.hasErrors()){
            ModelAndView mv = new ModelAndView("redirect:/user/detaljiIzvestajaManjaVozac/" +idIzvm);

            if(slika.equals("sopstveno")){
                ModelAndView m = new ModelAndView("redirect:/user/detaljiIzvestajaManjaVozacSO/" +idIzvm);
                redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
                redirectAttributes.addFlashAttribute("successMessage", "Slika uspešno sačuvana");

                if (fileUpload != null && fileUpload.length > 0) {
                    for (MultipartFile aFile : fileUpload) {
                        if(aFile.getSize() == 0){
                            redirectAttributes.addFlashAttribute("successMessage", "Ubacite sliku sopstvenog vozila!");
                            return m;
                        }
                        if (aFile.isEmpty()) break;
                        evropskiIzvestajIDokazi.setSlike(aFile.getBytes());
                    }
                    evropskiIzvestajIDokazi.setStatus("1");
                    evropskiIzvestajIDokazi.setVozacNezgodaManja(vozacNezIzvService.findManjaById(idIzvm));
                    evropskiIzvestajIDokaziService.saveEvropskiIzvestajIDokazi(evropskiIzvestajIDokazi);
                }

                return m;
            } else if(slika.equals("tudje")){
                ModelAndView m = new ModelAndView("redirect:/user/detaljiIzvestajaManjaVozacTV/" +idIzvm);
                redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
                redirectAttributes.addFlashAttribute("successMessage", "Slika uspesno sačuvan");

                if (fileUpload != null && fileUpload.length > 0) {
                    for (MultipartFile aFile : fileUpload) {
                        if(aFile.getSize() == 0) {
                            redirectAttributes.addFlashAttribute("successMessage", "Ubacite sliku tudjeg vozila!");
                            return m;
                        }
                        if (aFile.isEmpty()) break;
                        evropskiIzvestajIDokazi.setSlike(aFile.getBytes());
                    }
                    evropskiIzvestajIDokazi.setStatus("2");
                    evropskiIzvestajIDokazi.setVozacNezgodaManja(vozacNezIzvService.findManjaById(idIzvm));
                    evropskiIzvestajIDokaziService.saveEvropskiIzvestajIDokazi(evropskiIzvestajIDokazi);
                }

                return m;
            } else if(slika.equals("evropski")){
                ModelAndView m = new ModelAndView("redirect:/user/detaljiIzvestajaManjaVozacEI/" +idIzvm);
                redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
                redirectAttributes.addFlashAttribute("successMessage", "Slika uspesno sačuvan");
                if (fileUpload != null && fileUpload.length > 0) {
                    for (MultipartFile aFile : fileUpload) {
                        if(aFile.getSize() == 0){
                            redirectAttributes.addFlashAttribute("successMessage", "Ubacite sliku evropskog izvestaja!");
                            return m;
                        }
                        if (aFile.isEmpty()) break;
                        evropskiIzvestajIDokazi.setSlike(aFile.getBytes());
                    }
                    evropskiIzvestajIDokazi.setStatus("3");
                    evropskiIzvestajIDokazi.setVozacNezgodaManja(vozacNezIzvService.findManjaById(idIzvm));
                    evropskiIzvestajIDokaziService.saveEvropskiIzvestajIDokazi(evropskiIzvestajIDokazi);
                }else
                    redirectAttributes.addFlashAttribute("successMessage", "Imate gresku");
                return m;
            } else


            redirectAttributes.addFlashAttribute("successMessage","Neuspesno snimanje slike");
            return mv;

        }
        if(result.hasErrors()) {

            ModelAndView m = new ModelAndView("redirect:/user/detaljiIzvestajaManjaVozac/" +idIzvm);
            m.addObject("userName", user.getName() + " " + user.getLastName());
            m.setViewName("user/detaljiIzvestajaManjaVozac");
            m.addObject("successMessage", "Imate gresku");
            return m;
        }
        return new ModelAndView("redirect:/user/detaljiIzvestajaManjaVozac/" +idIzvm);
    }


    @RequestMapping(value = "/user/nazadManja", method = RequestMethod.GET)
    public ModelAndView nazadManja(RedirectAttributes redirectAttributes){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        ModelAndView m = new ModelAndView("redirect:/user/manjaSteta");
        redirectAttributes.addFlashAttribute("userName",user.getName() + " " + user.getLastName());
        return m;

    }

    @RequestMapping(value = "/user/nazadDetaljiIzvestajaManjaVozac/{idIzvm}", method = RequestMethod.GET)
    public ModelAndView unazadDetaljiIzvestajaVecaVozac(@PathVariable Long idIzvm, RedirectAttributes redirectAttributes){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        ModelAndView m = new ModelAndView("redirect:/user/detaljiIzvestajaManjaVozac/"+idIzvm);
        redirectAttributes.addFlashAttribute("userName",user.getName() + " " + user.getLastName());
        redirectAttributes.addFlashAttribute("idIzvm", idIzvm);
        return m;

    }

    @RequestMapping(value = "/user/imageEvropskiIzvestajIDokazi/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE} )
    public ResponseEntity<byte[]> getImageEvropskiIzvestajIDokazi(@PathVariable Long id) throws IOException {
        EvropskiIzvestajIDokazi evropskiIzvestajIDokazi = evropskiIzvestajIDokaziService.findEvropskiIzvestajIDokazikById(id);
        byte[] imageContent = evropskiIzvestajIDokazi.getSlike();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

}
