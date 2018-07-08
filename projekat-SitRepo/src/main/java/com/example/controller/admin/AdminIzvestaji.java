package com.example.controller.admin;

import com.example.dto.*;
import com.example.mapper.*;
import com.example.model.*;
import com.example.service.*;
import com.example.util.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;


@Controller
public class AdminIzvestaji {

    @Autowired
    EmailService emailService;

    @Autowired
    VozacNezIzvService vecaService;

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @Autowired
    UserPageService userPageService;

    @Autowired
    private UserService userService;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private KlijentiService klijentiService;


    @Autowired
    VozacNezIzvService vozacNezIzvService;

    @Autowired
    VozacNezgodaVecaMapper vozacNezgodaVecaMapper;

    @Autowired
    VozacNezgodaManjaMapper manjaMapper;

    @Autowired
    VoziloService voziloService;

    @Autowired
    VoziloMapper voziloMapper;

    @Autowired
    private PutnikIzvestajMapper putnikIzvestajMapper;

    @Autowired
    private PutnikIzvestajService putnikIzvestajService;

    @Autowired
    VozacNezgodaManjaMapper vozacNezgodaManjaMapper;

    @Autowired
    VozacNezgodaManjaMapper vozacNezgodaManjaMapperaMapper;

    @RequestMapping(value = "/admin/saljiIzvVeca/{idIzv}")
    public ModelAndView posaljiIzvVeca(@PathVariable Long idIzv,
                                       @RequestParam("pageSize") Optional<Integer> pageSize,
                                       @RequestParam("page") Optional<Integer> page) throws IOException, MessagingException, ParseException {

       Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       User user = userService.findUserByEmail(auth.getName());
       UserDTO userDTO = userMapper.convertToDto(user);

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        VozacNezgodaVeca veca = vecaService.findVecaById(idIzv);
        VozacNezgodaVecaDTO vecaDTO = vozacNezgodaVecaMapper.convertToDto(veca);

        //emailService.sendIzvVeca(vecaDTO, "duljarevic@gmail.com");
        //emailService.sendIzvVeca(vecaDTO, "milanstoka@gmail.com");
        //skloniti red iznad a odkomentarisati donji red

        Klijenti k = klijentiService.findByName(veca.getNazivOsiguranja());

        if (k == null) {

            Page<VozacNezgodaVeca> listaIzvestajaVecaVozac = vozacNezIzvService.findByPoslatPagebleVeca(false, new PageRequest(evalPage,evalPageSize));
            List<VozacNezgodaVecaDTO> vozacNezgodaVecaDTOS = vozacNezgodaVecaMapper.convertListToDto(listaIzvestajaVecaVozac.getContent());

            Pager pager = new Pager(listaIzvestajaVecaVozac.getTotalPages(), listaIzvestajaVecaVozac.getNumber(), BUTTONS_TO_SHOW);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("vozacNezgodaVecaVozacDTOS", vozacNezgodaVecaDTOS);
            modelAndView.addObject("successMessage", "Imate grešku! Proverite da li je osiguranje validno!");
            modelAndView.setViewName("admin/adminIzvestajiVecaVozacmsg");
            modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
            modelAndView.addObject("selectedPageSize", evalPageSize);
            modelAndView.addObject("pageSizes", PAGE_SIZES);
            modelAndView.addObject("pager", pager);
            modelAndView.addObject("listaIzvestajaVecaVozac",listaIzvestajaVecaVozac);
            return modelAndView;
        }

        if(veca.getNazivOsiguranja().equals(k.getName()) && veca.getDrzava().equals(k.getDrzava())){

            Klijenti klijenti = klijentiService.findByNameDrz(veca.getNazivOsiguranja(),
                    veca.getDrzava());

            emailService.sendIzvVeca(vecaDTO, klijenti.getEmail());

            veca.setPoslat(true);
            vozacNezIzvService.save(veca);

            Page<VozacNezgodaVeca> listaIzvestajaVecaVozac = vozacNezIzvService.findByPoslatPagebleVeca(false, new PageRequest(evalPage,evalPageSize));
            List<VozacNezgodaVecaDTO> vozacNezgodaVecaDTOS = vozacNezgodaVecaMapper.convertListToDto(listaIzvestajaVecaVozac.getContent());

            Pager pager = new Pager(listaIzvestajaVecaVozac.getTotalPages(), listaIzvestajaVecaVozac.getNumber(), BUTTONS_TO_SHOW);

            if(vozacNezgodaVecaDTOS.isEmpty()){

                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("vozacNezgodaVecaVozacDTOS", vozacNezgodaVecaDTOS);
                modelAndView.setViewName("admin/adminIzvestajiVecaVozacBlanko");
                modelAndView.addObject("userDTO",userDTO);
                modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());

                return modelAndView;
            }

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("vozacNezgodaVecaVozacDTOS", vozacNezgodaVecaDTOS);
            modelAndView.addObject("successMessage", "Uspešno ste poslali izveštaj!");
            modelAndView.setViewName("admin/adminIzvestajiVecaVozacmsg");
            modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
            modelAndView.addObject("selectedPageSize", evalPageSize);
            modelAndView.addObject("pageSizes", PAGE_SIZES);
            modelAndView.addObject("pager", pager);
            modelAndView.addObject("listaIzvestajaVecaVozac",listaIzvestajaVecaVozac);
            return modelAndView;
        }

