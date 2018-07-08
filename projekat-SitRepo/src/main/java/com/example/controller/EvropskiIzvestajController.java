package com.example.controller;

import com.example.dto.*;
import com.example.mapper.*;
import com.example.model.*;
import com.example.service.*;
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

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.List;

@Controller(value = "izvestaj")
public class EvropskiIzvestajController {

    @Autowired
    private UserService userService;

    @Autowired
    DrzavaService drzavaService;

    @Autowired
    UcesnikService ucesnikService;

    @Autowired
    PrikolicaBService prikolicaBService;

    @Autowired
    OsiguranjeBService osiguranjeBService;

    @Autowired
    KlijentiService klijentiService;

    @Autowired
    VozacNezgodaService vozacNezgodaService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PrikolicaBMapper prikolicaBMapper;

    @Autowired
    DrzaveMapper drzaveMapper;

    @Autowired
    OsiguranjeBMapper osiguranjeBMapper;

    @Autowired
    KlijentiMapper klijentiMapper;

    @Autowired
    UcesnikMapper ucesnikMapper;

    @Autowired
    VozacNezIzvService vozacNezIzvService;

    @Autowired
    UgovaracOsiguranjaBMapper ugovaracOsiguranjaBMapper;

    @Autowired
    UgovaracOsiguranjaBService ugovaracOsiguranjaBService;

    @Autowired
    VozacNezgodaMapper vozacNezgodaMapper;

    @Autowired
    VoziloBMapper voziloBMapper;

    @Autowired
    VoziloBService voziloBService;

