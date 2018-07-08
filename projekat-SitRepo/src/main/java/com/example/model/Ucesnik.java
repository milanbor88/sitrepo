package com.example.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Ucesnik")
public class Ucesnik {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@NotBlank(message = "Ovo polje je obavezno")
	@Length(max = 30, message = "Polje može sadržati do 30 karaktera")
	@Column(name = "email")
	private String email;

	@NotBlank(message = "Ovo polje je obavezno")
	@Column(name = "name")
	@Length(max = 12, message = "Polje može sadržati do 12 karaktera")
	private String name;

	@NotBlank(message = "Ovo polje je obavezno")
	@Length(max = 15, message = "Polje može sadržati do 15 karaktera")
	@Column(name = "last_name")
	private String lastName;

	@Length(max = 30, message = "Polje može sadržati do 30 karaktera")
	@Column(name = "adresa")
	private String adresa;

	@Length(max = 5, message = "Polje mora da sadrži 5 brojeva")
	@Column(name = "postanski_broj")
	private String postanskiBroj;

	@Length(max = 20, message = "Polje može sadržati do 20 karaktera")
	@Column(name = "drzava")
	private String drzava;

	@Length(max = 20, message = "Polje može sadržati do 20 karaktera")
	@Column(name = "telefon")
	private String telefon;

	@Length(max = 20, message = "Polje može sadržati do 20 karaktera")
	@Column(name = "broj_vozacke_dozvole")
	private String brVozackeDozvole;

	@Column(name = "kategorija_dozvole")
	private String kategorijaDozvole;

	@Column(name = "vozacka_dozvola_vazi_do")
	private String vozackaDozvolaVaziDo;

	@Column(name = "datum_rodjenja")
	private String datumRodjenja;

	@Column(name = "slika_ucesnik", columnDefinition = "LONGBLOB")
	private byte[] slikaUcesnik;

	@ManyToOne(fetch = FetchType.LAZY)
	private VozacNezgodaManja vozacNezgodaManja;

	@Version
	private long version;


	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Ucesnik() {
	}


	public String getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(String datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public byte[] getSlikaUcesnik() {
		return slikaUcesnik;
	}

	public void setSlikaUcesnik(byte[] slikaUcesnik) {
		this.slikaUcesnik = slikaUcesnik;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public VozacNezgodaManja getVozacNezgodaManja() {
		return vozacNezgodaManja;
	}

	public void setVozacNezgodaManja(VozacNezgodaManja vozacNezgodaManja) {
		this.vozacNezgodaManja = vozacNezgodaManja;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Ucesnik that = (Ucesnik) o;

		return getId() != null && Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return 31;

	}
}