        Page<VozacNezgodaVeca> listaIzvestajaVecaVozac = vozacNezIzvService.findByPoslatPagebleVeca(false, new PageRequest(evalPage,evalPageSize));
        List<VozacNezgodaVecaDTO> vozacNezgodaVecaDTOS = vozacNezgodaVecaMapper.convertListToDto(listaIzvestajaVecaVozac.getContent());

        Pager pager = new Pager(listaIzvestajaVecaVozac.getTotalPages(), listaIzvestajaVecaVozac.getNumber(), BUTTONS_TO_SHOW);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("vozacNezgodaVecaVozacDTOS", vozacNezgodaVecaDTOS);
            modelAndView.addObject("successMessage", "Ipate grešku! Proverite da li je osiguranje validno!");
            modelAndView.setViewName("admin/adminIzvestajiVecaVozacmsg");
            modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("listaIzvestajaVecaVozac",listaIzvestajaVecaVozac);
            return modelAndView;
    }


    @RequestMapping(value = "/admin/saljiIzvManja/{idIzv}")
    public ModelAndView posaljiIzvManja(@PathVariable Long idIzv,
                                        @RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page) throws IOException, MessagingException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        VozacNezgodaManja manja = vecaService.findManjaById(idIzv);
        VozacNezgodaManjaDTO manjaDTO = manjaMapper.convertToDto(manja);
       // emailService.sendIzvManja(manjaDTO, "duljarevic@gmail.com");
        //skloniti red iznad a odkomentarisati donji red

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;


        Klijenti k = klijentiService.findByName(manja.getNazivOsiguranja());
        if(k==null){

            Page<VozacNezgodaManja> listaIzvestajaManjaVozac = vozacNezIzvService.findByPoslatPagebleManja(false, new PageRequest(evalPage,evalPageSize));
            List<VozacNezgodaManjaDTO> vozacNezgodaManjaDTO = vozacNezgodaManjaMapperaMapper.convertListToDto(listaIzvestajaManjaVozac.getContent());

            Pager pager = new Pager(listaIzvestajaManjaVozac.getTotalPages(), listaIzvestajaManjaVozac.getNumber(), BUTTONS_TO_SHOW);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin/adminIzvestajiManjaVozacmsg");
            modelAndView.addObject("vozacNezgodaManjaDTO",vozacNezgodaManjaDTO);
            modelAndView.addObject("successMessage", "Imate grešku! Proverite da li je osiguranje validno!");
            modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());

            modelAndView.addObject("selectedPageSize", evalPageSize);
            modelAndView.addObject("pageSizes", PAGE_SIZES);
            modelAndView.addObject("pager", pager);
            modelAndView.addObject("listaIzvestajaManjaVozac",listaIzvestajaManjaVozac);
            return modelAndView;
        }

        if(manja.getNazivOsiguranja().equals(k.getName()) && manja.getDrzava().equals(k.getDrzava())){

            Klijenti klijenti = klijentiService.findByNameDrz(manja.getNazivOsiguranja(),
                    manja.getDrzava());
            emailService.sendIzvManja(manjaDTO, klijenti.getEmail());

            manja.setPoslat(true);
            vozacNezIzvService.save(manja);

            Page<VozacNezgodaManja> listaIzvestajaManjaVozac = vozacNezIzvService.findByPoslatPagebleManja(false, new PageRequest(evalPage,evalPageSize));
            List<VozacNezgodaManjaDTO> vozacNezgodaManjaDTO = vozacNezgodaManjaMapperaMapper.convertListToDto(listaIzvestajaManjaVozac.getContent());

            Pager pager = new Pager(listaIzvestajaManjaVozac.getTotalPages(), listaIzvestajaManjaVozac.getNumber(), BUTTONS_TO_SHOW);

            if(vozacNezgodaManjaDTO.isEmpty()){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("admin/adminIzvestajiManjaVozacBlanko");
                modelAndView.addObject("vozacNezgodaManjaDTO",vozacNezgodaManjaDTO);
                modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());

                return modelAndView;
            }

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin/adminIzvestajiManjaVozacmsg");
            modelAndView.addObject("vozacNezgodaManjaDTO",vozacNezgodaManjaDTO);
            modelAndView.addObject("successMessage", "Uspešno ste poslali izveštaj!");
            modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());

            modelAndView.addObject("selectedPageSize", evalPageSize);
            modelAndView.addObject("pageSizes", PAGE_SIZES);
            modelAndView.addObject("pager", pager);
            modelAndView.addObject("listaIzvestajaManjaVozac",listaIzvestajaManjaVozac);
            return modelAndView;
        }
        Page<VozacNezgodaManja> listaIzvestajaManjaVozac = vozacNezIzvService.findByPoslatPagebleManja(false, new PageRequest(evalPage,evalPageSize));
        List<VozacNezgodaManjaDTO> vozacNezgodaManjaDTO = vozacNezgodaManjaMapperaMapper.convertListToDto(listaIzvestajaManjaVozac.getContent());

        Pager pager = new Pager(listaIzvestajaManjaVozac.getTotalPages(), listaIzvestajaManjaVozac.getNumber(), BUTTONS_TO_SHOW);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/adminIzvestajiManjaVozacmsg");
        modelAndView.addObject("vozacNezgodaManjaDTO",vozacNezgodaManjaDTO);
        modelAndView.addObject("successMessage", "Imate grešku! Proverite da li je osiguranje validno!");
        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());

        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("listaIzvestajaManjaVozac",listaIzvestajaManjaVozac);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/saljiIzvVecaPutnik/{idIzv}")
    public ModelAndView posaljiIzvVecaPutnik(@PathVariable Long idIzv,
                                             @RequestParam("pageSize") Optional<Integer> pageSize,
                                             @RequestParam("page") Optional<Integer> page) throws IOException, MessagingException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        PutnikIzvestaj putnikIzvestaj = putnikIzvestajService.findById(idIzv);
        PutnikIzvestajDTO putnikIzvestajDTO = putnikIzvestajMapper.convertToDto(putnikIzvestaj);

        Klijenti k = klijentiService.findByName(putnikIzvestaj.getNazivOsiguranja());

        if(k==null) {

            Page<PutnikIzvestaj> listaPutinikIzv = putnikIzvestajService.findByPoslatPagePutnikIzv(false, new PageRequest(evalPage,evalPageSize));
            List<PutnikIzvestajDTO> vozacNezgodaVecaPutnikDTO = putnikIzvestajMapper.convertListToDto(listaPutinikIzv.getContent());

            Pager pager = new Pager(listaPutinikIzv.getTotalPages(), listaPutinikIzv.getNumber(), BUTTONS_TO_SHOW);

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin/adminIzvestajiVecaPutnikmsg");
            modelAndView.addObject("vozacNezgodaVecaPutnikDTO", vozacNezgodaVecaPutnikDTO);
            modelAndView.addObject("successMessage", "Imate grešku! Proverite da li je osiguranje validno!");
            modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());

            modelAndView.addObject("selectedPageSize", evalPageSize);
            modelAndView.addObject("pageSizes", PAGE_SIZES);
            modelAndView.addObject("pager", pager);
            modelAndView.addObject("listaPutinikIzv",listaPutinikIzv);
            return modelAndView;
        }

        if(putnikIzvestaj.getNazivOsiguranja().equals(k.getName()) && putnikIzvestaj.getDrzava().equals(k.getDrzava())) {

            Klijenti klijenti = klijentiService.findByNameDrz(putnikIzvestaj.getNazivOsiguranja(), putnikIzvestaj.getDrzava());
            emailService.sendIzvPutnik(putnikIzvestajDTO, klijenti.getEmail());
            putnikIzvestaj.setPoslat(true);
            putnikIzvestajService.save(putnikIzvestaj);

            Page<PutnikIzvestaj> listaPutinikIzv = putnikIzvestajService.findByPoslatPagePutnikIzv(false, new PageRequest(evalPage,evalPageSize));
            List<PutnikIzvestajDTO> vozacNezgodaVecaPutnikDTO = putnikIzvestajMapper.convertListToDto(listaPutinikIzv.getContent());

            Pager pager = new Pager(listaPutinikIzv.getTotalPages(), listaPutinikIzv.getNumber(), BUTTONS_TO_SHOW);

            if(vozacNezgodaVecaPutnikDTO.isEmpty()){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("admin/adminIzvestajiVecaPutnikBlanko");
                modelAndView.addObject("vozacNezgodaVecaPutnikDTO", vozacNezgodaVecaPutnikDTO);
                modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
                return modelAndView;

            }

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin/adminIzvestajiVecaPutnikmsg");
            modelAndView.addObject("vozacNezgodaVecaPutnikDTO", vozacNezgodaVecaPutnikDTO);
            modelAndView.addObject("successMessage", "Uspešno ste poslali izveštaj!");
            modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());

            modelAndView.addObject("selectedPageSize", evalPageSize);
            modelAndView.addObject("pageSizes", PAGE_SIZES);
            modelAndView.addObject("pager", pager);
            modelAndView.addObject("listaPutinikIzv",listaPutinikIzv);
            return modelAndView;

        }

        Page<PutnikIzvestaj> listaPutinikIzv = putnikIzvestajService.findByPoslatPagePutnikIzv(false, new PageRequest(evalPage,evalPageSize));
        List<PutnikIzvestajDTO> vozacNezgodaVecaPutnikDTO = putnikIzvestajMapper.convertListToDto(listaPutinikIzv.getContent());

        Pager pager = new Pager(listaPutinikIzv.getTotalPages(), listaPutinikIzv.getNumber(), BUTTONS_TO_SHOW);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/adminIzvestajiVecaPutnikmsg");
        modelAndView.addObject("vozacNezgodaVecaPutnikDTO", vozacNezgodaVecaPutnikDTO);
        modelAndView.addObject("successMessage", "Imate grešku! Proverite da li je osiguranje validno!!");
        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());

        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("listaPutinikIzv",listaPutinikIzv);
        return modelAndView;
    }
}
