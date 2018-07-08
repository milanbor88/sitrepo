package com.example.controller.admin;

import com.example.dto.*;
import com.example.mapper.*;
import com.example.model.*;
import com.example.service.*;
import com.example.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.*;
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
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Controller
public class AdminControler {

    private static final Logger LOGGER = Logger.getLogger( AdminControler.class.getName() );

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @Autowired
    private UserPageService userPageService;

    @Autowired
    private UserService userService;

    @Autowired
    PutnikSvedokService putnikSvedokService;

    @Autowired
    KlijentiService klijentiService;

    @Autowired
    KlijentiMapper klijentiMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    VozacNezIzvService vozacNezIzvService;

    @Autowired
    VozacNezgodaVecaMapper vozacNezgodaVecaMapper;

    @Autowired
    VozacNezgodaManjaMapper vozacNezgodaManjaMapperaMapper;

    @Autowired
    VozacNezgodaManjaMapper vozacNezgodaManjaMapper;

    @Autowired
    KlijentiPageService klijentiPageService;

    @Autowired
    EmailService emailService;

    @Autowired
    VoziloService voziloService;

    @Autowired
    VoziloMapper voziloMapper;

    @Autowired
    private OsiguranjeService osiguranjeService;

    @Autowired
    private OsiguranjeMapper osiguranjeMapper;

    @Autowired
    TipKlijentaMapper tipKlijentaMapper;

    @Autowired
    TipKlijentaService tipKlijentaService;

    @Autowired
    DrzavaService drzavaService;

    @Autowired
    PutnikIzvestajService putnikIzvestajService;

    @Autowired
    PutnikIzvestajMapper putnikIzvestajMapper;

    @Autowired
    PromoKodGen promoKodGen;


    @RequestMapping(value = {"/admin/adminIzvestajiVecaVozac"}, method = RequestMethod.GET)
    public ModelAndView adminIzvestajiVecaVozac(@RequestParam("pageSize") Optional<Integer> pageSize,
                                                @RequestParam("page") Optional<Integer> page) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<VozacNezgodaVeca> listaIzvestajaVecaVozac = vozacNezIzvService.findByPoslatPagebleVeca(false, new PageRequest(evalPage,evalPageSize));
        List<VozacNezgodaVecaDTO> vozacNezgodaVecaDTOS = vozacNezgodaVecaMapper.convertListToDto(listaIzvestajaVecaVozac.getContent());

        Pager pager = new Pager(listaIzvestajaVecaVozac.getTotalPages(), listaIzvestajaVecaVozac.getNumber(), BUTTONS_TO_SHOW);

        vozacNezgodaVecaDTOS.stream().forEach(dto -> LOGGER.info(dto.getUser().getLastName()));
        vozacNezgodaVecaDTOS.stream().forEach(d -> LOGGER.info(d.getNazivOsiguranja()));
        vozacNezgodaVecaDTOS.stream().forEach(d -> LOGGER.info(d.getRegOznakaVoz()));

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
        modelAndView.setViewName("admin/adminIzvestajiVecaVozac");
        modelAndView.addObject("userDTO",userDTO);
        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("listaIzvestajaVecaVozac",listaIzvestajaVecaVozac);

