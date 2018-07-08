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
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class IzvNezgodaPutnikController {


    @Autowired
    private UserService userService;

    @Autowired
    PutnikNezgodaMapper putnikNezgodaMapper;

    @Autowired
    PutnikIzvestajService putnikIzvestajService;

    @Autowired
    PutnikIzvestajMapper putnikIzvestajMapper;

    @Autowired
    OsiguranjeService osiguranjeService;

    @Autowired
    OsiguranjeMapper osiguranjeMapper;

    @Autowired
    PutnikNezgodaService putnikNezgodaService;

    @Autowired
    PutnikSvedokService putnikSvedokService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PutnikSvedokMapper putnikSvedokMapper;


    @RequestMapping(value = "/user/nezgodaPutnik", method = RequestMethod.GET)
    public ModelAndView nezgodaPutnik() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        List<PutnikIzvestaj> pi = putnikIzvestajService.findByUser(user);
        List<PutnikIzvestajDTO> putnikIzvestajDTOS = putnikIzvestajMapper.convertListToDto(pi);

        OsiguranjeDTO osiguranjeDTO = null;
        Osiguranje o = osiguranjeService.findOsiguranjeByUser(user);
        if (o != null) {
            osiguranjeDTO = osiguranjeMapper.convertToDto(o);
        } else {
            osiguranjeDTO = new OsiguranjeDTO();
        }

        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("putnikIzvestaj", putnikIzvestajDTOS);
        modelAndView.addObject("user",userDTO);
        modelAndView.addObject("osiguranjePutnik", osiguranjeDTO);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("user/nezgodaPutnik");
        return modelAndView;

    }

    @RequestMapping(value = "/user/detaljizvestajaNezgodaPutnik/{id}", method = RequestMethod.GET)
    public ModelAndView detaljizvestajaNezgodaPutnik(@PathVariable Long id) {

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);


        PutnikIzvestaj pi = putnikIzvestajService.findById(id);

        //PutnikNezgoda pn = putnikNezgodaService.findPutnikNezgodaById(id);
        PutnikNezgoda pn = pi.getPutnikNezgoda();
        PutnikNezgodaDTO putnikNezgodaDTO = putnikNezgodaMapper.convertToDto(pn);

        List<PutnikSvedok> putnikSvedoks = putnikSvedokService.findPutnikSvedokByPutnikIzvestajId(id);
        List<PutnikSvedokDTO> putnikSvedokDTOS = putnikSvedokMapper.convertListToDto(putnikSvedoks);

        boolean poslat = pi.isPoslat();


        modelAndView.addObject("userName",user.getName() + " " + user.getLastName());
        modelAndView.addObject("putniknezgoda", putnikNezgodaDTO);
        modelAndView.addObject("putnikSvedokDTOS",putnikSvedokDTOS);
        modelAndView.addObject("poslat", poslat);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("user/detaljizvestajaNezgodaPutnik");
        modelAndView.addObject("idIzvp", id);

        return modelAndView;
    }

    @RequestMapping(value = "/user/dodajPutnikSvedok/{idIzvp}", method = RequestMethod.GET)
    public ModelAndView dodajPutnikSvedok(@PathVariable Long idIzvp) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        PutnikSvedok pu = new PutnikSvedok();
        PutnikSvedokDTO putnikSvedokDTO = putnikSvedokMapper.convertToDto(pu);

        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("putnikSvedokDTO", putnikSvedokDTO);
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("user/dodajPutnikSvedok");
        modelAndView.addObject("idIzvp", idIzvp);

        return modelAndView;
    }

    @RequestMapping(value = "/user/snimiSvedokaPutnika/{idIzvp}", method = RequestMethod.POST)
    public ModelAndView updatePutnikSvedok(@Valid @ModelAttribute PutnikSvedokDTO putnikSvedokDTO,
                                           BindingResult result,
                                           RedirectAttributes redirectAttributes,
                                           @PathVariable Long idIzvp,
                                           @RequestParam MultipartFile[] fileUpload) throws IOException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        if(!result.hasErrors()){
            ModelAndView m = new ModelAndView("redirect:/user/dodajPutnikSvedok/" +idIzvp);
            redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
            redirectAttributes.addFlashAttribute("successMessage", "Svedok uspesno sačuvan možete dodati jos svedoka!");
            if (fileUpload != null && fileUpload.length > 0) {
                for (MultipartFile aFile : fileUpload) {
                    if (aFile.isEmpty()) break;
                    putnikSvedokDTO.setFotografijaLicneKarte(aFile.getBytes());
                }
            }
            PutnikSvedok putnikSvedok = putnikSvedokMapper.convertToEntity(putnikSvedokDTO);
            putnikSvedok.setPutnikIzvestaj(putnikIzvestajService.findById(idIzvp));
            putnikSvedokService.savePutnikSvedok(putnikSvedok);
            return m;
        }

        if(result.hasErrors()) {
            ModelAndView m = new ModelAndView("redirect:/user/dodajPutnikSvedok/"+idIzvp);
            m.addObject("userName", userDTO.getName() + " " + userDTO.getLastName());
            m.addObject("putnikSvedokDTO", putnikSvedokDTO);
            m.setViewName("user/dodajPutnikSvedok");
            m.addObject("successMessage", "Imate gresku");
            return m;
        }
        return new ModelAndView("redirect:/user/dodajPutnikSvedok/" +idIzvp);
    }

    @RequestMapping(value = "/user/obrisiPutnikSvedoka/{id}/{idIzvp}", method = RequestMethod.GET)
    public ModelAndView obrisiVozacaSvedoka(@PathVariable Long id,
                                            @PathVariable Long idIzvp,
                                            RedirectAttributes redirectAttributes){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        putnikSvedokService.deletePutnikSvedok(id);

        ModelAndView m = new ModelAndView("redirect:/user/detaljizvestajaNezgodaPutnik/" + idIzvp);
        redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
        redirectAttributes.addFlashAttribute("successMessage", "Svedok uspesno obrisan");
        return m;

    }

    @RequestMapping(value = "/user/nazadDetaljizvestajaNezgodaPutnik/{idIzvp}", method = RequestMethod.GET)
    public ModelAndView unazadDetaljiIzvestajaVecaVozac(@PathVariable Long idIzvp,
                                                        RedirectAttributes redirectAttributes){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        ModelAndView m = new ModelAndView("redirect:/user/detaljizvestajaNezgodaPutnik/" +idIzvp);
        redirectAttributes.addFlashAttribute("userName",user.getName() + " " + user.getLastName());
        redirectAttributes.addFlashAttribute("idIzvp", idIzvp);
        return m;

    }

    @RequestMapping(value = "/user/nazadPutnik", method = RequestMethod.GET)
    public ModelAndView nazadPutnik(RedirectAttributes redirectAttributes){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        ModelAndView m = new ModelAndView("redirect:/user/nezgodaPutnik");
        redirectAttributes.addFlashAttribute("userName",user.getName() + " " + user.getLastName());
        return m;

    }

    @RequestMapping(value = "/user/imagePutnikSvedok/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE} )
    public ResponseEntity<byte[]> getImagePutnikSvedok(@PathVariable Long id) throws IOException {
        PutnikSvedok putnikSvedok = putnikSvedokService.findPutnikSvedokById(id);
        byte[] imageContent = putnikSvedok.getFotografijaLicneKarte();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }


}
