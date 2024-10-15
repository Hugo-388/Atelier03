package model;

import java.time.LocalDate;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	private int personId;
	private int personCivilite;
	private StringProperty personNom;
	private StringProperty personPrenom;
	private String personPortable;
	private String personEmail;
	private LocalDate personDateDeNaissance;
	private String personAdresse;
	private String personCodePostal;
	private String personVille;
	private String personLogin;
	private String personPwd;


	public Person() {
		super();
	}



	public Person(int newPersonIdt, String newPersonNom, String newPersonPrenom) {
		super();
		this.personId = newPersonIdt;
		this.personCivilite = 1;
		this.personNom = new SimpleStringProperty(newPersonNom);
		this.personPrenom = new SimpleStringProperty(newPersonPrenom);
		this.personPortable = "Portable à saisir";
		this.personEmail = "Email à saisir";
		this.personDateDeNaissance = LocalDate.of(1900, 1, 1);
		this.personAdresse = "Adresse à saisir";
		this.personCodePostal = "Code postal à saisir";
		this.personVille = "Ville à saisr";
	}




	public Person(int personidt, int personCivilite, String personNom, String personPrenom, String personPortable,
			String personEmail, LocalDate personDateNaissance, String personAdresse, String personCodePostal,
			String personVille, String personLogin, String personPwd) {
		// TODO Auto-generated constructor stub
		super();
		this.personId = personidt;
		this.personCivilite = personCivilite;
		this.personNom = new SimpleStringProperty(personNom);
		this.personPrenom = new SimpleStringProperty(personPrenom);
		this.personPortable = personPortable;
		this.personEmail = personEmail;
		this.personDateDeNaissance = personDateNaissance;
		this.personAdresse = personAdresse;
		this.personCodePostal = personCodePostal;
		this.personVille = personVille;
		this.personLogin = personLogin;
		this.personPwd = personPwd;

	}



	public void setPersonId(int personIdt) {
		this.personId = personIdt;
	}



	public int getPersonCivilite() {
		return personCivilite;
	}



	public void setPersonCivilite(int personCivilite) {
		this.personCivilite = personCivilite;
	}



	public String getPersonNom() {
		return personNom.get();
	}

	public StringProperty getPersonNomProperty() {
		return personNom;
	}

	public void setPersonNom(String personNom) {
		this.personNom = new SimpleStringProperty(personNom);
	}



	public String getPersonPrenom() {
		return personPrenom.get();
	}


	public StringProperty getPersonPrenomProperty() {
		return personPrenom;
	}


	public void setPersonPrenom(String personPrenom) {
		this.personPrenom = new SimpleStringProperty(personPrenom);
	}



	public String getPersonPortable() {
		return personPortable;
	}



	public void setPersonPortable(String personPortable) {
		this.personPortable = personPortable;
	}



	public String getPersonEmail() {
		return personEmail;
	}



	public void setPersonEmail(String personEmail) {
		this.personEmail = personEmail;
	}



	public LocalDate getPersonDateDeNaissance() {
		return personDateDeNaissance;
	}



	public void setPersonDateDeNaissance(LocalDate personDateDeNaissance) {
		this.personDateDeNaissance = personDateDeNaissance;
	}



	public String getPersonAdresse() {
		return personAdresse;
	}



	public void setPersonAdresse(String personAdresse) {
		this.personAdresse = personAdresse;
	}



	public String getPersonCodePostal() {
		return personCodePostal;
	}



	public void setPersonCodePostal(String personCodePostal) {
		this.personCodePostal = personCodePostal;
	}



	public String getPersonVille() {
		return personVille;
	}



	public String getPersonLogin() {
		return personLogin;
	}



	public void setPersonLogin(String personLogin) {
		this.personLogin = personLogin;
	}



	public String getPersonPwd() {
		return personPwd;
	}



	public void setPersonPwd(String personPwd) {
		this.personPwd = personPwd;
	}



	public void setPersonVille(String personVille) {
		this.personVille = personVille;
	}


	public int getPersonId() {
		return personId;
	}




}