        return modelAndView;
    }

    @RequestMapping(value = {"/admin/adminPoslatiIzvestaji"}, method = RequestMethod.GET)
    public ModelAndView adminPoslatiIzvestaji(@RequestParam("pageSize") Optional<Integer> pageSize,
                                              @RequestParam("page") Optional<Integer> page) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<VozacNezgodaVeca> listaPIzvestajaVecaVozac = vozacNezIzvService.findByPoslatPagebleVeca(true, new PageRequest(evalPage,evalPageSize));
        List<VozacNezgodaVecaDTO> vozacNezgodaVecaDTOS = vozacNezgodaVecaMapper.convertListToDto(listaPIzvestajaVecaVozac.getContent());

        //vozacNezgodaVecaDTOS.stream().forEach(dto -> LOGGER.info(dto.getUser().getLastName()));

        Pager pager = new Pager(listaPIzvestajaVecaVozac.getTotalPages(), listaPIzvestajaVecaVozac.getNumber(), BUTTONS_TO_SHOW);

        if(vozacNezgodaVecaDTOS.isEmpty()){

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("vozacNezgodaVecaVozacDTOS", vozacNezgodaVecaDTOS);
            modelAndView.setViewName("admin/adminPoslatiIzvestajiBlanko");
            modelAndView.addObject("userDTO",userDTO);
            modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
            return modelAndView;

        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("vozacNezgodaVecaVozacDTOS", vozacNezgodaVecaDTOS);
        modelAndView.setViewName("admin/adminPoslatiIzvestaji");
        modelAndView.addObject("userDTO",userDTO);
        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("listaPIzvestajaVecaVozac",listaPIzvestajaVecaVozac);
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/adminIzvestajiManjaVozac"}, method = RequestMethod.GET)
    public ModelAndView adminIzvestajiManjaVozac(@RequestParam("pageSize") Optional<Integer> pageSize,
                                                 @RequestParam("page") Optional<Integer> page) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

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
        modelAndView.setViewName("admin/adminIzvestajiManjaVozac");
        modelAndView.addObject("vozacNezgodaManjaDTO",vozacNezgodaManjaDTO);
        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("listaIzvestajaManjaVozac",listaIzvestajaManjaVozac);
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/adminPoslatiIzvestajiManja"}, method = RequestMethod.GET)
    public ModelAndView adminPoslatiIzvestajiManja(@RequestParam("pageSize") Optional<Integer> pageSize,
                                                   @RequestParam("page") Optional<Integer> page) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<VozacNezgodaManja> listaPIzvestajaManjaVozac = vozacNezIzvService.findByPoslatPagebleManja(true, new PageRequest(evalPage,evalPageSize));
        List<VozacNezgodaManjaDTO> vozacPNezgodaManjaDTO = vozacNezgodaManjaMapperaMapper.convertListToDto(listaPIzvestajaManjaVozac.getContent());

        Pager pager = new Pager(listaPIzvestajaManjaVozac.getTotalPages(), listaPIzvestajaManjaVozac.getNumber(), BUTTONS_TO_SHOW);

        if(vozacPNezgodaManjaDTO.isEmpty()){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin/adminPoslatiIzvestajiManjaBlanko");
            modelAndView.addObject("vozacNezgodaManjaDTO",vozacPNezgodaManjaDTO);
            modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
            return modelAndView;

        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/adminPoslatiIzvestajiManja");
        modelAndView.addObject("vozacNezgodaManjaDTO",vozacPNezgodaManjaDTO);
        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("listaPIzvestajaManjaVozac",listaPIzvestajaManjaVozac);
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/adminIzvestajiVecaPutnik"}, method = RequestMethod.GET)
    public ModelAndView adminIzvestajiVecaPutnik(@RequestParam("pageSize") Optional<Integer> pageSize,
                                                 @RequestParam("page") Optional<Integer> page) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<PutnikIzvestaj> listaPutinikIzv = putnikIzvestajService.findByPoslatPagePutnikIzv(false, new PageRequest(evalPage,evalPageSize));
        List<PutnikIzvestajDTO> listaPutinikIzvDTO = putnikIzvestajMapper.convertListToDto(listaPutinikIzv.getContent());

        Pager pager = new Pager(listaPutinikIzv.getTotalPages(), listaPutinikIzv.getNumber(), BUTTONS_TO_SHOW);

        if(listaPutinikIzvDTO.isEmpty()){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin/adminIzvestajiVecaPutnikBlanko");
            modelAndView.addObject("vozacNezgodaVecaPutnikDTO", listaPutinikIzvDTO);
            modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
            return modelAndView;

        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/adminIzvestajiVecaPutnik");
        modelAndView.addObject("vozacNezgodaVecaPutnikDTO", listaPutinikIzvDTO);
        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("listaPutinikIzv",listaPutinikIzv);
        return modelAndView;
    }


    @RequestMapping(value = {"/admin/adminPoslatiIzvestajiPutnik"}, method = RequestMethod.GET)
    public ModelAndView adminPoslatiIzvestajiPutnik(@RequestParam("pageSize") Optional<Integer> pageSize,
                                                    @RequestParam("page") Optional<Integer> page) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<PutnikIzvestaj> vozacNezgodaVecaPutnik = putnikIzvestajService.findByPoslatPagePutnikIzv(true, new PageRequest(evalPage,evalPageSize));
        List<PutnikIzvestajDTO> vozacNezgodaVecaPutnikDTO = putnikIzvestajMapper.convertListToDto(vozacNezgodaVecaPutnik.getContent());

        if(vozacNezgodaVecaPutnikDTO.isEmpty()){

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("admin/adminPoslatiIzvestajiPutnikBlanko");
            modelAndView.addObject("vozacNezgodaVecaPutnikDTO", vozacNezgodaVecaPutnikDTO);
            modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
            return modelAndView;

        }

        Pager pager = new Pager(vozacNezgodaVecaPutnik.getTotalPages(), vozacNezgodaVecaPutnik.getNumber(), BUTTONS_TO_SHOW);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/adminPoslatiIzvestajiPutnik");
        modelAndView.addObject("vozacNezgodaVecaPutnikDTO", vozacNezgodaVecaPutnikDTO);
        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("vozacPNezgodaVecaPutnik",vozacNezgodaVecaPutnik);
        return modelAndView;
    }


    @RequestMapping(value = {"/admin/adminKlijenti"}, method = RequestMethod.GET)
    public ModelAndView adminKlijenti() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        List<TipKlijenta> tipKlijentas = tipKlijentaService.findAll();
        List<TipKlijentaDTO> tipKlijentaDTOS = tipKlijentaMapper.convertListToDto(tipKlijentas);

        List<Drzave> listaDrzava = drzavaService.findAll();

        Klijenti klijenti = new Klijenti();
        KlijentiDTO klijentiDTO = klijentiMapper.convertToDto(klijenti);
        modelAndView.addObject("userDTO",userDTO);
        modelAndView.addObject("klijentiDTO",klijentiDTO);
        modelAndView.addObject("tipKlijentaDTOS", tipKlijentaDTOS);
        modelAndView.addObject("listaDrzava", listaDrzava);
        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.setViewName("admin/adminKlijenti");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");

        return modelAndView;
    }

    @RequestMapping(value = {"/admin/tipKlijenta"}, method = RequestMethod.GET)
    public ModelAndView tipKlijenta() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);


        TipKlijenta tipKlijenta = new TipKlijenta();
        TipKlijentaDTO tipKlijentaDTO = tipKlijentaMapper.convertToDto(tipKlijenta);
        modelAndView.addObject("tipKlijentaDTO",tipKlijentaDTO);

        modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        modelAndView.setViewName("admin/tipKlijenta");

        return modelAndView;
    }

    @RequestMapping(value = {"/admin/adminKlijenti"}, method = RequestMethod.POST)
    public ModelAndView adminKlijentiPost(@Valid @ModelAttribute KlijentiDTO klijentiDTO,
                                          BindingResult result,
                                          RedirectAttributes redirectAttributes,
                                          @RequestParam MultipartFile[] fileUpload,
                                          HttpServletRequest request) throws ParseException, IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);
        List<TipKlijenta> tipKlijentas = tipKlijentaService.findAll();
        List<TipKlijentaDTO> tipKlijentaDTOS = tipKlijentaMapper.convertListToDto(tipKlijentas);
        List<Drzave> listaDrzava = drzavaService.findAll();