    @RequestMapping(value = "/user/vremeNezgodeB/{idIzvm}", method = RequestMethod.GET)
    public ModelAndView vremeNezgodeB(@PathVariable Long idIzvm) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);


        VozacNezgodaManja vnm = vozacNezIzvService.findManjaById(idIzvm);

        boolean poslat = vnm.isPoslat();

        VozacNezgoda vn = vnm.getVozacNezgoda();
        VozacNezgodaDTO vozacNezgodaDTO = vozacNezgodaMapper.convertToDto(vn);

        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("vozacNezgodaDTO",vozacNezgodaDTO);
        modelAndView.addObject("poslat", poslat);
        modelAndView.addObject("idIzvm",idIzvm);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("user/vremeNezgodeB");
        return modelAndView;

    }


    @RequestMapping(value = "/user/dodajUcesnikB/{idIzvm}", method = RequestMethod.GET)
    public ModelAndView ucesnikB(@PathVariable Long idIzvm) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);
        UcesnikDTO ucesnikDTO = null;

        VozacNezgodaManja manja = vozacNezIzvService.findManjaById(idIzvm);
        Ucesnik ucesnik = ucesnikService.findByVozacNezgodaManja(manja);
        if (ucesnik != null) {
            System.out.println("ENTITET " +ucesnik.getVozackaDozvolaVaziDo());
            ucesnikDTO = ucesnikMapper.convertToDto(ucesnik);
            System.out.println("sit 1 " + ucesnikDTO.getVozackaDozvolaVaziDo());
        } else {
            ucesnikDTO = new UcesnikDTO();
            System.out.println("sit 2 " + ucesnikDTO.getVozackaDozvolaVaziDo());
        }

        boolean poslat = manja.isPoslat();

        List<Drzave> listaDrzava = drzavaService.findAll();
        List<DrzaveDTO> drzaveDTOS = drzaveMapper.convertListToDto(listaDrzava);

        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("ucesnikDTO", ucesnikDTO);
        modelAndView.addObject("drzaveDTOS",drzaveDTOS);
        modelAndView.addObject("poslat", poslat);
        modelAndView.addObject("idIzvm",idIzvm);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("user/ucesnikB");
        return modelAndView;

    }

    @RequestMapping(value = "/user/snimiUcesnikB/{idIzvm}", method = RequestMethod.POST)
    public ModelAndView snimiPutnikSvedok(@Valid @ModelAttribute UcesnikDTO ucesnikDTO,
                                          BindingResult result,
                                          RedirectAttributes redirectAttributes,
                                          @PathVariable Long idIzvm,
                                          @RequestParam MultipartFile[] fileUpload) throws IOException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        if(!result.hasErrors()){
            ModelAndView m = new ModelAndView("redirect:/user/dodajUcesnikB/" +idIzvm);
            redirectAttributes.addFlashAttribute("userName",ucesnikDTO.getName() + " " + userDTO.getLastName());
            redirectAttributes.addFlashAttribute("successMessage", "Učesnik uspešno sačuvan!");

            Ucesnik u = ucesnikService.findUcesnikByVozacNezgodaManjaId(idIzvm);
            if(u!=null){
                ucesnikDTO.setSlikaUcesnik(u.getSlikaUcesnik());
            }
            if (fileUpload != null) {
                for (MultipartFile aFile : fileUpload) {
                    if (aFile.isEmpty()) break;
                    ucesnikDTO.setSlikaUcesnik(aFile.getBytes());
                }
            }

            Ucesnik ucesnik = ucesnikMapper.convertToEntity(ucesnikDTO);
            ucesnik.setVozacNezgodaManja(vozacNezIzvService.findManjaById(idIzvm));
            ucesnikService.saveUcesnik(ucesnik);
            return m;
        }

        if(result.hasErrors()) {
            List<Drzave> listaDrzava = drzavaService.findAll();
            List<DrzaveDTO> drzaveDTOS = drzaveMapper.convertListToDto(listaDrzava);
            ModelAndView m = new ModelAndView("redirect:/user/dodajUcesnikB/" +idIzvm);
            m.addObject("userName", userDTO.getName() + " " + userDTO.getLastName());
            m.setViewName("user/ucesnikB");
            m.addObject("listaDrzava",drzaveDTOS);
            m.addObject("drzaveDTOS",drzaveDTOS);
            m.addObject("ucesnikDTO", ucesnikDTO);
            m.addObject("successMessage", "Imate gresku");
            return m;
        }
        return new ModelAndView("redirect:/user/dodajUcesnikB/" + idIzvm);
    }

    @RequestMapping(value = "/user/dodajUgovaraca/{idIzvm}", method = RequestMethod.GET)
    public ModelAndView dodajUgovaraca(@PathVariable Long idIzvm) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        UgovaracOsiguranjaBDTO ugovaracOsiguranjaBDTO = null;

        VozacNezgodaManja manja = vozacNezIzvService.findManjaById(idIzvm);
        UgovaracOsiguranjaB uob = ugovaracOsiguranjaBService.findByVozacNezgodaManja(manja);
        if (uob != null) {
            ugovaracOsiguranjaBDTO = ugovaracOsiguranjaBMapper.convertToDto(uob);
        } else {
            ugovaracOsiguranjaBDTO = new UgovaracOsiguranjaBDTO();
        }

        boolean poslat = manja.isPoslat();

        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("ugovaracOsiguranjaBDTO", ugovaracOsiguranjaBDTO);
        modelAndView.addObject("idIzvm",idIzvm);
        modelAndView.addObject("poslat", poslat);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("user/ugovaracB");
        return modelAndView;
    }

    @RequestMapping(value = "/user/snimiUgovaracaB/{idIzvm}", method = RequestMethod.POST)
    public ModelAndView snimiUgovaracaB(@Valid @ModelAttribute UgovaracOsiguranjaBDTO ugovaracOsiguranjaBDTO,
                                        BindingResult result,
                                        RedirectAttributes redirectAttributes,
                                        @PathVariable Long idIzvm,
                                        @RequestParam MultipartFile[] fileUpload) throws IOException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);
        if(!result.hasErrors()){
            ModelAndView m = new ModelAndView("redirect:/user/dodajUgovaraca/" +idIzvm);
            redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
            redirectAttributes.addFlashAttribute("successMessage", "Ugovarač osiguranja uspesno sačuvan!");


            UgovaracOsiguranjaB uob = ugovaracOsiguranjaBService.findUOBByVozacNezgodaManjaId(idIzvm);
            if(uob!=null){
                ugovaracOsiguranjaBDTO.setFotografijaPoliseUO(uob.getFotografijaPoliseUO());
            }
            if (fileUpload != null) {
                for (MultipartFile aFile : fileUpload) {
                    if (aFile.isEmpty()) break;
                    ugovaracOsiguranjaBDTO.setFotografijaPoliseUO(aFile.getBytes());
                }
            }

            UgovaracOsiguranjaB ugovaracOsiguranjaB = ugovaracOsiguranjaBMapper.convertToEntity(ugovaracOsiguranjaBDTO);
            ugovaracOsiguranjaB.setVozacNezgodaManja(vozacNezIzvService.findManjaById(idIzvm));
            ugovaracOsiguranjaBService.saveUgovaracOsigruranjaB(ugovaracOsiguranjaB);
            return m;
        }

        if(result.hasErrors()) {
            ModelAndView m = new ModelAndView("redirect:/user/dodajUgovaraca/" +idIzvm);
            m.addObject("userName", user.getName() + " " + user.getLastName());
            m.addObject("ugovaracOsiguranjaBDTO", ugovaracOsiguranjaBDTO);
            m.setViewName("user/ugovaracB");
            m.addObject("successMessage", "Imate gresku");
            return m;
        }
        return new ModelAndView("redirect:/user/dodajUgovaraca");
    }



    @RequestMapping(value = "/user/dodajVoziloB/{idIzvm}", method = RequestMethod.GET)
    public ModelAndView dodajVoziloB(@PathVariable Long idIzvm) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        List<Drzave> listaDrzava = drzavaService.findAll();
        List<DrzaveDTO> drzaveDTOS = drzaveMapper.convertListToDto(listaDrzava);

        VoziloBDTO voziloBDTO = null;
        VozacNezgodaManja manja = vozacNezIzvService.findManjaById(idIzvm);
        VoziloB vb = voziloBService.findByVozacNezgodaManja(manja);
        if (vb != null) {
            voziloBDTO = voziloBMapper.convertToDto(vb);
        } else {
            voziloBDTO = new VoziloBDTO();
        }

        boolean poslat = manja.isPoslat();

        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("voziloBDTO", voziloBDTO);
        modelAndView.addObject("drzaveDTOS", drzaveDTOS);
        modelAndView.addObject("poslat",poslat);
        modelAndView.addObject("idIzvm", idIzvm);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("user/voziloB");
        return modelAndView;

    }

    @RequestMapping(value = "/user/snimiVoziloB/{idIzvm}", method = RequestMethod.POST)
    public ModelAndView snimiVoziloB(@Valid @ModelAttribute VoziloBDTO voziloBDTO,
                                     BindingResult result,
                                     RedirectAttributes redirectAttributes,
                                     @PathVariable Long idIzvm,
                                     @RequestParam MultipartFile[] fileUpload) throws IOException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        List<Drzave> listaDrzava = drzavaService.findAll();
        List<DrzaveDTO> drzaveDTOS = drzaveMapper.convertListToDto(listaDrzava);

        if(!result.hasErrors()){
            ModelAndView m = new ModelAndView("redirect:/user/dodajVoziloB/" +idIzvm);
            redirectAttributes.addFlashAttribute("drzaveDTOS", drzaveDTOS);
            redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
            redirectAttributes.addFlashAttribute("successMessage", "Vozilo uspesno sačuvano!");

            VoziloB v = voziloBService.findUOBByVozacNezgodaManjaId(idIzvm);
            if(v!=null){
                voziloBDTO.setSlikaVozila(v.getSlikaVozila());
            }
            if (fileUpload != null) {
                for (MultipartFile aFile : fileUpload) {
                    if (aFile.isEmpty()) break;
                    voziloBDTO.setSlikaVozila(aFile.getBytes());
                }
            }

            VoziloB voziloB = voziloBMapper.convertToEntity(voziloBDTO);
            voziloB.setVozacNezgodaManja(vozacNezIzvService.findManjaById(idIzvm));
            voziloBService.saveVoziloB(voziloB);
            return m;
        }
        if(result.hasErrors()) {
            ModelAndView m = new ModelAndView("redirect:/user/dodajVoziloB");
            m.addObject("voziloBDTO", voziloBDTO);
            m.addObject("drzaveDTOS", drzaveDTOS);
            m.addObject("userName", userDTO.getName() + " " + userDTO.getLastName());
            m.setViewName("user/voziloB");
            m.addObject("successMessage", "Imate gresku");
            return m;
        }
        return new ModelAndView("redirect:/user/dodajVoziloB/" +idIzvm);
    }

    @RequestMapping(value = "/user/dodajPrikolicuB/{idIzvm}", method = RequestMethod.GET)
    public ModelAndView dodajPrikolicuB(@PathVariable Long idIzvm) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        PrikolicaBDTO prikolicaBDTO = null;

        VozacNezgodaManja manja = vozacNezIzvService.findManjaById(idIzvm);
        PrikolicaB pb = prikolicaBService.findByVozacNezgodaManja(manja);
        if (pb != null) {
            prikolicaBDTO = prikolicaBMapper.convertToDto(pb);
        } else {
            prikolicaBDTO = new PrikolicaBDTO();
        }

        boolean poslat = manja.isPoslat();

        List<Drzave> listaDrzava = drzavaService.findAll();
        List<DrzaveDTO> drzaveDTOS = drzaveMapper.convertListToDto(listaDrzava);
        modelAndView.addObject("userName",user.getName() + " " + user.getLastName());
        modelAndView.addObject("userDTO",userDTO);
        modelAndView.addObject("prikolicaBDTO", prikolicaBDTO);
        modelAndView.addObject("poslat", poslat);
        modelAndView.addObject("drzaveDTOS",drzaveDTOS);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("user/prikolicaB");
        return modelAndView;

    }

    @RequestMapping(value = "/user/snimiPrikolicuB/{idIzvm}", method = RequestMethod.POST)
    public ModelAndView snimiPrikolicuB(@Valid @ModelAttribute PrikolicaBDTO prikolicaBDTO,
                                        BindingResult result,
                                        RedirectAttributes redirectAttributes,
                                        @PathVariable Long idIzvm,
                                        @RequestParam MultipartFile[] fileUpload) throws IOException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        List<Drzave> listaDrzava = drzavaService.findAll();
        List<DrzaveDTO> drzaveDTOS = drzaveMapper.convertListToDto(listaDrzava);

        if(!result.hasErrors()){
            ModelAndView m = new ModelAndView("redirect:/user/dodajPrikolicuB/" +idIzvm);
            redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
            redirectAttributes.addFlashAttribute("successMessage", "Prikolica uspešno sačuvana!");

            PrikolicaB p = prikolicaBService.findUOBByVozacNezgodaManjaId(idIzvm);
            if(p!=null){
                prikolicaBDTO.setSlikaPrikolice(p.getSlikaPrikolice());
            }
            if (fileUpload != null) {
                for (MultipartFile aFile : fileUpload) {
                    if (aFile.isEmpty()) break;
                    prikolicaBDTO.setSlikaPrikolice(aFile.getBytes());
                }
            }
            PrikolicaB prikolicaB = prikolicaBMapper.convertToEntity(prikolicaBDTO);
            prikolicaB.setVozacNezgodaManja(vozacNezIzvService.findManjaById(idIzvm));
            prikolicaBService.savePrikolicaB(prikolicaB);
            return m;
        }

        if(result.hasErrors()) {
            ModelAndView m = new ModelAndView("redirect:/user/dodajPrikolicuB/" +idIzvm);
            m.addObject("userName", user.getName() + " " + user.getLastName());
            m.setViewName("user/prikolicaB");
            m.addObject("prikolicaBDTO", prikolicaBDTO);
            m.addObject("drzaveDTOS",drzaveDTOS);
            m.addObject("successMessage", "Imate gresku");
            return m;
        }
        return new ModelAndView("redirect:/user/dodajPrikolicuB" +idIzvm);
    }

    @RequestMapping(value = "/user/dodajOsiguranjeB/{idIzvm}", method = RequestMethod.GET)
    public ModelAndView dodajOsiguranjeB(@PathVariable Long idIzvm) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        List<Klijenti> listaKlijenata = klijentiService.findKlijentiByTipKlijenta("Osiguranje");
        List<KlijentiDTO> klijentiDTOS = klijentiMapper.convertListToDto(listaKlijenata);

        List<Drzave> listaDrzava = drzavaService.findAll();

        OsiguranjeBDTO osiguranjeBDTO = null;

        VozacNezgodaManja manja = vozacNezIzvService.findManjaById(idIzvm);
        OsiguranjeB ob = osiguranjeBService.findByVozacNezgodaManja(manja);
        if (ob != null) {
            osiguranjeBDTO = osiguranjeBMapper.convertToDto(ob);
        } else {
            osiguranjeBDTO = new OsiguranjeBDTO();
        }

        boolean poslat = manja.isPoslat();

        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("osiguranjeBDTO", osiguranjeBDTO);
        modelAndView.addObject("listaDrzava", listaDrzava);
        modelAndView.addObject("poslat", poslat);
        modelAndView.addObject("klijentiDTOS",klijentiDTOS);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("user/osiguranjeB");
        return modelAndView;

    }


    @RequestMapping(value = "/user/dodajOsiguranjeblankB/{idIzvm}", method = RequestMethod.GET)
    public ModelAndView dodajOsiguranjeblankB(@PathVariable Long idIzvm) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        List<Klijenti> listaKlijenata = klijentiService.findKlijentiByTipKlijenta("Osiguranje");
        List<KlijentiDTO> klijentiDTOS = klijentiMapper.convertListToDto(listaKlijenata);

        List<Drzave> listaDrzava = drzavaService.findAll();

        OsiguranjeBDTO osiguranjeBDTO = null;

        VozacNezgodaManja manja = vozacNezIzvService.findManjaById(idIzvm);
        OsiguranjeB ob = osiguranjeBService.findByVozacNezgodaManja(manja);
        if (ob != null) {
            osiguranjeBDTO = osiguranjeBMapper.convertToDto(ob);
        } else {
            osiguranjeBDTO = new OsiguranjeBDTO();
        }

        boolean poslat = manja.isPoslat();

        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("osiguranjeBDTO", osiguranjeBDTO);
        modelAndView.addObject("klijentiDTOS",klijentiDTOS);
        modelAndView.addObject("listaDrzava", listaDrzava);
        modelAndView.addObject("poslat", poslat);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("user/osiguranjeblankB");
        return modelAndView;

    }

    @RequestMapping(value = "/user/snimiOsiguranjeB/{idIzvm}", method = RequestMethod.POST)
    public ModelAndView snimiOsiguranjeB(@Valid @ModelAttribute OsiguranjeBDTO osiguranjeBDTO,
                                         BindingResult result,
                                         @PathVariable Long idIzvm,
                                         RedirectAttributes redirectAttributes) throws ParseException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        List<Klijenti> listaKlijenata = klijentiService.findKlijentiByTipKlijenta("Osiguranje");
        List<KlijentiDTO> klijentiDTOS = klijentiMapper.convertListToDto(listaKlijenata);

        List<Drzave> listaDrzava = drzavaService.findAll();


        if(result.hasErrors()){
            ModelAndView m = new ModelAndView("redirect:/user/dodajOsiguranjeB/" +idIzvm);
            m.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
            m.setViewName("user/osiguranjeB");
            m.addObject("osiguranjeBDTO", osiguranjeBDTO);
            m.addObject("listaDrzava", listaDrzava);
            m.addObject("klijentiDTOS",klijentiDTOS);
            m.addObject("successMessage", "Imate grešku!");
            return m;
        }
        if (!result.hasErrors()) {
            ModelAndView m = new ModelAndView("redirect:/user/dodajOsiguranjeB/" +idIzvm);
            redirectAttributes.addFlashAttribute("userName",user.getName() + " " + user.getLastName());
            redirectAttributes.addFlashAttribute("klijentiDTOS",klijentiDTOS);
            redirectAttributes.addFlashAttribute("listaDrzava", listaDrzava);
            redirectAttributes.addFlashAttribute("successMessage", "Osiguranje uspešno sačuvano!");
            OsiguranjeB osiguranje = osiguranjeBMapper.convertToEntity(osiguranjeBDTO);
            osiguranje.setVozacNezgodaManja(vozacNezIzvService.findManjaById(idIzvm));
            osiguranjeBService.updateOsiguranjeB(osiguranje);
            return m;
        }
        return new ModelAndView("redirect:/user/dodajOsiguranjeB/" +idIzvm);
    }



    @RequestMapping(value = "/user/imageUcesnik/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE} )
    public ResponseEntity<byte[]> getImageUcesnik(@PathVariable Long id) throws IOException {
        Ucesnik ucesnik = ucesnikService.findUcesnikById(id);
        byte[] imageContent = ucesnik.getSlikaUcesnik();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/imageUgovaracOsiguranjaB/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE} )
    public ResponseEntity<byte[]> getImageUgovaracOsiguranjaB(@PathVariable Long id) throws IOException {
        UgovaracOsiguranjaB ugovaracOsiguranjaB = ugovaracOsiguranjaBService.findUgovaracOsiguranjaBById(id);
        byte[] imageContent = ugovaracOsiguranjaB.getFotografijaPoliseUO();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/imagevoziloB/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE} )
    public ResponseEntity<byte[]> getImageVoziloB(@PathVariable Long id) throws IOException {
        VoziloB voziloB = voziloBService.findVoziloBById(id);
        byte[] imageContent = voziloB.getSlikaVozila();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/imagePrikolicaB/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE} )
    public ResponseEntity<byte[]> getPrikolicaB(@PathVariable Long id) throws IOException {
        PrikolicaB prikolicaB = prikolicaBService.findPrikolicaBById(id);
        byte[] imageContent = prikolicaB.getSlikaPrikolice();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

}
