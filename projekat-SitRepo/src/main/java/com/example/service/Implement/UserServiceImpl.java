package com.example.service.Implement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import com.example.model.*;
import com.example.repository.*;
import com.example.service.UserService;
import com.example.service.VoziloService;
import org.hibernate.Hibernate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userService")
@org.springframework.context.annotation.Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public class UserServiceImpl implements UserService {

	private final static Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	DrzavaRepository drzavaRepository;

	@Autowired
	VoziloService voziloService;

	@Autowired
	KlijentiRepository klijentiRepository;

	@Autowired
	AutomobiliRepository automobiliRepository;

	@Autowired
	OkolnostiNezgodeRepository okolnostiNezgodeRepository;


	@Override
	public List<User> findUserAllByStatus(String status) {
		return userRepository.findAllByStatus(status);
	}

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		// user.setActive(false);

		//  user.setRoles(Arrays.asList(userRole));
		User res = userRepository.save(user);
		return res;
	}

	@Override
	public void upUser(User user) {
		User res = userRepository.save(user);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public void updateUser(User user) {
		//user.setPassword(user.getPassword());
		//user.setActive(true);


		/*Role userRole = roleRepository.findByRole(test.getRoles().iterator().next().getRole());
		user.setRoles(new ArrayList<Role>(Arrays.asList(userRole)));*/
/*		User sUser = userRepository.findByEmail(user.getEmail());
		User uUser = userRepository.findOne(sUser.getId());
		uUser.setDrzava(sUser.getDrzava());
		userRepository.save(uUser);*/
		User sUser = userRepository.findByEmail(user.getEmail());
		int rere = userRepository.updateUser(sUser.getId(),user.getName(),user.getEmail(),user.getPassword(),user.getLastName(),
				user.getAdresa(),user.getPostanskiBroj(),user.getDrzava(),user.getTelefon(),
				user.getBrVozackeDozvole(),user.getKategorijaDozvole(),user.getVozackaDozvolaVaziDo(),user.getActive(),
				sUser.getVersion(),user.getStatus(),user.getSlikaUser());
	}

	@Override
	public List<User> findAll() {
		List<User> users = userRepository.findAll();
		//kada admin treba da izlista samo user-e sa ROLE_USER
		users.forEach(user -> Hibernate.initialize(user.getUserRoles()));
		return users;
	}

	@Override
	public Page<User> findAll(PageRequest page) {
		return userRepository.findAll(page);
	}

	@Override
	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User findUserById(Long id) {
		return userRepository.findById(id);
	}

	// PRIVREMENA METODA ZBOG UPISA U BAZU
	@Override
	public void createUserAndRole(){

		Role roleUser = new Role();
		roleUser.setRole("ROLE_USER");
		Role roleAdmin = new Role();
		roleAdmin.setRole("ROLE_ADMIN");
		if (roleRepository.findByRole("ROLE_USER") == null) {



			roleRepository.save(roleUser);
			roleRepository.save(roleAdmin);

			Klijenti osiguranja1 = new Klijenti();
			Klijenti osiguranja2 = new Klijenti();
			Klijenti osiguranja3 = new Klijenti();
			osiguranja1.setName("Dunav");
			osiguranja1.setTipKlijenta("Osiguranje");
			osiguranja2.setName("Delta");
			osiguranja2.setTipKlijenta("Osiguranje");
			osiguranja3.setName("Triglav");
			osiguranja3.setTipKlijenta("Osiguranje");

			klijentiRepository.save(osiguranja1);
			klijentiRepository.save(osiguranja2);
			klijentiRepository.save(osiguranja3);



			User user = new User();
			user.setEmail("user@user");
			user.setPassword("user");
			user.setName("user");
			user.setLastName("user");
			user.setPassword(bCryptPasswordEncoder.encode("user"));
			user.setActive(true);
			Role userRole = roleRepository.findByRole("ROLE_USER");
			//user.setRoles(new ArrayList<Role>(Arrays.asList(userRole)));
			user.setKlijenti(osiguranja1);
			userRepository.save(user);

			User admin = new User();
			admin.setEmail("admin@admin");
			admin.setPassword(bCryptPasswordEncoder.encode("admin"));
			admin.setName("admin");
			admin.setLastName("admin");
			admin.setActive(true);
			Role adminRole = roleRepository.findByRole("ROLE_ADMIN");
			//admin.setRoles(new ArrayList<Role>(Arrays.asList(adminRole)));
			userRepository.save(admin);

			Drzave d1 = new Drzave();
			Drzave d2 = new Drzave();
			Drzave d3 = new Drzave();
			d1.setDrzava("Bosna i Hercegovina");
			d2.setDrzava("Srbija");
			d3.setDrzava("Hrvatska");
			drzavaRepository.save(d1);
			drzavaRepository.save(d2);
			drzavaRepository.save(d3);

			Automobili a1 = new Automobili();
			a1.setMarka("Pezo");
			List<AutomobiliTip> listaPezoa = new ArrayList<>();
			AutomobiliTip at1 = new AutomobiliTip();
			AutomobiliTip at2 = new AutomobiliTip();
			AutomobiliTip at3 = new AutomobiliTip();
			AutomobiliTip at4 = new AutomobiliTip();

			at1.setModel("206");
			at2.setModel("307");
			at3.setModel("407");
			at4.setModel("3008");

			at1.setAutomobili(a1);
			at2.setAutomobili(a1);
			at3.setAutomobili(a1);
			at4.setAutomobili(a1);

			listaPezoa.add(at1);
			listaPezoa.add(at2);
			listaPezoa.add(at3);
			listaPezoa.add(at4);

			a1.setTipAutomobila(listaPezoa);
			automobiliRepository.save(a1);

			System.out.println("POPUNJENA BAZA DA RADI TRENUTNO");
		}
	}

	// PRIVREMENA METODA ZBOG UPISA U BAZU
	@Override
	public void createDrzavaAutomobilAndAutomobilTip() {

	}

	@Override
	public void saveAdmin(User user) throws IOException {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		Role userRole = roleRepository.findByRole("ROLE_ADMIN");
		//user.setRoles(new ArrayList<Role>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public Klijenti save(Klijenti klijent) {
		return klijentiRepository.save(klijent);
	}

	@Override
	public User authenticateUser(String email, String password) {
		User user = userRepository.findUserByEmailAndPassword(email, password);
		if (user != null) {
			return user;
		} else
			return null;
	}

		@Override
		public User findUserEmail(String email) {
			User user =  userRepository.findByEmail(email);
			Hibernate.initialize(user.getUserRoles());
			return user;
		}

	@Override
	public List<User> findUserByUserRoles(String role) {
		return userRepository.findAllByUserRoles(role);
	}


}

