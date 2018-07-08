package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {
	 List<User> findAllByStatus(String status);
	 User findByEmail(String email);
	 User findById(Long id);
	 List<User> findAll();
	 User save(User user);
	 void deleteById(Long id);
	 User findUserByEmailAndPassword(String email, String password);

	 List<User> findAllByUserRoles(String role);

	@Modifying
	@Transactional(readOnly =true)
	@Query("UPDATE User u SET u.name = :name, u.active = :active, u.adresa = :adresa," +
			" u.brVozackeDozvole = :brVozackeDozvole, u.drzava = :drzava, u.email = :email, " +
			"u.kategorijaDozvole = :kategorijaDozvole, u.lastName = :lastName, u.password = :password, " +
			"u.postanskiBroj = :postanskiBroj, u.slikaUser = :slikaUser, u.status = :status," +
			"u.telefon = :telefon, u.vozackaDozvolaVaziDo = :vozackaDozvolaVaziDo, " +
			"u.version = :version  WHERE u.id = :user_id")
	int updateUser(@Param("user_id") Long userId, @Param("name") String name, @Param("email") String email, @Param("password") String password,
							   @Param("lastName") String lastName, @Param("adresa") String adresa, @Param("postanskiBroj") String postanskiBroj,
							   @Param("drzava") String drzava, @Param("telefon") String telefon, @Param("brVozackeDozvole") String brVozackeDozvole,
							   @Param("kategorijaDozvole") String kategorijaDozvole, @Param("vozackaDozvolaVaziDo") String vozackaDozvolaVaziDo,
							   @Param("active") boolean active, @Param("version") Long version, @Param("status") String status,
							   @Param("slikaUser") byte[] slikaUser);


}
