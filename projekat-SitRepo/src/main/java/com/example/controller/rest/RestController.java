package com.example.controller.rest;

import com.example.dto.*;
import com.example.exception.ErrorCode;
import com.example.exception.ResourceAlreadyExistException;
import com.example.mapper.*;
import com.example.model.*;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.*;
import com.example.util.*;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpStatus;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@org.springframework.web.bind.annotation.RestController
@Api(value = "REST CONTROLLER",description = "glavni kontorler")
public class RestController {

    private int totalPage=0,currentPage=0;

    @Autowired
    UserService userService;

    @Autowired
    VoziloService voziloService;

    @Autowired
    PrikolicaService prikolicaService;

    @Autowired
    OsiguranjeService osiguranjeService;

    @Autowired
    UgovaracOsiguranjaService ugovaracOsiguranjaService;

    @Autowired
    KlijentiService klijentiService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    EmailService emailService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    OsiguranjeMapper osiguranjeMapper;

    @Autowired
    PrikolicaMapper prikolicaMapper;

    @Autowired
    UgovaracOsiguranjaMapper ugovaracOsiguranjaMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserRoleMapper userRolesMapper;

    @Autowired
    private UserRolesService userRolesService;


    @Autowired
    private VoziloMapper voziloMapper;

    @Autowired
    private ValidationUtil validationUtil;

    @Autowired
    private KlijentiMapper klijentiMapper;

    private VoziloDTO vozDTO = new VoziloDTO();

    private PrikolicaDTO prDTO = new PrikolicaDTO();

    private OsiguranjeDTO osDTO = new OsiguranjeDTO();

    private UgovaracOsiguranjaDTO ugOsDTO = new UgovaracOsiguranjaDTO();


