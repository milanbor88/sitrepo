package com.example.controller.rest;


import com.example.dto.*;
import com.example.mapper.*;
import com.example.model.*;
import com.example.service.*;
import com.example.util.ApiResult;
import com.example.util.JsonPdfResult;
import com.example.util.UserDataMobile;
import com.example.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.Base64;

@RestController
public class IzvestajNezgodaVecaRest {

    @Autowired
    VozacNezIzvService vecaService;

    @Autowired
    private VozacSvedokService vozacSvedokService;


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VozacNezgodaService vozacNezgodaService;

    @Autowired
    private VozacNezgodaMapper vozacNezgodaMapper;

    @Autowired
    private VozacNezgodaVecaMapper vecaMapper;

    @Autowired
    private VozacSvedokMapper vozacSvedokMapper;

    @Autowired
    private VozacNezgodaIzvestajMapper izvestajMapper;

    @Autowired
    private PovredjeniDrugoVoziloService drugoVoziloService;

    @Autowired
    private PovredjeniDrugoVoziloMapper drugoVoziloMapper;

    @Autowired
    private PovredjeniVanVozilaService vanVozilaService;

    @Autowired
    private PovredjeniVanVozilaMapper vanVozilaMapper;

    @Autowired
    private PovredjeniVaseVoziloService vaseVoziloService;

    @Autowired
    private PovredjeniVaseVoziloMapper vaseVoziloMapper;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ValidationUtil validationUtil;

    @Autowired
    private OsiguranjeService osiguranjeService;

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private UgovaracOsiguranjaService ugOsigService;

    @Autowired
    private PrikolicaService prikolicaService;

/*
    @GetMapping(value = "/rest-api/vecaNezgoda/stampaj/{idIzv}")
    @ResponseBody
    public ApiResult stampajIzv(@PathVariable Long idIzv,
                                HttpServletRequest request) throws IOException, MessagingException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        VozacNezgodaIzvestaj  veca = vecaService.findbyId(idIzv);
        VozacNezgodaIzvestajDTO vecaDTO = izvestajMapper.convertToDto(veca);
        emailService.sendIzvVeca(vecaDTO, "duljarevic@gmail.com");
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.OK.value(), null);
        return apiResult;
    }
*/



