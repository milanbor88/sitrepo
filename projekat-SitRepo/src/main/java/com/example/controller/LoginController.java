package com.example.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.example.dto.*;
import com.example.mapper.*;
import com.example.model.*;
import com.example.repository.RoleRepository;
import com.example.service.*;
import com.example.util.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Controller
public class LoginController {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private VoziloService voziloService;

	@Autowired
	private PrikolicaService prikolicaService;

	@Autowired
	private OsiguranjeService osiguranjeService;

	@Autowired
	private UgovaracOsiguranjaService ugovaracOsiguranjaService;

	@Autowired
	private KlijentiService klijentiService;

	@Autowired
	AutomobiliService automobiliService;

	@Autowired
	AutomobiliTipService automobiliTipService;

	@Autowired
	DrzavaService drzavaService;

	@Autowired
	OkolnostiNezgodeService okolnostiNezgodeService;

	@Autowired
	ZvanicniOrganiService zvanicniOrganiService;

	@Autowired
	EmailService emailService;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	VoziloMapper voziloMapper;

	@Autowired
	OsiguranjeMapper osiguranjeMapper;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UgovaracOsiguranjaMapper ugovaracOsiguranjaMapper;

	@Autowired
	PrikolicaMapper prikolicaMapper;

	@Autowired
	DrzaveMapper drzaveMapper;

	@Autowired
	KlijentiMapper klijentiMapper;

	@Autowired
	UserRolesService userRolesService;


	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute LoginDTO loginDTO) {

		// PRIVREMENE METODE ZBOG UPISA U BAZU
		//userService.createUserAndRole();
		//userService.createDrzavaAutomobilAndAutomobilTip();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		modelAndView.addObject("user", new LoginDTO());

		return modelAndView;
	}

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public ModelAndView login(@ModelAttribute LoginDTO loginDTO) {

		// PRIVREMENE METODE ZBOG UPISA U BAZU
//		userService.createUserAndRole();
//		userService.createDrzavaAutomobilAndAutomobilTip();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		modelAndView.addObject("user", new LoginDTO());

		return modelAndView;

	}

	@RequestMapping(value = {"/loginError"}, method = RequestMethod.GET)
	public ModelAndView loginGreska(@ModelAttribute LoginDTO loginDTO) {

		// PRIVREMENE METODE ZBOG UPISA U BAZU
		//userService.createUserAndRole();
		//userService.createDrzavaAutomobilAndAutomobilTip();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("loginmessage");
		modelAndView.addObject("successMessage", "Pogrešni parametri logovanaja! Pokušajte ponovo.");
		modelAndView.addObject("user", new LoginDTO());

		return modelAndView;

	}

	@RequestMapping(value = "/proba")
	public String proba(HttpServletRequest request){

		if (request.isUserInRole("ADMIN")) {
			return "redirect:/admin/adminIzvestajiVecaVozac";
		}
		return "redirect:/user/home";
	}


	@RequestMapping(value = "/user/user", method = RequestMethod.GET)
	public ModelAndView user() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());

		List<Drzave> listaDrzava = drzavaService.findAll();

		modelAndView.addObject("userName",user.getName() + " " + user.getLastName());
		modelAndView.addObject("user",user);
		modelAndView.addObject("listaDrzava", listaDrzava);
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("user/user");
		return modelAndView;

	}

	@RequestMapping(value = "/user/user", method = RequestMethod.POST)
	public ModelAndView updateUser(@Valid @ModelAttribute User user,
								   BindingResult result,
								   @RequestParam MultipartFile[] fileUpload) throws IOException {

		List<Drzave> listaDrzava = drzavaService.findAll();
		if(!result.hasErrors()){
			ModelAndView m = new ModelAndView("redirect:/user/user");
			m.addObject("userName",user.getName() + " " + user.getLastName());
			m.addObject("listaDrzava", listaDrzava);
			m.setViewName("user/user");

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
				userService.saveUser(u);
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
						userService.saveUser(u);
					}
				}
			}
			m.addObject("successMessage", "Izmene uspešno sačuvane!");
			return m;
		}

		if(result.hasErrors()){
			ModelAndView m = new ModelAndView("redirect:/user/user");
			m.addObject("userName",user.getName() + " " + user.getLastName());
			m.addObject("listaDrzava", listaDrzava);
			m.setViewName("user/user");
			m.addObject("successMessage", "Imate gresku");
			return m;
		}
		return new ModelAndView("redirect:/user/user");
	}

	@RequestMapping(value = "/user/vozilo", method = RequestMethod.GET)
	public ModelAndView vozilo() throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		UserDTO userDTO = userMapper.convertToDto(user);

		VoziloDTO voziloDTO = null;