    @PostMapping(value = "/rest-api/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserDataMobile login(@RequestBody @Valid LoginDTO loginDTO,
                                BindingResult result,
                                HttpServletRequest request){

        UserDataMobile dataMobile = new UserDataMobile();
        if (!result.hasErrors()) {

            User user  = userService.findUserByEmail(loginDTO.getUsername());
            if (user != null) {
            if(user.getActive()) {
                    boolean sifraEncde = bCryptPasswordEncoder.matches(loginDTO.getPassword(), user.getPassword());

                    if (sifraEncde) {

                        UserDTO authDTO = userMapper.convertToDto(user);
                        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

                        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

                        Authentication authentication = new UsernamePasswordAuthenticationToken(authDTO, loginDTO.getPassword(), grantedAuthorities);
                        SecurityContext securityContext = SecurityContextHolder.getContext();
                        securityContext.setAuthentication(authentication);

                        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                                HttpStatus.OK.value(), null);

                        Optional<Vozilo> vozilo = Optional.ofNullable(voziloService.findVoziloByUser(user));
                        Optional<Prikolica> prikolica = Optional.ofNullable(prikolicaService.findPrikolicaByUser(user));
                        Optional<Osiguranje> osiguranje = Optional.ofNullable(osiguranjeService.findOsiguranjeByUser(user));
                        Optional<UgovaracOsiguranja> ugovaracOsiguranja = Optional.ofNullable(ugovaracOsiguranjaService.findUgovaracOsiguranjaByUser(user));

                        vozilo.ifPresent(v -> vozDTO = voziloMapper.convertToDto(v));
                        prikolica.ifPresent(p -> prDTO = prikolicaMapper.convertToDto(p));
                        osiguranje.ifPresent(o -> osDTO = osiguranjeMapper.convertToDto(o));
                        ugovaracOsiguranja.ifPresent(uo -> ugOsDTO = ugovaracOsiguranjaMapper.convertToDto(uo));
                        dataMobile.setUser(userMapper.convertToDto(user));
                        dataMobile.setVozilo(vozDTO);
                        dataMobile.setPrikolica(prDTO);
                        dataMobile.setOsiguranje(osDTO);
                        dataMobile.setUgovaracOsiguranja(ugOsDTO);
                        dataMobile.setApiResult(apiResult);


                        return dataMobile;
                    }
                    ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                            HttpStatus.BAD_REQUEST.value(), "pogresna parametri logovanja");
                    dataMobile.setApiResult(apiResult);
                    return dataMobile;
                }
                ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                        HttpStatus.BAD_REQUEST.value(), "profil nije aktiviran");
                dataMobile.setApiResult(apiResult);
                return dataMobile;
            }
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.BAD_REQUEST.value(), "pogresan email" );
            dataMobile.setApiResult(apiResult);
            return dataMobile;
        }

        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), validationUtil.getValidateErrors(result).toString() );
        dataMobile.setApiResult(apiResult);
        return dataMobile;
    }

    @PostMapping(value = "/rest-api/saveOffUserData")
    @ResponseBody
    public ApiResult usnimiMobDataUser(@RequestBody UserDataMobile dataMobile, HttpServletRequest request) throws ParseException, IOException {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO authDTO = (UserDTO) auth.getPrincipal();

        UserDTO userDTO = dataMobile.getUser();

        if (userDTO != null) {

            if (authDTO.getPassword().equals(userDTO.getPassword())){
                userService.updateUser(userMapper.convertToEntity(userDTO));
            } else {
                /*u.setPassword(user.getPassword());*/
                userService.saveUser(userMapper.convertToEntity(userDTO));
            }
            userDTO.setActive(true);
            userService.upUser(userMapper.convertToEntity(userDTO));


            VoziloDTO voziloDTO = dataMobile.getVozilo();
            voziloDTO.setUser(userDTO);

            PrikolicaDTO prikolicaDTO = dataMobile.getPrikolica();
            prikolicaDTO.setUser(userDTO);

            OsiguranjeDTO osiguranjeDTO = dataMobile.getOsiguranje();
            osiguranjeDTO.setUser(userDTO);

            UgovaracOsiguranjaDTO ugovaracOsiguranjaDTO = dataMobile.getUgovaracOsiguranja();
            ugovaracOsiguranjaDTO.setUser(userDTO);

            voziloService.updateVozilo(voziloMapper.convertToEntity(voziloDTO));
            prikolicaService.updatePrikolica(prikolicaMapper.convertToEntity(prikolicaDTO));
            osiguranjeService.updateOsiguranje(osiguranjeMapper.convertToEntity(osiguranjeDTO));
            ugovaracOsiguranjaService.updateUgovarac(ugovaracOsiguranjaMapper.convertToEntity(ugovaracOsiguranjaDTO));

            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;
        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), "neispravan email");
        return apiResult;
    }


    @GetMapping(value = "/rest-api/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        return "korisnik je odjavljen";
    }

    @PostMapping(value = "/rest-api/createUser",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody public ApiResult createUser(@RequestBody @Valid LoginDTO loginDTO,
                                              BindingResult result,
                                              HttpServletRequest request) throws IOException, ParseException, MessagingException {
        if (!result.hasErrors()){
        User nUser = userService.findUserByEmail(loginDTO.getUsername());
        if(nUser != null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.BAD_REQUEST.value(), ErrorCode.ERR_USR_001.getMessageKey());
            return apiResult;
        }


            RoleDTO roleDTO = roleMapper.convertToDto(roleRepository.findByRole("ROLE_USER"));
            //userDTO.se(roleDTO);
/*            //userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
            if (userDTO.getSlika()!=null) {
                byte[] decodedBytes = Base64.getDecoder().decode(userDTO.getSlika());
                userDTO.setSlikaUser(decodedBytes);
            }*/

            UserDTO userDTO = new UserDTO();
            userDTO.setPassword(loginDTO.getPassword());
            userDTO.setEmail(loginDTO.getUsername());
            userDTO.setLastName(loginDTO.getLastName());
            userDTO.setName(loginDTO.getFirstName());

            if(!loginDTO.getPromoKod().equals("")) {
                Klijenti klijenti =klijentiService.findByPromoKod(loginDTO.getPromoKod());

                if (klijenti == null) {
                    ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                            HttpStatus.BAD_REQUEST.value(), "pogresno ste uneli promo kod");
                    return apiResult;
                }
                KlijentiDTO klijentiDTO = klijentiMapper.convertToDto(klijenti);
                userDTO.setKlijentiDTO(klijentiDTO);

            } else {
                KlijentiDTO klijentiDTO = klijentiMapper.convertToDto(klijentiService.findByPromoKod("000000"));
                userDTO.setKlijentiDTO(klijentiDTO);
            }


            userDTO.setActive(false);
            nUser = userService.saveUser(userMapper.convertToEntity(userDTO));
            UserDTO returnUser = userMapper.convertToDto(nUser);
            UserRolesDTO userRolesDTO = new UserRolesDTO(returnUser,roleDTO);
            UserRoles userRoles = userRolesService.saveUserRoles(userRolesMapper.convertToEntity(userRolesDTO));
            // UserRoles userRoles = userRolesService.saveUserRoles(userRolesMapper.convertToEntity(userRolesDTO));

                Mail mail = new Mail();
                mail.setFrom("no-reply@sitrepoapp");
                mail.setTo(returnUser.getEmail());
                mail.setSubject("Sitrepo app activisation link!!!!");

                Map<String, Object> model = new HashMap<String, Object>();
                model.put("name", "sitrepo.com");
                model.put("location", "Serbia");
                model.put("signature", "http://google.com");
                model.put("username", returnUser.getEmail());
                mail.setModel(model);

                emailService.sendSimpleMessage(mail);

            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;
        }

        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), validationUtil.getValidateErrors(result).toString());
        return apiResult;
    }

    @PutMapping(value = "/rest-api/updateUser",  consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody public ApiResult updateUser(@RequestBody @Valid UserDTO userDTO,
                                              BindingResult result,
                                              HttpServletRequest request) throws ParseException, IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO idUser = (UserDTO) auth.getPrincipal();
        User user = userService.findById(idUser.getId());
        userDTO.setId(user.getId());
        userDTO.setVersion(user.getVersion());

        KlijentiDTO klijentiDTO = klijentiMapper.convertToDto(user.getKlijenti());
        userDTO.setKlijentiDTO(klijentiDTO);

        if (!result.hasErrors()) {

            if (userDTO.getSlika() != null) {
                byte[] decodedBytes = Base64.getDecoder().decode(userDTO.getSlika());
                userDTO.setSlikaUser(decodedBytes);
            }
            userDTO.setActive(true);

            if (idUser.getPassword().equals(userDTO.getPassword())){
                userService.updateUser(userMapper.convertToEntity(userDTO));
            } else {
                userService.saveUser(userMapper.convertToEntity(userDTO));
            }

            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;
        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(),  validationUtil.getValidateErrors(result).toString());
        return apiResult;
    }

    @PutMapping(value = "/rest-api/osiguranje")
    @ResponseBody public  ApiResult snimiOsiguranje(@RequestBody @Valid OsiguranjeDTO osiguranjeDTO,
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

            osiguranjeDTO.setUser(userDTO);
            //osiguranjeDTO.setId(id);
            osiguranjeService.
                    updateOsiguranje(osiguranjeMapper.convertToEntity(osiguranjeDTO));

            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;

        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(),  validationUtil.getValidateErrors(result).toString());
        return apiResult;
    }

    @PutMapping(value = "/rest-api/prikolica")
    @ResponseBody public ApiResult snimiPrikolica(@RequestBody @Valid PrikolicaDTO prikolicaDTO,
                                                  BindingResult result,
                                                  HttpServletRequest request) throws ParseException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();

        if (userDTO == null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
            return apiResult;
        }
        if (!result.hasErrors()) {

            prikolicaDTO.setUser(userDTO);

            if (prikolicaDTO.getSlikaPrikolicebase64() != null) {
                byte[] decodedBytes = Base64.getDecoder().decode(prikolicaDTO.getSlikaPrikolicebase64());
                prikolicaDTO.setSlikaPrikolice(decodedBytes);
            }
            Prikolica prikolica = prikolicaMapper.convertToEntity(prikolicaDTO);
            prikolicaService.updatePrikolica(prikolica);
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;
        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(),HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), validationUtil.getValidateErrors(result).toString());
        return apiResult;

    }

    @PutMapping(value = "/rest-api/osigLice")
    @ResponseBody ApiResult updateOsigLice(@RequestBody @Valid UgovaracOsiguranjaDTO ugovaracOsiguranjaDTO,
                                           BindingResult result,
                                           HttpServletRequest request) throws IOException,ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();

        if (userDTO == null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
            return apiResult;
        }
        if (!result.hasErrors()) {

            ugovaracOsiguranjaDTO.setUser(userDTO);

            if (ugovaracOsiguranjaDTO.getFotoPolise() != null ) {
                byte[] decodedBytes = Base64.getDecoder().decode(
                        ugovaracOsiguranjaDTO.getFotoPolise()
                );
                ugovaracOsiguranjaDTO.setFotografijaPoliseUO(decodedBytes);
            }
            UgovaracOsiguranja ugovaracOsiguranja = ugovaracOsiguranjaMapper.convertToEntity(ugovaracOsiguranjaDTO);
            ugovaracOsiguranjaService.updateUgovarac(ugovaracOsiguranja);
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;


        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), validationUtil.getValidateErrors(result).toString());
        return apiResult;
    }

    @PostMapping(value = "/rest-api/snimiVozilo")
    @ResponseBody
    public ApiResult snimiVozilo(@RequestBody @Valid VoziloDTO voziloDTO,
                                 BindingResult result,
                                 HttpServletRequest request) throws ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        if (userDTO == null) {
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.UNAUTHORIZED.value(), "access denied");
            return apiResult;
        }
        if (!result.hasErrors()){
            voziloDTO.setUser(userDTO);
            if (voziloDTO.getFotoVozila() != null){
                byte[] decodedBytes = Base64.getDecoder().decode(voziloDTO.getFotoVozila());
                voziloDTO.setSlikaVozila(decodedBytes);
            }

            voziloService.updateVozilo(voziloMapper.convertToEntity(voziloDTO));
            ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                    HttpStatus.OK.value(), null);
            return apiResult;
        }
        ApiResult apiResult = new ApiResult(request.getRequestURL().toString(), HttpMethod.valueOf(request.getMethod()).name(),
                HttpStatus.BAD_REQUEST.value(), validationUtil.getValidateErrors(result).toString());
        return apiResult;
    }

    @GetMapping(value = "/rest-api/getKlijentLokacije")
    @ResponseBody
    public List<KlijentLtdLng> getKLijenti(@RequestParam(value = "latitude" ) Double latitude,
                                           @RequestParam(value = "longitude") Double longitude,
                                           HttpServletRequest request) {


        List<KlijentLtdLng> lista = klijentiService.getDistance(latitude, longitude);

        return  lista;
    }

    @DeleteMapping(value = "/rest-api/deleteUser/{id}")
    @ResponseBody
    public void deleteUser(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = (UserDTO) auth.getPrincipal();
        if (userDTO == null) {
            logout(request);
        }

       userService.deleteUserById(id);
        logout(request);
    }




