package com.example.controller.rest;


import com.example.dto.PutnikIzvestajDTO;
import com.example.dto.PutnikNezgodaDTO;
import com.example.dto.PutnikSvedokDTO;
import com.example.dto.UserDTO;
import com.example.mapper.PutnikIzvestajMapper;
import com.example.mapper.PutnikNezgodaMapper;
import com.example.mapper.PutnikSvedokMapper;
import com.example.mapper.UserMapper;
import com.example.model.*;
import com.example.service.*;
import com.example.util.ApiResult;
import com.example.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.Base64;

@RestController
public class PutnikIzvestajRest {


    @Autowired
    private PutnikIzvestajService putnikIzvestajService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PutnikSvedokService svedokService;

    @Autowired
    private PutnikSvedokMapper putnikSvedokMapper;

    @Autowired
    private PutnikIzvestajMapper putnikIzvestajMapper;

    @Autowired
    private PutnikNezgodaService putnikNezgodaService;

    @Autowired
    private PutnikNezgodaMapper putnikNezgodaMapper;

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

    @Autowired
    private UserService userService;

    @GetMapping(value = "/rest-api/putnikIzvestaj")
    @ResponseBody
    public ApiResult napraviPutnikIzv( HttpServletRequest request) throws ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        PutnikIzvestaj izvestaj = new PutnikIzvestaj();
        /*User user = userMapper.convertToEntity(userDTO);
        izvestaj.setUser(user);*/

