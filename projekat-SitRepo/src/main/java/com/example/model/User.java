package com.example.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

/*	@Email(message = "Email nije pravilno unet")*/
	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	@Transient
	private String password;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "adresa")
	private String adresa;

	@Column(name = "postanski_broj")
	private String postanskiBroj;

	@Column(name = "drzava")
	private String drzava;

	@Column(name = "telefon")
	private String telefon;

	@Column(name = "broj_vozacke_dozvole")
	private String brVozackeDozvole;

	@Column(name = "kategorija_dozvole")
	private String kategorijaDozvole;

	@Column(name = "vozacka_dozvola_vazi_do")
	private String vozackaDozvolaVaziDo;

	@Column(name = "active")
	private boolean active;


	@Version
	private long version;

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@Column(name = "status")
	private String status;

	@Column(name = "slika_user", columnDefinition = "LONGBLOB")
	private byte[] slikaUser;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private List<UserRoles> userRoles;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<Vozilo> vozila;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<Prikolica> prikolice;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<Osiguranje> osiguranja;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<UgovaracOsiguranja> ugovaracOsiguranja;


	public boolean isActive() {
		return active;
	}

	public List<VozacNezgodaIzvestaj> getVozacNezgodaIzvestaj() {
		return vozacNezgodaIzvestaj;
	}

	public void setVozacNezgodaIzvestaj(List<VozacNezgodaIzvestaj> vozacNezgodaIzvestaj) {
		this.vozacNezgodaIzvestaj = vozacNezgodaIzvestaj;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<VozacNezgodaIzvestaj> vozacNezgodaIzvestaj;

	public List<PutnikIzvestaj> getPutnikIzvestaj() {
		return putnikIzvestaj;
	}

	public void setPutnikIzvestaj(List<PutnikIzvestaj> putnikIzvestaj) {
		this.putnikIzvestaj = putnikIzvestaj;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private List<PutnikIzvestaj> putnikIzvestaj;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Klijenti klijenti;

	public Klijenti getKlijenti() {
		return klijenti;
	}

	public void setKlijenti(Klijenti klijenti) {
		this.klijenti = klijenti;
	}

	public boolean isActiPutnikSvedve() {
		return active;
	}


	public byte[] getSlikaUser() {
		return slikaUser;
	}

	public void setSlikaUser(byte[] slikaUser) {
		this.slikaUser = slikaUser;
	}



	public List<UgovaracOsiguranja> getUgovaracOsiguranja() {
		return ugovaracOsiguranja;
	}

	public void setUgovaracOsiguranja(List<UgovaracOsiguranja> ugovaracOsiguranja) {
		this.ugovaracOsiguranja = ugovaracOsiguranja;
	}

	public List<Osiguranje> getOsiguranja() {
		return osiguranja;
	}

	public void setOsiguranja(List<Osiguranje> osiguranja) {
		this.osiguranja = osiguranja;
	}

	public List<Prikolica> getPrikolice() {
		return prikolice;
	}

	public void setPrikolice(List<Prikolica> prikolice) {
		this.prikolice = prikolice;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getPostanskiBroj() {
		return postanskiBroj;
	}

	public void setPostanskiBroj(String postanskiBroj) {
		this.postanskiBroj = postanskiBroj;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getBrVozackeDozvole() {
		return brVozackeDozvole;
	}

	public void setBrVozackeDozvole(String brVozackeDozvole) {
		this.brVozackeDozvole = brVozackeDozvole;
	}

	public String getKategorijaDozvole() {
		return kategorijaDozvole;
	}

	public void setKategorijaDozvole(String kategorijaDozvole) {
		this.kategorijaDozvole = kategorijaDozvole;
	}

	public String getVozackaDozvolaVaziDo() {
		return vozackaDozvolaVaziDo;
	}

	public void setVozackaDozvolaVaziDo(String vozackaDozvolaVaziDo) {
		this.vozackaDozvolaVaziDo = vozackaDozvolaVaziDo;
	}

	public List<Vozilo> getVozila() {
		return vozila;
	}

	public void setVozila(List<Vozilo> vozila) {
		this.vozila = vozila;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<UserRoles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRoles> userRoles) {
		this.userRoles = userRoles;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		User that = (User) o;

		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return 31;
	}
}