/*    @PostMapping(value = "/rest-api/getKlijentLokacije")
    @ResponseBody
    public KlijentiDTO getKLijenti(@PathVariable String latitude, @PathVariable String longitude) {

    }*/



/*
    @GetMapping(value = "/rest-api/getpageuser")
    @ResponseBody
    public List<UserDTO> getPageUser(HttpServletRequest request) {
        PageRequest pageble = new PageRequest(0,2);
        Page<User> userPage = userService.findAll(pageble);
        totalPage = userPage.getTotalPages();
        currentPage = userPage.getNumber();
        return  userMapper.convertListToDto(userPage.getContent());

    }
    @GetMapping(value = "/rest-api/getnext")
    @ResponseBody
    public List<UserDTO> getNext(HttpServletRequest request) {
        currentPage = currentPage +1;
        if (totalPage == currentPage){
            System.out.println("odradi nista");
            // return  null;
        }
        PageRequest pageble = new PageRequest(currentPage,2);
        Page<User> userPage = userService.findAll(pageble);

       */
/* Pageable next = userPage.nextPageable();
        Page<User> kk = userRepository.findAll(next);*//*

        //return  userMapper.convertListToDto(kk.getContent());
        return  userMapper.convertListToDto(userPage.getContent());

    }
*/

}