//        String name = request.getParameter("name");
//        String drzava = request.getParameter("drzava");
//
//        Klijenti k = klijentiService.findByNameDrz(name,drzava);
//
//
//        if (k.getName() != null && k.getDrzava() != null && !result.hasErrors()) {
//
//            ModelAndView m = new ModelAndView("redirect:/admin/adminKlijenti");
//            m.addObject("userName", userDTO.getName() + " " + userDTO.getLastName());
//            m.setViewName("admin/adminKlijenti");
//            m.addObject("tipKlijentaDTOS", tipKlijentaDTOS);
//            m.addObject("klijentiDTO", klijentiDTO);
//            m.addObject("listaDrzava", listaDrzava);
//            m.addObject("successMessage", "Imate grešku! Klijent već postoji u bazi!");
//
//            return m;
//        }

        if(result.hasErrors()){

            ModelAndView m = new ModelAndView("redirect:/admin/adminKlijenti");
            m.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
            m.setViewName("admin/adminKlijenti");
            m.addObject("klijentiDTO", klijentiDTO);
            m.addObject("tipKlijentaDTOS", tipKlijentaDTOS);
            m.addObject("listaDrzava", listaDrzava);
            m.addObject("successMessage", "Imate grešku!");

            return m;
        }
        if (!result.hasErrors()) {

                ModelAndView m = new ModelAndView("redirect:/admin/adminKlijenti");
                redirectAttributes.addFlashAttribute("userName", userDTO.getName() + " " + userDTO.getLastName());
                redirectAttributes.addFlashAttribute("successMessage", "Klijent je uspešno sačuvan! Možete dodati jos klijenata.");


               String promoKod = promoKodGen.checkPromoKod();
               klijentiDTO.setPromoKod(promoKod);


                Klijenti p = klijentiService.findByName(klijentiDTO.getName());
                if(p!=null){
                    klijentiDTO.setSlikaKlijenta(p.getSlikaKlijenta());
                }
                if (fileUpload != null) {
                    for (MultipartFile aFile : fileUpload) {
                        if (aFile.isEmpty()) break;
                        klijentiDTO.setSlikaKlijenta(aFile.getBytes());
                    }
                }

                Klijenti klijenti = klijentiMapper.convertToEntity(klijentiDTO);
                userService.save(klijenti);

                return m;
            }
        return new ModelAndView("redirect:/admin/adminKlijenti");
    }

    @RequestMapping(value = {"/admin/snimiTipKlijenta"}, method = RequestMethod.POST)
    public ModelAndView snimiTipKlijenta(@Valid @ModelAttribute TipKlijentaDTO tipKlijentaDTO,
                                          BindingResult result,
                                          RedirectAttributes redirectAttributes) throws ParseException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        if(result.hasErrors()){
            ModelAndView m = new ModelAndView("redirect:/admin/tipKlijenta");
            m.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
            m.setViewName("admin/tipKlijenta");
            m.addObject("tipKlijentaDTO", tipKlijentaDTO);
            m.addObject("successMessage", "Imate grešku!");

            return m;
        }
        if (!result.hasErrors()) {
            ModelAndView m = new ModelAndView("redirect:/admin/tipKlijenta");
            redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
            redirectAttributes.addFlashAttribute("successMessage", "TipKlijenta je uspešno sačuvan! Možete dodati jos!");
            TipKlijenta tipKlijenta = tipKlijentaMapper.convertToEntity(tipKlijentaDTO);
            tipKlijentaService.save(tipKlijenta);
            return m;
        }

        return new ModelAndView("redirect:/admin/tipKlijenta");
    }


    @RequestMapping(value = {"/admin/adminKlijentiLista"}, method = RequestMethod.GET)
    public ModelAndView adminKlijentiLista(@RequestParam("pageSize") Optional<Integer> pageSize,
                                           @RequestParam("page") Optional<Integer> page) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<Klijenti> klijentis = klijentiPageService.findAllPageable(new PageRequest(evalPage, evalPageSize));
        List<KlijentiDTO> klijentiDTOS = klijentiMapper.convertListToDto(klijentis.getContent());
        Pager pager = new Pager(klijentis.getTotalPages(), klijentis.getNumber(), BUTTONS_TO_SHOW);

        if(klijentiDTOS.isEmpty()){

            ModelAndView m = new ModelAndView("redirect:/admin/adminKorisnici");
            m.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
            m.addObject("klijentis",klijentis);
            m.setViewName("admin/adminKlijentiListaBlanko");
            return m;
        }

        ModelAndView m = new ModelAndView("redirect:/admin/adminKorisnici");
        m.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        m.addObject("klijentis",klijentis);
        m.addObject("selectedPageSize", evalPageSize);
        m.addObject("pageSizes", PAGE_SIZES);
        m.addObject("pager", pager);
        m.setViewName("admin/adminKlijentiLista");
        return m;
    }


    @RequestMapping(value = {"/admin/adminKorisnici"}, method = RequestMethod.GET)
    public ModelAndView adminKorisnici(@RequestParam("pageSize") Optional<Integer> pageSize,
                                        @RequestParam("page") Optional<Integer> page) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);
        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        Page<User> lista = userPageService.findAllPageable(new PageRequest(evalPage, evalPageSize));
        List<UserDTO> userDTOList = userMapper.convertListToDto(lista.getContent());

        Pager pager = new Pager(lista.getTotalPages(), lista.getNumber(), BUTTONS_TO_SHOW);

        if(userDTOList.isEmpty()){
            ModelAndView m = new ModelAndView("redirect:/admin/adminKorisnici");
            m.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
            m.addObject("lista",lista);
            m.setViewName("admin/adminKorisniciBlanko");
            return m;
        }

        ModelAndView m = new ModelAndView("redirect:/admin/adminKorisnici");
        m.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
        m.addObject("lista",lista);
        m.addObject("selectedPageSize", evalPageSize);
        m.addObject("pageSizes", PAGE_SIZES);
        m.addObject("pager", pager);
        m.setViewName("admin/adminKorisnici");
        return m;
    }


    @RequestMapping(value = "/admin/aktivirajKorisnika/{id}", method = RequestMethod.GET)
    public ModelAndView aktivirajKlienta(@PathVariable Long id, RedirectAttributes redirectAttributes) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);


        User u = userService.findById(id);
        u.setActive(true);
        u.setStatus("Aktivan");
        userService.updateUser(u);

        ModelAndView m = new ModelAndView("redirect:/admin/adminKorisnici");
        redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
        redirectAttributes.addFlashAttribute("admin/adminKorisnici");
        return m;

    }

    @RequestMapping(value = "/admin/deaktivirajKorisnika/{id}", method = RequestMethod.GET)
    public ModelAndView deaktivirajKorisnika(@PathVariable Long id, RedirectAttributes redirectAttributes) throws IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        User u = userService.findById(id);
        u.setActive(false);
        u.setStatus("Neaktivan");
        userService.updateUser(u);

        ModelAndView m = new ModelAndView("redirect:/admin/adminKorisnici");
        redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
        redirectAttributes.addFlashAttribute("admin/adminKorisnici");
        return m;
    }


    @RequestMapping(value = {"/access-denied"}, method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("access-denied");
        modelAndView.addObject("poruka", "GRESKA U LOGOVANJU");
        return modelAndView;
    }

    @RequestMapping(value = {"/admin/adminPodesavanja"}, method = RequestMethod.GET)
    public ModelAndView adminPodesavanja() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        List<Drzave> listaDrzava = drzavaService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/adminPodesavanja");
        modelAndView.addObject("user",userDTO);
        modelAndView.addObject("listaDrzava", listaDrzava);
        modelAndView.addObject("userName",user.getName() + " " + user.getLastName());
        return modelAndView;
    }

    @RequestMapping(value = "/admin/adminPodesavanja", method = RequestMethod.POST)
    public ModelAndView updateAdmin(@Valid @ModelAttribute User user,
                                    BindingResult result,
                                    @RequestParam MultipartFile[] fileUpload) throws IOException {

        List<Drzave> listaDrzava = drzavaService.findAll();
        if(!result.hasErrors()){
            ModelAndView m = new ModelAndView("redirect:/admin/adminPodesavanja");
            m.addObject("userName",user.getName() + " " + user.getLastName());
            m.setViewName("admin/adminPodesavanja");
            m.addObject("listaDrzava", listaDrzava);

            User u = userService.findUserByEmail(user.getEmail());
            u.setAdresa(user.getAdresa());
            u.setBrVozackeDozvole(user.getBrVozackeDozvole());
            u.setDrzava(user.getDrzava());
            u.setEmail(user.getEmail());
            u.setKategorijaDozvole(user.getKategorijaDozvole());
            u.setPostanskiBroj(user.getPostanskiBroj());
            u.setTelefon(user.getTelefon());
            u.setVozackaDozvolaVaziDo(user.getVozackaDozvolaVaziDo());
            if (u.getPassword().equals(user.getPassword())){
                userService.updateUser(u);
            } else {
                u.setPassword(user.getPassword());
                userService.saveAdmin(u);
            }
            if (fileUpload != null && fileUpload.length > 0) {
                for (MultipartFile aFile : fileUpload){
                    if (aFile.isEmpty()) break;
                    u.setAdresa(user.getAdresa());
                    u.setBrVozackeDozvole(user.getBrVozackeDozvole());
                    u.setDrzava(user.getDrzava());
                    u.setEmail(user.getEmail());
                    u.setKategorijaDozvole(user.getKategorijaDozvole());
                    u.setPostanskiBroj(user.getPostanskiBroj());
                    u.setTelefon(user.getTelefon());
                    u.setVozackaDozvolaVaziDo(user.getVozackaDozvolaVaziDo());
                    u.setSlikaUser(aFile.getBytes());
                    if (u.getPassword().equals(user.getPassword())){
                        userService.updateUser(u);
                    } else {
                        u.setPassword(user.getPassword());
                        userService.saveAdmin(u);
                    }
                }
            }
            m.addObject("successMessage", "Izmene uspešno sačuvane!");
            return m;
        }

        if(result.hasErrors()){
            ModelAndView m = new ModelAndView("redirect:/admin/adminPodesavanja");
            m.addObject("userName",user.getName() + " " + user.getLastName());
            m.setViewName("/admin/adminPodesavanja");
            m.addObject("listaDrzava", listaDrzava);
            m.addObject("successMessage", "Imate gresku");
            return m;
        }

        return new ModelAndView("redirect:/admin/adminPodesavanja");
    }

    @RequestMapping(value = "/admin/obrisiKlienta/{id}", method = RequestMethod.GET)
    public ModelAndView obrisiKlienta(@PathVariable Long id, RedirectAttributes redirectAttributes){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);


        klijentiService.deleteKlientiById(id);

        ModelAndView m = new ModelAndView("redirect:/admin/adminKlijentiLista");
        redirectAttributes.addFlashAttribute("userName",user.getName() + " " + user.getLastName());
        redirectAttributes.addFlashAttribute("successMessage", "Klijent uspesno obrisan");
        return m;

    }

    @RequestMapping(value = "/admin/imageAdmin", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE} )
    public ResponseEntity<byte[]> getImageAdmin() throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);
        byte[] imageContent = userDTO.getSlikaUser();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/imageUsera/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE} )
    public ResponseEntity<byte[]> getImageUsera(@PathVariable Long id) throws IOException {
        User users = userService.findUserById(id);
        UserDTO userDTO = userMapper.convertToDto(users);
        byte[] imageContent = userDTO.getSlikaUser();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/stampaPDF", method = RequestMethod.GET)
    public ModelAndView stampa (){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        List<PutnikSvedok> putnikSvedok = putnikSvedokService.findPutnikSvedokAll();
        return new ModelAndView(new PDFView(),"promenljiva",putnikSvedok);
    }

    @RequestMapping(value = "/admin/stampaPDF/manja/{id}", method = RequestMethod.GET)
    public ModelAndView stampaManja (@PathVariable Long id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        VozacNezgodaManja manjaIzv = vozacNezIzvService.findManjaById(id);
        return new ModelAndView(new ManjaPDF(),"manja",manjaIzv);
    }

    @RequestMapping(value = "/admin/stampaPDF/veca/{id}", method = RequestMethod.GET)
    public ModelAndView stampaVeca (@PathVariable Long id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        VozacNezgodaVeca vecaIzv = vozacNezIzvService.findVecaById(id);
        return new ModelAndView(new VecaPDF(),"veca",vecaIzv);
    }

    @RequestMapping(value = "/admin/stampaPDF/putnik/{id}", method = RequestMethod.GET)
    public ModelAndView stampaPutnik (@PathVariable Long id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        UserDTO userDTO = userMapper.convertToDto(user);

        PutnikIzvestaj putnikIzv = putnikIzvestajService.findById(id);
        return new ModelAndView(new PutnikPDF(),"putnik",putnikIzv);
    }

}
