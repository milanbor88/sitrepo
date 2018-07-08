package com.example.controller.rest;

import com.example.dto.*;
import com.example.mapper.*;
import com.example.model.*;
import com.example.service.*;
import com.example.util.ApiResult;
import com.example.util.ValidationUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.Base64;

@RestController
@Api(value = "SitRepo", description = "Operations for IzvestajNezgodaManjaRest in SitRepo",
        basePath = "/rest-api/manjaNezgoda/")
public class IzvestajNezgodaManjaRest {


    @Autowired
    VozacNezIzvService manjaService;

    @Autowired
    VozacSvedokService vozacSvedokService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    VozacNezgodaManjaMapper manjaMapper;

    @Autowired
    VozacSvedokMapper vozacSvedokMapper;

    @Autowired
    VozacNezgodaIzvestajMapper izvestajMapper;

    @Autowired
    PovredjeniDrugoVoziloService drugoVoziloService;

    @Autowired
    PovredjeniDrugoVoziloMapper drugoVoziloMapper;

    @Autowired
    PovredjeniVanVozilaService vanVozilaService;

    @Autowired
    PovredjeniVanVozilaMapper vanVozilaMapper;

    @Autowired
    PovredjeniVaseVoziloService vaseVoziloService;

    @Autowired
    PovredjeniVaseVoziloMapper vaseVoziloMapper;

    @Autowired
    EvropskiIzvestajIDokaziService dokaziService;

    @Autowired
    EvropskiIzvestajIDokaziMapper dokaziMapper;

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


 /*   @ApiOperation(value = "Servis za snimanje svedoka kod manje nezgode" , response = ApiResult.class, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST")
    @PostMapping(value = "/rest-api/manjaNezgoda/snimiSvedoka")
    @ResponseBody
    public ApiResult snimiSvedokaNezgodaManja(@RequestBody @Valid VozacSvedokDTO svedokDTO,
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

            VozacNezgodaIzvestaj izvestajVeca = manjaService.findTopIzvestakByUser(userMapper.convertToEntity(userDTO));
            VozacNezgodaIzvestajDTO vecaDTO = izvestajMapper.convertToDto(izvestajVeca);
            svedokDTO.setVozacNezgodaIzvestaj(vecaDTO);
            if (svedokDTO.getFotoLicneKarte()!= null){
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

    @ApiOperation(value = "Servis za snimanje povredjenih lica u drugom vozilu za manju nezgodu" , response = ApiResult.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST")
    @PostMapping(value = "/rest-api/manjaNezgoda/snimiDrugoManja")
    @ResponseBody
    public ApiResult snimiDrugoManja(@RequestBody @Valid PovredjeniDrugoVoziloDTO drugoVoziloDTO,
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
            VozacNezgodaIzvestaj izvestajVeca = manjaService.findTopIzvestakByUser(userMapper.convertToEntity(userDTO));
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


    @ApiOperation(value = "Servis sa snimanje povredjenih lica van vozila za manju nezgodu" , response = ApiResult.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST")
    @PostMapping(value = "/rest-api/manjaNezgoda/snimiVanManja")
    @ResponseBody
    public ApiResult snimiVanManja(@RequestBody @Valid PovredjeniVanVozilaDTO vanVozilaDTO,
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
            VozacNezgodaIzvestaj izvestajVeca = manjaService.findTopIzvestakByUser(userMapper.convertToEntity(userDTO));
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

    @ApiOperation(value = "Servis za snimanje povredjenih lica u sopsstvenom vozilu za manju nezgodu" , response = ApiResult.class, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST")
    @PostMapping(value = "/rest-api/manjaNezgoda/snimiVaseManja")
    @ResponseBody
    public ApiResult snimiVaseManja(@RequestBody @Valid PovredjeniVaseVoziloDTO vaseVoziloDTO,
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
            VozacNezgodaIzvestaj izvestajVeca = manjaService.findTopIzvestakByUser(userMapper.convertToEntity(userDTO));
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

    @ApiOperation(value = "Servis za snimanje dokaza u manjoj nezgodi" , response = ApiResult.class,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST")
    @PostMapping(value = "/rest-api/manjaNezgoda/snimiEvroDokManja/{dokaz}")
    @ResponseBody
    public ApiResult snimiEvroDokManja(@RequestBody @Valid EvropskiIzvestajIDokaziDTO dokaziDTO,
                                       BindingResult result,
                                       @PathVariable String dokaz,
                                       HttpServletRequest request)
            throws IOException, ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        if (userDTO == null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
            return apiResult;
        }
        if (!result.hasErrors()) {

            VozacNezgodaManja izvestajManja = manjaService.topmanja(userMapper.convertToEntity(userDTO));
            VozacNezgodaManjaDTO manjaDTO = manjaMapper.convertToDto(izvestajManja);
            dokaziDTO.setVozacNezgodaManjaDTO(manjaDTO);
            if (dokaziDTO.getDokazi()!= null) {
                byte[] decodedBytes = Base64.getDecoder().decode(dokaziDTO.getDokazi());
                dokaziDTO.setSlike(decodedBytes);
            }
            if(dokaz.equals("sopstveno")) {
                dokaziDTO.setStatus("1");
            } else if (dokaz.equals("tudje")){
                dokaziDTO.setStatus("2");
            } else if (dokaz.equals("evropski")){
                dokaziDTO.setStatus("3");
            }

            dokaziService.saveEvropskiIzvestajIDokazi(
                    dokaziMapper.convertToEntity(dokaziDTO)
            );


            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;

        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), validationUtil.getValidateErrors(result).toString());
        return apiResult;
    }

    @ApiOperation(value = "Servis za kreiranje izvestaja za manju nezgodu" , response = ApiResult.class, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET")
    @GetMapping(value = "/rest-api/vozacManja")
    @ResponseBody
    public ApiResult usnimiManjaNezgoda( HttpServletRequest request) throws ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        if (userDTO == null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
            return apiResult;
        } else {
            VozacNezgodaIzvestaj izvestaj = new VozacNezgodaManja();


            izvestaj = manjaService.save(izvestaj);
            izvestaj = manjaService.findbyId(izvestaj.getId());
            izvestaj.setUser(userMapper.convertToEntity(userDTO));
            izvestaj = manjaService.save(izvestaj);

            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;
        }
    }*/