   /* @PostMapping(value = "/rest-api/vecaNezgoda/snimiSvedoka")
    @ResponseBody
    public ApiResult snimiSvedokaNezgodaVeca(@RequestBody @Valid VozacSvedokDTO svedokDTO,
                                             BindingResult result,
                                             HttpServletRequest request) throws IOException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        if (userDTO == null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
            return apiResult;
        }
        if (!result.hasErrors()) {

            VozacNezgodaIzvestaj izvestajVeca = vecaService.findTopIzvestakByUser(userMapper.convertToEntity(userDTO));
            VozacNezgodaIzvestajDTO vecaDTO = izvestajMapper.convertToDto(izvestajVeca);
            svedokDTO.setVozacNezgodaIzvestaj(vecaDTO);
            if (svedokDTO.getFotoLicneKarte() != null){
                byte[] decodedBytes = Base64.getDecoder().decode(svedokDTO.getFotoLicneKarte());
                svedokDTO.setFotografijaLicneKarte(decodedBytes);
            }
            vozacSvedokService.saveVozacSvedok(vozacSvedokMapper.convertToEntity(svedokDTO));


            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;

        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), validationUtil.getValidateErrors(result).toString());
        return apiResult;
    }

    @PostMapping(value = "/rest-api/vecaNezgoda/snimiDrugoVeca")
    @ResponseBody
    public ApiResult snimiDrugoVeca(@RequestBody @Valid PovredjeniDrugoVoziloDTO drugoVoziloDTO,
                                    BindingResult result,
                                    HttpServletRequest request) throws IOException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        if (userDTO == null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
            return apiResult;
        }
        if (!result.hasErrors()) {
            VozacNezgodaIzvestaj izvestajVeca = vecaService.findTopIzvestakByUser(userMapper.convertToEntity(userDTO));
            VozacNezgodaIzvestajDTO vecaDTO = izvestajMapper.convertToDto(izvestajVeca);
            drugoVoziloDTO.setVozacNezgodaIzvestaj(vecaDTO);

            drugoVoziloService.savePovredjeniDrugoVozilo(
                    drugoVoziloMapper.convertToEntity(drugoVoziloDTO));
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;

        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), validationUtil.getValidateErrors(result).toString());
        return apiResult;
    }


    @PostMapping(value = "/rest-api/vecaNezgoda/snimiVanVeca")
    @ResponseBody
    public ApiResult snimiVanVeca(@RequestBody @Valid PovredjeniVanVozilaDTO vanVozilaDTO,
                                  BindingResult result,
                                  HttpServletRequest request) throws IOException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        if (userDTO == null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
            return apiResult;
        }
        if (!result.hasErrors()) {
            VozacNezgodaIzvestaj izvestajVeca = vecaService.findTopIzvestakByUser(userMapper.convertToEntity(userDTO));
            VozacNezgodaIzvestajDTO vecaDTO = izvestajMapper.convertToDto(izvestajVeca);
            vanVozilaDTO.setVozacNezgodaIzvestaj(vecaDTO);

            vanVozilaService.savePovredjeniVanVozila(
                    vanVozilaMapper.convertToEntity(vanVozilaDTO)
            );
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;

        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), validationUtil.getValidateErrors(result).toString());
        return apiResult;
    }

    @PostMapping(value = "/rest-api/vecaNezgoda/snimiVaseVeca")
    @ResponseBody
    public ApiResult snimiVaseVeca(@RequestBody @Valid PovredjeniVaseVoziloDTO vaseVoziloDTO,
                                   BindingResult result,
                                   HttpServletRequest request) throws IOException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        if (userDTO == null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
            return apiResult;
        }
        if (!result.hasErrors()) {
            VozacNezgodaIzvestaj izvestajVeca = vecaService.findTopIzvestakByUser(userMapper.convertToEntity(userDTO));
            VozacNezgodaIzvestajDTO vecaDTO = izvestajMapper.convertToDto(izvestajVeca);
            vaseVoziloDTO.setVozacNezgodaIzvestaj(vecaDTO);

            vaseVoziloService.savePovredjeniVaseVozilo(
                    vaseVoziloMapper.convertToEntity(vaseVoziloDTO)
            );
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;

        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), validationUtil.getValidateErrors(result).toString());
        return apiResult;
    }

    @PostMapping(value = "/rest-api/vecaNezgoda/snimiVremeNezVeca")
    @ResponseBody
    public ApiResult snimiVremeNezgodaVeca(@RequestBody @Valid VozacNezgodaDTO vozacNezgodaDTO,
                                           BindingResult result,
                                           HttpServletRequest request) throws IOException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        if (userDTO == null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
            return apiResult;
        }
        if (!result.hasErrors()) {

            VozacNezgoda vozacNezgoda = vozacNezgodaService.saveVozacNezgoda(
                    vozacNezgodaMapper.convertToEntity(vozacNezgodaDTO));

            VozacNezgodaVeca izvestajVeca = vecaService.topveca(userMapper.convertToEntity(userDTO));
            izvestajVeca.setVozacNezgoda(vozacNezgoda);

          *//*  VozacNezgodaVecaDTO vecaDTO = vecaMapper.convertToDto(izvestajVeca);

           vozacNezgodaDTO = vozacNezgodaMapper.convertToDto(vozacNezgoda);*//*
            //   VozacNezgodaVeca izvestaj = vecaService.findVecaById(idIzvestaja);
            //   vecaDTO.setVozacNezgodaDTO(vozacNezgodaDTO);
            // izvestajVeca = vecaMapper.convertToEntity(vecaDTO);
            vecaService.save(izvestajVeca);

            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;

        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), validationUtil.getValidateErrors(result).toString());
        return apiResult;
    }

    @GetMapping(value = "/rest-api/vozacVeca")
    @ResponseBody
    public ApiResult usnimiVecaVozac( HttpServletRequest request) throws ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        VozacNezgodaIzvestaj izvestaj = new VozacNezgodaVeca();

        izvestaj = vecaService.save(izvestaj);
        izvestaj = vecaService.findbyId(izvestaj.getId());
        izvestaj.setUser(userMapper.convertToEntity(userDTO));
        izvestaj = vecaService.save(izvestaj);

        return  new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.OK.value(), null);
    }*/

