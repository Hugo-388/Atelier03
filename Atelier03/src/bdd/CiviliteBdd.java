package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Civilite;
import model.Cstes;
import utilities.GestionExceptions;


public class CiviliteBdd extends GestionExceptions {

	// Méthode pour sélectionner toutes les civilités
	public static ObservableList<Civilite> selectAllCivilites() {
		// Liste observable pour stocker les données des civilités
		ObservableList<Civilite> listeDonnees = FXCollections.observableArrayList(); 
		Civilite civilite = null;
		String SQL = "";
		// Requête SQL pour sélectionner toutes les civilités
		SQL += "SELECT * FROM dbo.civilite ORDER BY civiliteLbl";

		try { 
			// Chargement du driver JDBC
			Class.forName(Cstes.DRIVER);
			// Connexion à la base de données
			Connection connexion = DriverManager.getConnection(Cstes.PATH, Cstes.USER, Cstes.PWD);
			// Création d'un statement pour exécuter la requête
			Statement statement = connexion.createStatement();
			// Exécution de la requête et récupération des résultats
			ResultSet resultSet = statement.executeQuery(SQL);
			// Parcours des résultats
			while (resultSet.next()) {
				// Mapping des résultats à un objet Civilite
				civilite = map(resultSet);
				if (civilite != null) {
					// Ajout de la civilité à la liste
					listeDonnees.add(civilite);
				}
			}
		} catch (ClassNotFoundException e) {
			// Gestion de l'exception si le driver JDBC n'est pas trouvé
			System.out.println("Impossible de trouver le driver JDBC pour SQLServer");
		} catch (SQLException e) {
			// Gestion des exceptions SQL
			gestionExceptionsStates(e, SQL);
		}
		return listeDonnees;
	}

	// Méthode pour sélectionner une seule civilité par ID
	public static Civilite selectOneCivilite(int civiliteIdt) {
		Civilite civilite = null;
		String SQL = "";
		// Requête SQL pour sélectionner une civilité par ID
		SQL += "SELECT * FROM dbo.civilite ORDER BY civiliteLbl";

		try { 
			// Chargement du driver JDBC
			Class.forName(Cstes.DRIVER);
			// Connexion à la base de données
			Connection connexion = DriverManager.getConnection(Cstes.PATH, Cstes.USER, Cstes.PWD);
			// Création d'un statement pour exécuter la requête
			Statement statement = connexion.createStatement();
			// Exécution de la requête et récupération des résultats
			ResultSet resultSet = statement.executeQuery(SQL);
			// Parcours des résultats
			while (resultSet.next()) {
				// Mapping des résultats à un objet Civilite
				civilite = map(resultSet);
			}
		} catch (ClassNotFoundException e) {
			// Gestion de l'exception si le driver JDBC n'est pas trouvé
			System.out.println("Impossible de trouver le driver JDBC pour SQLServer");
		} catch (SQLException e) {
			// Gestion des exceptions SQL
			gestionExceptionsStates(e, SQL);
		}
		return civilite;
	}

	// Méthode privée pour mapper les résultats de la requête SQL à un objet Civilite
	private static Civilite map(ResultSet resultset) {
		Civilite civilite = null;
		try {
			// Extraction des données du ResultSet
			int civiliteIdt = resultset.getInt(1);
			String civiliteLbl = resultset.getString(2);
			String civiliteLbc = resultset.getString(3);
			// Création d'un objet Civilite avec les données extraites
			civilite = new Civilite(civiliteIdt, civiliteLbl, civiliteLbc);
		} catch (Exception e) {
			// Gestion des exceptions et affichage d'un message d'erreur
			System.out.println("Erreur de la lecture de la table civilite" + e);
		}
		return civilite;
	}
}