    @PostMapping(value = "/rest-api/saveVozacManja")
    @ResponseBody
    public ApiResult saveManjaNezgoda(@RequestBody @Valid VozacNezgodaManjaDTO manjaDTO,
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

            Osiguranje osiguranje = osiguranjeService.findOsiguranjeByUser(user);
            UgovaracOsiguranja ugcOsiguranja = ugOsigService.findUgovaracOsiguranjaByUser(user);
            Vozilo vozilo = voziloService.findVoziloByUser(user);
            Prikolica prikolica = prikolicaService.findPrikolicaByUser(user);

            manjaDTO.setPoslat(false);
            manjaDTO.setUser(userDTO);
            VozacNezgodaManja manja = manjaMapper.convertToEntity(manjaDTO);

            manja.setNazivOsiguranja(osiguranje.getNaziv());
            manja.setBrojUgovoraOsiguranja(osiguranje.getBrojUgovora());
            manja.setBrojZelenogKartona(osiguranje.getBrojZelenogKartona());
            manja.setPolisaVaziOd(osiguranje.getPolisaVaziOd());
            manja.setPolisaVaziDo(osiguranje.getPolisaVaziDo());
            manja.setZeleniKartonVaziOd(osiguranje.getZeleniKartonVaziOd());
            manja.setZeleniKartonVaziDo(osiguranje.getZeleniKartonVaziDo());
            manja.setFilijala(osiguranje.getFilijala());
            manja.setDrzava(osiguranje.getDrzava());

            manja.setImePrezimeUO(ugcOsiguranja.getImePrezimeUO());
            manja.setAdresaUO(ugcOsiguranja.getAdresaUO());
            manja.setPostanskiBrojUO(ugcOsiguranja.getPostanskiBrojUO());
            manja.setTelefonUO(ugcOsiguranja.getTelefonUO());
            manja.setMailUO(ugcOsiguranja.getMailUO());

            manja.setMarkaVozila(vozilo.getMarka());
            manja.setTipVozila(vozilo.getTip());
            manja.setRegOznakaVoz(vozilo.getRegistarskaOznakaVO());
            manja.setDrzavaRegVozila(vozilo.getDrzavaUKojojJeVoziloRegistrovano());

            manja.setRegOznakaPr(prikolica.getRegistarskaOznaka());
            manja.setMaxTezinaPr(prikolica.getMaksimalnaDozvoljenaTezina());
            manja.setDrRegPrikolica(prikolica.getDrzavaUKojojJeRegistrovana());

            manja = manjaService.saveManja(manja);


            if ( manja.getPovredjeniVaseVozilo() != null) {
                for (PovredjeniVaseVozilo pov : manja.getPovredjeniVaseVozilo()) {
                    PovredjeniVaseVozilo vaseVozilo = pov;
                    vaseVozilo.setVozacNezgodaIzvestaj(manja);
                    vaseVoziloService.savePovredjeniVaseVozilo(vaseVozilo);
                }
            }

            if ( manja.getPovredjeniVanVozila() != null) {
                for (PovredjeniVanVozila van : manja.getPovredjeniVanVozila()) {
                    PovredjeniVanVozila vanVozila = van;
                    vanVozila.setVozacNezgodaIzvestaj(manja);
                    vanVozilaService.savePovredjeniVanVozila(vanVozila);
                }
            }

            if ( manja.getPovredjeniDrugoVozilo() != null) {
                for (PovredjeniDrugoVozilo drugo : manja.getPovredjeniDrugoVozilo()) {
                    PovredjeniDrugoVozilo drugoVozilo = drugo;
                    drugoVozilo.setVozacNezgodaIzvestaj(manja);
                    drugoVoziloService.savePovredjeniDrugoVozilo(drugoVozilo);
                }
            }

            if ( manja.getVozacSvedok() != null) {
                for (VozacSvedok svedok : manja.getVozacSvedok()) {
                    VozacSvedok vozacSvedok = svedok;
                    vozacSvedok.setVozacNezgodaIzvestaj(manja);
                    vozacSvedokService.saveVozacSvedok(vozacSvedok);
                }
            }

            if ( manja.getEvropskiIzvestajIDokazis() != null) {
                for (EvropskiIzvestajIDokazi iDokazi : manja.getEvropskiIzvestajIDokazis()) {
                    EvropskiIzvestajIDokazi evropskiIzvestajIDokazi = iDokazi;
                    evropskiIzvestajIDokazi.setVozacNezgodaManja(manja);
                    dokaziService.saveEvropskiIzvestajIDokazi(evropskiIzvestajIDokazi);
                }
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