        izvestaj = putnikIzvestajService.save(izvestaj);
        izvestaj = putnikIzvestajService.findById(izvestaj.getId());
        izvestaj.setUser(userMapper.convertToEntity(userDTO));
        putnikIzvestajService.save(izvestaj);
/*        VozacNezgodaIzvestaj nezgodaVeca = vecaService.findbyId(izvestaj.getId());

        nezgodaVeca.setUser(user);*/
        // nezgodaVeca = vecaService.save(nezgodaVeca);
        return  new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.OK.value(), null);
    }


    @PostMapping(value = "/rest-api/putnikIzv/dodajSvedokPutnik")
    @ResponseBody
    public ApiResult usnimiPutnikSvedok(@RequestBody  @Valid PutnikSvedokDTO putnikSvedokDTO,
                                        BindingResult result,
                                        HttpServletRequest request) throws ParseException{

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        if (userDTO == null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
            return apiResult;
        }
        if (!result.hasErrors()) {

            PutnikIzvestaj izvestaj = putnikIzvestajService.
                    topPutnikIzvestaj(userMapper.convertToEntity(userDTO));
            PutnikIzvestajDTO izvestajDTO = putnikIzvestajMapper.convertToDto(izvestaj);
            putnikSvedokDTO.setPutnikIzvestajDTO(izvestajDTO);
            if (!putnikSvedokDTO.getFotoLicneKarte().equals("")){
                byte[] decodedBytes = Base64.getDecoder().decode(putnikSvedokDTO.getFotoLicneKarte());
                putnikSvedokDTO.setFotografijaLicneKarte(decodedBytes);
            }

            svedokService.savePutnikSvedok(putnikSvedokMapper.convertToEntity(putnikSvedokDTO));

            return new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), validationUtil.getValidateErrors(result).toString());
        return apiResult;
    }

    @PostMapping(value = "/rest-api/putnikIzv/snimiVremeNezPutnik")
    @ResponseBody
    public ApiResult usnimiPutnikNezgoda(@RequestBody @Valid PutnikNezgodaDTO putnikNezgodaDTO,
                                         BindingResult result,
                                         HttpServletRequest request) throws ParseException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        if (userDTO == null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
            return apiResult;
        }
        if (!result.hasErrors()) {

            PutnikNezgoda putnikNezgoda = putnikNezgodaService.savePutnikNezgoda(
                    putnikNezgodaMapper.convertToEntity(putnikNezgodaDTO));

            PutnikIzvestaj izvestaj = putnikIzvestajService.topPutnikIzvestaj(userMapper.convertToEntity(userDTO));
            izvestaj.setPutnikNezgoda(putnikNezgoda);
            putnikIzvestajService.save(izvestaj);


            return new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), validationUtil.getValidateErrors(result).toString());
        return apiResult;
    }

    @PostMapping(value = "/rest-api/savePutnikIzv")
    @ResponseBody
    public ApiResult savePutnikIzv(@RequestBody @Valid PutnikIzvestajDTO putnikIzvDTO,
                                   BindingResult result, HttpServletRequest request) throws ParseException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        if (userDTO == null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
            return apiResult;
        }
        if (!result.hasErrors()) {

            User user = userMapper.convertToEntity(userDTO);

            Osiguranje osiguranje = osiguranjeService.findOsiguranjeByUser(user);
            UgovaracOsiguranja ugcOsiguranja = ugOsigService.findUgovaracOsiguranjaByUser(user);
            Vozilo vozilo = voziloService.findVoziloByUser(user);
            Prikolica prikolica = prikolicaService.findPrikolicaByUser(user);

            PutnikNezgodaDTO nezgodaDTO = putnikIzvDTO.getPutnikNezgoda();
            nezgodaDTO.setUser(userDTO);
            PutnikNezgoda nezgoda = putnikNezgodaMapper.convertToEntity(nezgodaDTO);




            nezgoda = putnikNezgodaService.savePutnikNezgoda(nezgoda);

            nezgodaDTO = putnikNezgodaMapper.convertToDto(nezgoda);

            putnikIzvDTO.setPutnikNezgoda(nezgodaDTO);
            putnikIzvDTO.setUser(userDTO);
            putnikIzvDTO.setPoslat(false);
            PutnikIzvestaj putnikIzvestaj = putnikIzvestajMapper.convertToEntity(putnikIzvDTO);

            putnikIzvestaj.setNazivOsiguranja(osiguranje.getNaziv());
            putnikIzvestaj.setBrojUgovoraOsiguranja(osiguranje.getBrojUgovora());
            putnikIzvestaj.setBrojZelenogKartona(osiguranje.getBrojZelenogKartona());
            putnikIzvestaj.setPolisaVaziOd(osiguranje.getPolisaVaziOd());
            putnikIzvestaj.setPolisaVaziDo(osiguranje.getPolisaVaziDo());
            putnikIzvestaj.setZeleniKartonVaziOd(osiguranje.getZeleniKartonVaziOd());
            putnikIzvestaj.setZeleniKartonVaziDo(osiguranje.getZeleniKartonVaziDo());
            putnikIzvestaj.setFilijala(osiguranje.getFilijala());
            putnikIzvestaj.setDrzava(osiguranje.getDrzava());

            putnikIzvestaj.setImePrezimeUO(ugcOsiguranja.getImePrezimeUO());
            putnikIzvestaj.setAdresaUO(ugcOsiguranja.getAdresaUO());
            putnikIzvestaj.setPostanskiBrojUO(ugcOsiguranja.getPostanskiBrojUO());
            putnikIzvestaj.setTelefonUO(ugcOsiguranja.getTelefonUO());
            putnikIzvestaj.setMailUO(ugcOsiguranja.getMailUO());

            putnikIzvestaj.setMarkaVozila(vozilo.getMarka());
            putnikIzvestaj.setTipVozila(vozilo.getTip());
            putnikIzvestaj.setRegOznakaVoz(vozilo.getRegistarskaOznakaVO());
            putnikIzvestaj.setDrzavaRegVozila(vozilo.getDrzavaUKojojJeVoziloRegistrovano());

            putnikIzvestaj.setRegOznakaPr(prikolica.getRegistarskaOznaka());
            putnikIzvestaj.setMaxTezinaPr(prikolica.getMaksimalnaDozvoljenaTezina());
            putnikIzvestaj.setDrRegPrikolica(prikolica.getDrzavaUKojojJeRegistrovana());

            putnikIzvestaj = putnikIzvestajService.save(putnikIzvestaj);


            for (PutnikSvedok svedok : putnikIzvestaj.getPutnikSvedok()) {
                PutnikSvedok putnikSvedok = svedok;
                putnikSvedok.setPutnikIzvestaj(putnikIzvestaj);
                svedokService.savePutnikSvedok(putnikSvedok);
            }


            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;

        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), validationUtil.getValidateErrors(result).toString());
        return apiResult;
    }

}
