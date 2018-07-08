package com.example.service;

import com.example.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.io.IOException;
import java.util.List;

public interface UserService {
	public List<User> findUserAllByStatus(String status);

	public User findUserByEmail(String email);

	public User saveUser(User user) throws IOException;

	public void upUser(User user);

	public void updateUser(User user);

	List<User> findAll();

	Page<User> findAll(PageRequest page);

	public void deleteUserById(Long id);

	public User findUserById(Long id);

	public void createUserAndRole();

	public void createDrzavaAutomobilAndAutomobilTip();

	public void saveAdmin(User user) throws IOException;

	public Klijenti save(Klijenti klijent);

	public User authenticateUser(String email, String password);

	public User findById(Long id);
	public User findUserEmail(String email);

	public List<User> findUserByUserRoles(String role);

}