    @PostMapping(value = "/rest-api/saveVozacVeca")
    @ResponseBody
    public ApiResult saveVecaNezgoda(@RequestBody @Valid VozacNezgodaVecaDTO vecaDTO,
                                     BindingResult result,HttpServletRequest request) throws ParseException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        if (userDTO == null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
            return apiResult;
        }
        if (!result.hasErrors()) {

            User user = userMapper.convertToEntity(userDTO);

            VozacNezgodaDTO nezgodaDTO = vecaDTO.getVozacNezgoda();
            nezgodaDTO.setUser(userDTO);
            VozacNezgoda nezgoda = vozacNezgodaMapper.convertToEntity(nezgodaDTO);

            nezgoda = vozacNezgodaService.saveVozacNezgoda(nezgoda);

            nezgodaDTO = vozacNezgodaMapper.convertToDto(nezgoda);

            Osiguranje osiguranje = osiguranjeService.findOsiguranjeByUser(user);
            UgovaracOsiguranja ugcOsiguranja = ugOsigService.findUgovaracOsiguranjaByUser(user);
            Vozilo vozilo = voziloService.findVoziloByUser(user);
            Prikolica prikolica = prikolicaService.findPrikolicaByUser(user);

            vecaDTO.setVozacNezgoda(nezgodaDTO);
            vecaDTO.setUser(userDTO);
            vecaDTO.setPoslat(false);
            VozacNezgodaVeca veca = vecaMapper.convertToEntity(vecaDTO);
            veca.setNazivOsiguranja(osiguranje.getNaziv());
            veca.setBrojUgovoraOsiguranja(osiguranje.getBrojUgovora());
            veca.setBrojZelenogKartona(osiguranje.getBrojZelenogKartona());
            veca.setPolisaVaziOd(osiguranje.getPolisaVaziOd());
            veca.setPolisaVaziDo(osiguranje.getPolisaVaziDo());
            veca.setZeleniKartonVaziOd(osiguranje.getZeleniKartonVaziOd());
            veca.setZeleniKartonVaziDo(osiguranje.getZeleniKartonVaziDo());
            veca.setFilijala(osiguranje.getFilijala());
            veca.setDrzava(osiguranje.getDrzava());

            veca.setImePrezimeUO(ugcOsiguranja.getImePrezimeUO());
            veca.setAdresaUO(ugcOsiguranja.getAdresaUO());
            veca.setPostanskiBrojUO(ugcOsiguranja.getPostanskiBrojUO());
            veca.setTelefonUO(ugcOsiguranja.getTelefonUO());
            veca.setMailUO(ugcOsiguranja.getMailUO());

            veca.setMarkaVozila(vozilo.getMarka());
            veca.setTipVozila(vozilo.getTip());
            veca.setRegOznakaVoz(vozilo.getRegistarskaOznakaVO());
            veca.setDrzavaRegVozila(vozilo.getDrzavaUKojojJeVoziloRegistrovano());

            veca.setRegOznakaPr(prikolica.getRegistarskaOznaka());
            veca.setMaxTezinaPr(prikolica.getMaksimalnaDozvoljenaTezina());
            veca.setDrRegPrikolica(prikolica.getDrzavaUKojojJeRegistrovana());

            veca = vecaService.saveVeca(veca);

            for (PovredjeniVaseVozilo pov : veca.getPovredjeniVaseVozilo()) {
                PovredjeniVaseVozilo vaseVozilo = pov;
                vaseVozilo.setVozacNezgodaIzvestaj(veca);
                vaseVoziloService.savePovredjeniVaseVozilo(vaseVozilo);
            }

            for (PovredjeniVanVozila van : veca.getPovredjeniVanVozila()) {
                PovredjeniVanVozila vanVozila = van;
                vanVozila.setVozacNezgodaIzvestaj(veca);
                vanVozilaService.savePovredjeniVanVozila(vanVozila);
            }

            for (PovredjeniDrugoVozilo drugo : veca.getPovredjeniDrugoVozilo()) {
                PovredjeniDrugoVozilo drugoVozilo = drugo;
                drugoVozilo.setVozacNezgodaIzvestaj(veca);
                drugoVoziloService.savePovredjeniDrugoVozilo(drugoVozilo);
            }

            for (VozacSvedok svedok : veca.getVozacSvedok()) {
                VozacSvedok vozacSvedok = svedok;
                vozacSvedok.setVozacNezgodaIzvestaj(veca);
                vozacSvedokService.saveVozacSvedok(vozacSvedok);
            }

            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;

        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), validationUtil.getValidateErrors(result).toString());
        return apiResult;

    }

    @GetMapping(value = "/rest-api/generatePDFVeca/{id}")
    @ResponseBody
    public JsonPdfResult genPFGJSOn(@PathVariable(value = "id") Long id,
                                    HttpServletRequest request) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        JsonPdfResult result = new JsonPdfResult();
        if (userDTO == null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
            result.setApiResult(apiResult);
            return result;
        }

                VozacNezgodaVeca veca = vecaService.findVecaById(id);
                byte[] response = emailService.getPDFReport(vecaMapper.convertToDto(veca));

                String encoded = Base64.getEncoder().encodeToString(response);

                ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.OK.value(), null);
                result.setApiResult(apiResult);
                result.setPdfString(encoded);

                return result;
    }




/*
    @PostMapping(value = "/rest-api/dodajSvedokVozacVeca/{idIzvestaja}")
    @ResponseBody
    public ApiResult usnimiVozac(@RequestBody VozacSvedok vozacSvedok,
                                 @PathVariable(value = "idIzvestaja") long idIzvestaja,
                                 HttpServletRequest request) {


        VozacNezgodaIzvestaj  izvestaj = vecaService.findbyId(idIzvestaja);

        vozacSvedok.setVozacNezgodaIzvestaj(izvestaj);
        vozacSvedokService.saveVozacSvedok(vozacSvedok);

        return  new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.OK.value(), null);
    }


    @PostMapping(value = "/rest-api/dodajVozacNezgoda/{idIzvestaja}")
    @ResponseBody
    public ApiResult usnimiVozacNezgoda(@RequestBody VozacNezgoda vozacNezgoda,
                                        @PathVariable(value = "idIzvestaja") long idIzvestaja,
                                        HttpServletRequest request) {

  *//*      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();*//*

        vozacNezgoda = vozacNezgodaService.saveVozacNezgoda(vozacNezgoda);

        VozacNezgodaVeca izvestaj = vecaService.findVecaById(idIzvestaja);
        izvestaj.setVozacNezgoda(vozacNezgoda);
        vecaService.saveVeca(izvestaj);


        return  new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.OK.value(), null);

    }*/
}
