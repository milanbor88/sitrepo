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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Controller
public class IzvNezgodaVecaController {


    @Autowired
    private UserService userService;


    @Autowired
    VozacNezgodaService vozacNezgodaService;

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
    VozacSvedokService vozacSvedokService;

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


    @RequestMapping(value = "/user/home", method = RequestMethod.GET)
    public ModelAndView priizvestaji() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        VoziloDTO voziloDTO = null;
        PrikolicaDTO prikolicaDTO = null;
        OsiguranjeDTO osiguranjeDTO = null;
        UgovaracOsiguranjaDTO ugovaracOsiguranjaDTO = null;

        List<VozacNezgodaVeca> listaIzvestajaVecaVozac = vozacNezIzvService.findVozacNezgodaVecaByUser(user);
        List<VozacNezgodaVecaDTO> vozacNezgodaVecaVozacDTOS = vozacNezgodaVecaMapper.convertListToDto(listaIzvestajaVecaVozac);

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


        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("user", userDTO);
        modelAndView.addObject("osiguranjeDTO", osiguranjeDTO);
        modelAndView.addObject("voziloDTO", voziloDTO);
        modelAndView.addObject("prikolicaDTO",prikolicaDTO);
        modelAndView.addObject("ugovaracOsiguranjaDTO",ugovaracOsiguranjaDTO);
        modelAndView.addObject("listaIzvestajaVecaVozac", vozacNezgodaVecaVozacDTOS);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("user/home");
        return modelAndView;
    }


    @RequestMapping(value = "/user/detaljiIzvestajaVecaVozac/{id}", method = RequestMethod.GET)
    public ModelAndView detaljiIzvestajaVecaVozac(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        VozacNezgodaVeca vnv = vozacNezIzvService.findVecaById(id);


        VozacNezgoda vn = vnv.getVozacNezgoda();
        VozacNezgodaDTO vozacNezgodaDTO = vozacNezgodaMapper.convertToDto(vn);

        List<PovredjeniVaseVozilo> povredjeniVaseVoziloVS = povredjeniVaseVoziloService.findPvvByVozacNezgodaIzvestajId(id);
        List<PovredjeniVanVozila> povredjeniVanVozilaVS = povredjeniVanVozilaService.findPvanByVozacNezgodaIzvestajId(id);
        List<PovredjeniDrugoVozilo> povredjeniDrugoVoziloVS = povredjeniDrugoVoziloService.findPdvByVozacNezgodaIzvestajId(id);
        List<VozacSvedok> vozacSvedoks = vozacSvedokService.findAllByVozacNezgodaIzvestajId(id);

        boolean poslat = vnv.isPoslat();

        modelAndView.addObject("userName",user.getName() + " " + user.getLastName());
        modelAndView.addObject("vozacnezgoda", vozacNezgodaDTO);
        modelAndView.addObject("povredjeniVaseVoziloVS", povredjeniVaseVoziloVS);
        modelAndView.addObject("povredjeniVanVozilaVS", povredjeniVanVozilaVS);
        modelAndView.addObject("povredjeniDrugoVoziloVS", povredjeniDrugoVoziloVS);
        modelAndView.addObject("vozacSvedoks", vozacSvedoks);
        modelAndView.addObject("poslat", poslat);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("user/detaljiIzvestajaVecaVozac");
        modelAndView.addObject("idIzv", id);

        return modelAndView;
    }

    @RequestMapping(value = "/user/dodajSvedoka/{idIzv}", method = RequestMethod.GET)
    public ModelAndView dodajVozacSvedok(@PathVariable Long idIzv) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        VozacSvedok vs = new VozacSvedok();
        VozacSvedokDTO vozacSvedokDTO = vozacSvedokMapper.convertToDto(vs);

        modelAndView.addObject("userName", userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("vozacSvedokDTO", vozacSvedokDTO);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.addObject("idIzv", idIzv);
        modelAndView.setViewName("user/dodajSvedoka");
        return modelAndView;

    }

    @RequestMapping(value = "/user/snimiSvedokaVeca/{idIzv}", method = RequestMethod.POST)
    public ModelAndView snimiPrikolicuB(@Valid @ModelAttribute VozacSvedokDTO vozacSvedokDTO,
                                        BindingResult result,
                                        RedirectAttributes redirectAttributes,
                                        @PathVariable Long idIzv,
                                        @RequestParam MultipartFile[] fileUpload) throws IOException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        if(!result.hasErrors()){
            ModelAndView m = new ModelAndView("redirect:/user/dodajSvedoka/"+idIzv);
            redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
            redirectAttributes.addFlashAttribute("successMessage", "Svedok uspešno sačuvan možete uneti još svedoka");
            if (fileUpload != null && fileUpload.length > 0) {
                for (MultipartFile aFile : fileUpload) {
                    if (aFile.isEmpty()) break;
                    vozacSvedokDTO.setFotografijaLicneKarte(aFile.getBytes());
                }
            }
            VozacSvedok vozacSvedok = vozacSvedokMapper.convertToEntity(vozacSvedokDTO);
            vozacSvedok.setVozacNezgodaIzvestaj(vozacNezIzvService.findVecaById(idIzv));
            vozacSvedokService.saveVozacSvedok(vozacSvedok);
            return m;
        }

        if(result.hasErrors()) {
            ModelAndView m = new ModelAndView("redirect:/user/dodajSvedoka" + idIzv);
            m.addObject("userName", userDTO.getName() + " " + userDTO.getLastName());
            m.addObject("vozacSvedokDTO", vozacSvedokDTO);
            m.setViewName("user/dodajSvedoka");
            m.addObject("successMessage", "Imate gresku");
            return m;
        }
        return new ModelAndView("redirect:/user/dodajSvedoka/" +idIzv);
    }


    @RequestMapping(value = "/user/obrisiVozacaSvedoka/{id}/{idIzv}", method = RequestMethod.GET)
    public ModelAndView obrisiVozacaSvedoka(@PathVariable Long id,
                                            @PathVariable Long idIzv,
                                            RedirectAttributes redirectAttributes){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        vozacSvedokService.deleteVozacSvedok(id);

        ModelAndView m = new ModelAndView("redirect:/user/detaljiIzvestajaVecaVozac/" + idIzv);
        redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
        redirectAttributes.addFlashAttribute("successMessage", "Svedok uspesno obrisan");
        return m;

    }


    @RequestMapping(value = "/user/nazadVeca", method = RequestMethod.GET)
    public ModelAndView unazad(RedirectAttributes redirectAttributes){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        ModelAndView m = new ModelAndView("redirect:/user/home");
        redirectAttributes.addFlashAttribute("userName",user.getName() + " " + user.getLastName());
        return m;

    }


    @RequestMapping(value = "/user/nazadDetaljiIzvestajaVecaVozac/{idIzv}", method = RequestMethod.GET)
    public ModelAndView unazadDetaljiIzvestajaVecaVozac(@PathVariable Long idIzv,RedirectAttributes redirectAttributes){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        ModelAndView m = new ModelAndView("redirect:/user/detaljiIzvestajaVecaVozac/"+idIzv);
        redirectAttributes.addFlashAttribute("userName",user.getName() + " " + user.getLastName());
        redirectAttributes.addFlashAttribute("idIzv", idIzv);
        return m;

    }

    @RequestMapping(value = "/user/imageVozacSvedok/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE} )
    public ResponseEntity<byte[]> getImageVozacSvedok(@PathVariable Long id) throws IOException {
        VozacSvedok vozacSvedok = vozacSvedokService.findVozacSvedokById(id);
        byte[] imageContent = vozacSvedok.getFotografijaLicneKarte();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }





}