//		Automobili automobilMarka = automobiliService.findAutomobiliByMarka("Pezo");
//		List<AutomobiliTip> listaTipova = automobiliTipService.findByAutomobili_Marka(automobilMarka.getMarka());

		List<Drzave> listaDrzava = drzavaService.findAll();
		List<DrzaveDTO> drzaveDTOS = drzaveMapper.convertListToDto(listaDrzava);

		Vozilo v = voziloService.findVoziloByUser(user);
		if (v != null) {
			voziloDTO = voziloMapper.convertToDto(v);
		} else {
			voziloDTO = new VoziloDTO();
		}

		modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
		modelAndView.addObject("voziloDTO", voziloDTO);
		modelAndView.addObject("drzaveDTOS",drzaveDTOS);
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("user/vozilo");
		return modelAndView;

	}

	@RequestMapping(value = "/user/vozilo", method = RequestMethod.POST)
	public ModelAndView updateVozilo(@Valid @ModelAttribute VoziloDTO voziloDTO,
									 BindingResult result,
									 RedirectAttributes redirectAttributes,
									 @RequestParam MultipartFile[] fileUpload) throws IOException, ParseException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		UserDTO userDTO = userMapper.convertToDto(user);
		List<Drzave> listaDrzava = drzavaService.findAll();
		List<DrzaveDTO> drzaveDTOS = drzaveMapper.convertListToDto(listaDrzava);

		if(!result.hasErrors()) {
			ModelAndView m = new ModelAndView("redirect:/user/vozilo");
			redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
			redirectAttributes.addFlashAttribute("drzaveDTOS",drzaveDTOS);
			voziloDTO.setUser(userDTO);


			Vozilo v = voziloService.findVoziloByUser(user);
			if(v!=null){
				voziloDTO.setSlikaVozila(voziloService.findVoziloByUser(user).getSlikaVozila());
			}
			if (fileUpload != null) {
					for (MultipartFile aFile : fileUpload) {
						if (aFile.isEmpty()) break;
						voziloDTO.setSlikaVozila(aFile.getBytes());
					}
				}

			redirectAttributes.addFlashAttribute("successMessage", "Vozilo je uspešno sačuvano");
			Vozilo vozilo = voziloMapper.convertToEntity(voziloDTO);
			voziloService.updateVozilo(vozilo);
			return m;
		}
		if(result.hasErrors()){
			ModelAndView m = new ModelAndView("redirect:/user/vozilo");
			m.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
			m.addObject("drzaveDTOS",drzaveDTOS);
			m.addObject("voziloDTO", voziloDTO);
			m.setViewName("user/vozilo");
			m.addObject("successMessage", "Imate grešku!");
			return m;
		}
		return new ModelAndView("redirect:/user/vozilo");
	}


	@RequestMapping(value = "/user/osiguranje", method = RequestMethod.GET)
	public ModelAndView osiguranje() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		UserDTO userDTO = userMapper.convertToDto(user);
		OsiguranjeDTO osiguranjeDTO = null;

		List<Klijenti> listaKlijenata = klijentiService.findKlijentiByTipKlijenta("Osiguranje");
		List<KlijentiDTO> klijentiDTOS = klijentiMapper.convertListToDto(listaKlijenata);

		List<Drzave> listaDrzava = drzavaService.findAll();

		Osiguranje o = osiguranjeService.findOsiguranjeByUser(user);
		if (o != null) {
			osiguranjeDTO = osiguranjeMapper.convertToDto(o);
		} else {
			osiguranjeDTO = new OsiguranjeDTO();
		}

		modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
		modelAndView.addObject("userDTO",userDTO);
		modelAndView.addObject("osiguranjeDTO", osiguranjeDTO);
		modelAndView.addObject("listaDrzava", listaDrzava);
		modelAndView.addObject("klijentiDTOS", klijentiDTOS);
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("user/osiguranje");

		return modelAndView;
	}

	@RequestMapping(value = "/user/osiguranjeblank", method = RequestMethod.GET)
	public ModelAndView osiguranjeblank() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		UserDTO userDTO = userMapper.convertToDto(user);
		OsiguranjeDTO osiguranjeDTO = null;

		Osiguranje o = osiguranjeService.findOsiguranjeByUser(user);
		if (o != null) {
			osiguranjeDTO = osiguranjeMapper.convertToDto(o);
		} else {
			osiguranjeDTO = new OsiguranjeDTO();
		}
		List<Drzave> listaDrzava = drzavaService.findAll();

		modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
		modelAndView.addObject("userDTO",userDTO);
		modelAndView.addObject("listaDrzava", listaDrzava);
		modelAndView.addObject("osiguranjeDTO", osiguranjeDTO);
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("user/osiguranjeblank");

		return modelAndView;
	}

	@RequestMapping(value = "/user/osiguranje", method = RequestMethod.POST)
	public ModelAndView updateOsiguranje(@Valid @ModelAttribute OsiguranjeDTO osiguranjeDTO,
										 BindingResult result,
										 RedirectAttributes redirectAttributes
										 ) throws ParseException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		UserDTO userDTO = userMapper.convertToDto(user);
		List<Klijenti> listaKlijenata = klijentiService.findKlijentiByTipKlijenta("Osiguranje");
		List<KlijentiDTO> klijentiDTOS = klijentiMapper.convertListToDto(listaKlijenata);
		List<Drzave> listaDrzava = drzavaService.findAll();

		if(result.hasErrors()){
			ModelAndView m = new ModelAndView("redirect:/user/osiguranje");
			m.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
			m.addObject("successMessage", "Imate grešku!");
			m.addObject("osiguranjeDTO", osiguranjeDTO);
			m.addObject("listaDrzava", listaDrzava);
			m.addObject("klijentiDTOS",klijentiDTOS);
			m.setViewName("user/osiguranje");
			return m;
		}
		if (!result.hasErrors()) {
			ModelAndView m = new ModelAndView("redirect:/user/osiguranje");
			osiguranjeDTO.setUser(userDTO);
			Osiguranje osiguranje = osiguranjeMapper.convertToEntity(osiguranjeDTO);
			redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
			redirectAttributes.addFlashAttribute("klijentiDTOS",klijentiDTOS);
			redirectAttributes.addFlashAttribute("listaDrzava", listaDrzava);
			redirectAttributes.addFlashAttribute("successMessage", "Izmene uspešno sačuvane!");
			osiguranjeService.updateOsiguranje(osiguranje);
			return m;
		}
		return new ModelAndView("redirect:/user/osiguranje");
	}

	@RequestMapping(value = "/user/osiguranjeblank", method = RequestMethod.POST)
	public ModelAndView updateOsiguranjeblank(@Valid @ModelAttribute OsiguranjeDTO osiguranjeDTO,
										 BindingResult result,
										 RedirectAttributes redirectAttributes
	) throws ParseException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		UserDTO userDTO = userMapper.convertToDto(user);
		List<Drzave> listaDrzava = drzavaService.findAll();

		if(result.hasErrors()){
			ModelAndView m = new ModelAndView("redirect:/user/osiguranjeblank");
			m.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
			m.addObject("successMessage", "Imate grešku!");
			m.addObject("listaDrzava", listaDrzava);
			m.addObject("osiguranjeDTO", osiguranjeDTO);
			m.setViewName("user/osiguranjeblank");
			return m;
		}
		if (!result.hasErrors()) {
			ModelAndView m = new ModelAndView("redirect:/user/osiguranjeblank");
			osiguranjeDTO.setUser(userDTO);
			Osiguranje osiguranje = osiguranjeMapper.convertToEntity(osiguranjeDTO);
			redirectAttributes.addFlashAttribute("listaDrzava", listaDrzava);
			redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
			redirectAttributes.addFlashAttribute("successMessage", "Izmene uspešno sačuvane!");
			osiguranjeService.updateOsiguranje(osiguranje);
			return m;
		}
		return new ModelAndView("redirect:/user/osiguranjeblank");
	}


	@RequestMapping(value = "/user/prikolica", method = RequestMethod.GET)
	public ModelAndView prikolica() throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		PrikolicaDTO prikolicaDTO = null;

		List<Drzave> listaDrzava = drzavaService.findAll();
		List<DrzaveDTO> drzaveDTOS = drzaveMapper.convertListToDto(listaDrzava);

		Prikolica p = prikolicaService.findPrikolicaByUser(user);
		if (p != null) {
			prikolicaDTO = prikolicaMapper.convertToDto(p);
		} else {
			prikolicaDTO = new PrikolicaDTO();
		}

		modelAndView.addObject("userName",user.getName() + " " + user.getLastName());
		modelAndView.addObject("prikolicaDTO", prikolicaDTO);
		modelAndView.addObject("drzaveDTOS",drzaveDTOS);
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("user/prikolica");
		return modelAndView;
	}

	@RequestMapping(value = "/user/prikolica", method = RequestMethod.POST)
	public ModelAndView updatePrikolica(@Valid @ModelAttribute PrikolicaDTO prikolicaDTO,
										BindingResult result,
										RedirectAttributes redirectAttributes,
										@RequestParam MultipartFile[] fileUpload) throws IOException, ParseException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		UserDTO userDTO = userMapper.convertToDto(user);
		List<Drzave> listaDrzava = drzavaService.findAll();
		List<DrzaveDTO> drzaveDTOS = drzaveMapper.convertListToDto(listaDrzava);


		if(result.hasErrors()){
			ModelAndView m = new ModelAndView("redirect:/user/prikolica");
			m.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
			m.addObject("successMessage", "Imate grešku!");
			m.addObject("drzaveDTOS",drzaveDTOS);
			m.addObject("drzaveDTOS",listaDrzava);
			m.setViewName("user/prikolica");
			return m;
		}
		if (!result.hasErrors()) {
			ModelAndView m = new ModelAndView("redirect:/user/prikolica");
			redirectAttributes.addFlashAttribute("userName",user.getName() + " " + user.getLastName());
			redirectAttributes.addFlashAttribute("listaDrzava",listaDrzava);
			prikolicaDTO.setUser(userDTO);


			Prikolica p = prikolicaService.findPrikolicaByUser(user);
			if(p!=null){
				prikolicaDTO.setSlikaPrikolice(prikolicaService.findPrikolicaByUser(user).getSlikaPrikolice());
			}
			if (fileUpload != null) {
				for (MultipartFile aFile : fileUpload) {
					if (aFile.isEmpty()) break;
					prikolicaDTO.setSlikaPrikolice(aFile.getBytes());
				}
			}

			redirectAttributes.addFlashAttribute("successMessage", "Izmene uspešno sačuvane!");
			Prikolica prikolica = prikolicaMapper.convertToEntity(prikolicaDTO);
			prikolicaService.updatePrikolica(prikolica);
			return m;
		}
		return new ModelAndView("redirect:/user/prikolica");
	}


	@RequestMapping(value = "/user/ugovarac", method = RequestMethod.GET)
	public ModelAndView ugovarc() throws IOException {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		UserDTO userDTO = userMapper.convertToDto(user);
		UgovaracOsiguranjaDTO ugovaracOsiguranjaDTO = null;
		List<Drzave> listaDrzava = drzavaService.findAll();

		UgovaracOsiguranja uo = ugovaracOsiguranjaService.findUgovaracOsiguranjaByUser(user);
		if (uo != null) {
			ugovaracOsiguranjaDTO = ugovaracOsiguranjaMapper.convertToDto(uo);
		} else {
			ugovaracOsiguranjaDTO = new UgovaracOsiguranjaDTO();
		}

		modelAndView.addObject("userName",userDTO.getName() + " " + userDTO.getLastName());
		modelAndView.addObject("ugovaracOsiguranjaDTO", ugovaracOsiguranjaDTO);
		modelAndView.addObject("listaDrzava", listaDrzava);
		modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
		modelAndView.setViewName("user/ugovarac");
		return modelAndView;

	}

	@RequestMapping(value = "/user/ugovarac", method = RequestMethod.POST)
	public ModelAndView updateUgovaracOsiguranja(@Valid @ModelAttribute UgovaracOsiguranjaDTO ugovaracOsiguranjaDTO,
												 BindingResult result,
												 RedirectAttributes redirectAttributes,
												 @RequestParam MultipartFile[] fileUpload) throws IOException, ParseException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		UserDTO userDTO = userMapper.convertToDto(user);

		if(result.hasErrors()){
			ModelAndView m = new ModelAndView("redirect:/user/ugovarac");
			m.addObject("userName",user.getName() + " " + user.getLastName());
			m.addObject("ugovaracOsiguranjaDTO", ugovaracOsiguranjaDTO);
			m.setViewName("user/ugovarac");
			m.addObject("successMessage", "Imate grešku!");
			return m;
		}
		if (!result.hasErrors()) {
			ModelAndView m = new ModelAndView("redirect:/user/ugovarac");
			redirectAttributes.addFlashAttribute("userName",userDTO.getName() + " " + userDTO.getLastName());
			ugovaracOsiguranjaDTO.setUser(userDTO);

			UgovaracOsiguranja uo = ugovaracOsiguranjaService.findUgovaracOsiguranjaByUser(user);
			if(uo!=null){
				ugovaracOsiguranjaDTO.setFotografijaPoliseUO(ugovaracOsiguranjaService.findUgovaracOsiguranjaByUser(user).getFotografijaPoliseUO());
			}
			if (fileUpload != null) {
				for (MultipartFile aFile : fileUpload) {
					if (aFile.isEmpty()) break;
					ugovaracOsiguranjaDTO.setFotografijaPoliseUO(aFile.getBytes());
				}
			}

			UgovaracOsiguranja ugovaracOsiguranja = ugovaracOsiguranjaMapper.convertToEntity(ugovaracOsiguranjaDTO);
			ugovaracOsiguranjaService.updateUgovarac(ugovaracOsiguranja);
			redirectAttributes.addFlashAttribute("successMessage", "Izmene uspešno sačuvane!");
			return m;
		}
		return new ModelAndView("redirect:/user/ugovarac");
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid LoginDTO loginDTO,
									  BindingResult bindingResult) throws MessagingException,IOException, ParseException {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByEmail(loginDTO.getUsername());

		if (userExists != null) {
			/*bindingResult
					.rejectValue("email", "error.user",
							"Već postoji korisnik koji je registrovan sa dostavljenim e-poštom");*/
			modelAndView.addObject("successMessage", "Greska email postoji u bazi!");
			modelAndView.addObject("user",loginDTO);
			modelAndView.setViewName("loginmessage");
			return  modelAndView;
		}

		if(!bindingResult.hasErrors()) {
			modelAndView.addObject("successMessage", "Korisnik je uspesno kreiran! Aktivirajte svoj nalog putem e-maila!");
			modelAndView.addObject("user", new LoginDTO());
			modelAndView.setViewName("loginmessage");
		}

		UserDTO userDTO = new UserDTO();
		userDTO.setLastName(loginDTO.getLastName());
		userDTO.setEmail(loginDTO.getUsername());
		userDTO.setName(loginDTO.getFirstName());
		userDTO.setPassword(loginDTO.getPassword());
		if(!loginDTO.getPromoKod().equals("") ) {
			Klijenti klijenti = klijentiService.findByPromoKod(loginDTO.getPromoKod());
			if (klijenti == null) {
				modelAndView.addObject("successMessage", "Greška, pograšano ste uneli promo kod!");
				modelAndView.addObject("user",loginDTO);
				modelAndView.setViewName("loginmessage");
				return  modelAndView;

			}
			KlijentiDTO klijentiDTO = klijentiMapper.convertToDto(klijenti);
			userDTO.setKlijentiDTO(klijentiDTO);

		} else {
			KlijentiDTO klijentiDTO = klijentiMapper.convertToDto(klijentiService.findByPromoKod("000000"));
			userDTO.setKlijentiDTO(klijentiDTO);
		}


		Role userRole = roleRepository.findByRole("ROLE_USER");
		User proba = userMapper.convertToEntity(userDTO);
		proba = userService.saveUser(proba);
		UserRoles userRoles = new UserRoles(proba, userRole);
		userRolesService.saveUserRoles(userRoles);

		Mail mail = new Mail();
		mail.setFrom("no-reply@sitrepoapp");
		mail.setTo(userDTO.getEmail());
		mail.setSubject("Sitrepo app activisation link!!!!");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", "sitrepo.com");
		model.put("location", "Serbia");
		model.put("signature", "http://google.com");
		model.put("username",userDTO.getEmail());
		mail.setModel(model);

		emailService.sendSimpleMessage(mail);

		return modelAndView;
	}


	@RequestMapping(value = "/resetLozinke", method = RequestMethod.POST)
	public ModelAndView resetLozinke(HttpServletRequest request) throws MessagingException,IOException, ParseException {
		ModelAndView modelAndView = new ModelAndView();
		String email = request.getParameter("resetpass");
		User userExists = userService.findUserByEmail(email);

		if (userExists == null) {
			/*bindingResult
					.rejectValue("email", "error.user",
							"Već postoji korisnik koji je registrovan sa dostavljenim e-poštom");*/
			modelAndView.addObject("successMessage", "Greska email nije registrovan u bazi! Izvršite registraciju!");
			modelAndView.addObject("user",new LoginDTO());
			modelAndView.setViewName("loginmessage");
			return  modelAndView;
		}

		modelAndView.addObject("successMessage", "Posetite vasu e-mail adresu da bi ste resetovali lozinku!");
		modelAndView.addObject("user", new LoginDTO());
		modelAndView.setViewName("loginmessage");

		Mail mail = new Mail();
		mail.setFrom("no-reply@sitrepoapp");
		mail.setTo(email);
		mail.setSubject("Sitrepo app link za resetovanje lozinke!!!!");

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", "sitrepo.com");
		model.put("location", "Serbia");
		model.put("signature", "http://google.com");
		model.put("username",email);
		mail.setModel(model);

		emailService.sendRestartSimpleMessage(mail);

		return modelAndView;
	}



	@RequestMapping(value = "/kontaktforma", method = RequestMethod.POST)
	public ModelAndView kontaktforma(HttpServletRequest request) throws MessagingException,IOException, ParseException {

		ModelAndView modelAndView = new ModelAndView();
		String subject = request.getParameter("comments");
		String email = request.getParameter("email");
		String name = request.getParameter("name");

		modelAndView.addObject("successMessage", "Posetite vasu e-mail adresu da bi ste resetovali lozinku!");
		modelAndView.setViewName("email-template");

		Mail mail = new Mail();
		mail.setFrom(email);
		mail.setTo("duljarevic@gmail.com");
		mail.setSubject(subject);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", name);
		model.put("location", "Serbia");
		model.put("signature", "http://google.com");
		model.put("username",email);
		mail.setModel(model);


		emailService.sendKontaktFormaSimpleMessage(mail);

		return modelAndView;
	}

	@RequestMapping(value = "/user/imageUgovaracOsiguranja", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE} )
	public ResponseEntity<byte[]> getImage() throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		byte[] imageContent = user.getUgovaracOsiguranja().get(0).getFotografijaPoliseUO();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
	}
	@RequestMapping(value = "/user/imageVozilo", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE} )
	public ResponseEntity<byte[]> getImageVozilo() throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		byte[] imageContent = user.getVozila().get(0).getSlikaVozila();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
	}
	@RequestMapping(value = "/user/imagePrikolica", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE} )
	public ResponseEntity<byte[]> getImagePrikolica() throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		byte[] imageContent = user.getPrikolice().get(0).getSlikaPrikolice();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/imageUser", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE} )
	public ResponseEntity<byte[]> getImageUser() throws IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		byte[] imageContent = user.getSlikaUser();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/emailActivisation/{username}", method =RequestMethod.GET )
	public ModelAndView emailActivizacija(ModelAndView model,@PathVariable String username, HttpServletRequest request) {

		User user = userService.findUserByEmail(username);
		user.setActive(true);
		user.setStatus("Aktivan");
		userService.updateUser(user);
		model.setViewName("login");
		model.addObject("user", new LoginDTO());
		return model;
	}

	@RequestMapping(value = "/emailRestartLozinku/{email}", method =RequestMethod.GET )
	public ModelAndView emailRestartLozinku(ModelAndView model,@PathVariable String email) throws IOException {

		User user = userService.findUserByEmail(email);

		model.setViewName("restartPass");
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "/emailRestartLozinkumessage/{email}", method =RequestMethod.GET )
	public ModelAndView emailRestartLozinkumessage(ModelAndView model,@PathVariable String email) throws IOException {

		User user = userService.findUserByEmail(email);

		model.setViewName("restartPassMessage");
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "/snimiNovuLozinku/{email}", method =RequestMethod.POST )
	public ModelAndView snimiNovuLozinku(ModelAndView model,@PathVariable String email,
										 HttpServletRequest request,
										 RedirectAttributes redirectAttributes) throws IOException {

			String pass = request.getParameter("pass");
			String password = request.getParameter("password");

			User user = userService.findUserByEmail(email);
			if(pass.equals(password)) {
				user.setPassword(pass);
				userService.saveUser(user);
				ModelAndView m = new ModelAndView("redirect:/login");
				redirectAttributes.addFlashAttribute("successMessage", "Lozinka uspesno promenjana!");
				return m;
			}else {
				ModelAndView m = new ModelAndView("redirect:/emailRestartLozinkumessage/" + email);
				redirectAttributes.addFlashAttribute("successMessage", "Imate grešku! Lozinke se ne poklapaju");
				return m;
			}
	}


	@RequestMapping(value = "/deleteUser/{id}" , method = RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable(value = "id") Long id, HttpServletRequest request,
								   RedirectAttributes redirectAttributes) {

		userService.deleteUserById(id);
		ModelAndView m = new ModelAndView("redirect:/logout");
		return m;

	}

}
