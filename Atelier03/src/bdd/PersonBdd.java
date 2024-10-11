package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Cstes;
import model.Person;

public class PersonBdd {

	// Méthode pour sélectionner toutes les personnes
	public static ObservableList<Person> selectAllPersons() {
		// Liste observable pour stocker les données des personnes
		ObservableList<Person> listeTrier = FXCollections.observableArrayList();
		// Requête SQL pour sélectionner toutes les personnes
		String SQL = "SELECT * FROM dbo.person ORDER BY personNom, personPrenom";

		try (Connection connexion = DriverManager.getConnection(Cstes.PATH, Cstes.USER, Cstes.PWD);
				PreparedStatement statement = connexion.prepareStatement(SQL);
				ResultSet resultSet = statement.executeQuery()) {

			// Parcours des résultats
			while (resultSet.next()) {
				// Mapping des résultats à un objet Person
				Person person = map(resultSet);
				if (person != null) {
					// Ajout de la personne à la liste
					listeTrier.add(person);
				}
			}
		} catch (SQLException e) {
			// Gestion des exceptions SQL
			gestionExceptionsSQL(e, SQL);
		}

		return listeTrier;
	}

	// Méthode pour gérer les exceptions SQL
	private static void gestionExceptionsSQL(SQLException e, String sql) {
		// Logique pour gérer les exceptions SQL (à implémenter)
	}

	// Méthode privée pour mapper les résultats de la requête SQL à un objet Person
	private static Person map(ResultSet resultset) {
		// Initialisation des variables
		Person person = null;
		try {
			// Extraction des données du ResultSet
			int personidt = resultset.getInt(1);
			int personCivilite = resultset.getInt(2);
			String personNom = resultset.getString(3);
			String personPrenom = resultset.getString(4);
			String personPortable = resultset.getString(5);
			String personEmail = resultset.getString(6);
			LocalDate personDateNaissance = Instant.ofEpochMilli(resultset.getDate(7).getTime())
					.atZone(ZoneId.systemDefault()).toLocalDate();
			String personAdresse = resultset.getString(8);
			String personCodePostal = resultset.getString(9);
			String personVille = resultset.getString(10);
			String personLogin = resultset.getString(11);
			String personPwd = resultset.getString(12);
			// Création d'un objet Person avec les données extraites
			person = new Person(personidt, personCivilite, personNom, personPrenom, personPortable, personEmail,
					personDateNaissance, personAdresse, personCodePostal, personVille, personLogin, personPwd);
		} catch (Exception e) {
			// Gestion des exceptions et affichage d'un message d'erreur
			System.out.println("Erreur de la lecture de la table Person" + e);
		}
		return person;
	}
}