package controller;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Civilite;
import model.Person;

public class personDefinitionController {

	/** Définition des contrôles de la fenêtre **/ 
	@FXML private Label lblTitre; 
	@FXML private ComboBox<Civilite> cbxCivilite; 
	@FXML private TextField txfPersonNom; 
	@FXML private TextField txfPersonPrenom; 
	@FXML private TextField txfPersonPortable; 
	@FXML private TextField txfPersonEmail; 
	@FXML private TextField txfPersonAdresse; 
	@FXML private TextField txfPersonCodePostal; 
	@FXML private TextField txfPersonVille; 
	@FXML private DatePicker dapPersonDateNaissance; 
	@FXML private Button btnAnnuler; 
	@FXML private Button btnValider; 
	@FXML private TextArea txaErreurs;

	/** Attributs de la classe **/
	private Person person = null;
	private ObservableList<Civilite> listeCivilite = FXCollections.observableArrayList();
	public boolean isBtnValiderClicked = false;

	/** Constantes de la classe **/
	private static final String CR = "\n";

	/** Méthodes évènement **/
	@FXML private void evtOnMouseClickedBtnAnnuler() {
		Stage stage = (Stage) btnAnnuler.getScene().getWindow(); 
		stage.close();     
	}

	@FXML
	private void evtOnMouseClickedBtnValider() {
		String erreur = "";

		if (txfPersonNom.getText().isBlank()) {
			erreur += "Saisir le nom\n";
		}
		if (txfPersonPrenom.getText().isBlank()) {
			erreur += "Saisir le prénom\n";
		}
		if (txfPersonPortable.getText().isBlank() || txfPersonPortable.getText().length() != 14) {
			erreur += "Saisir un portable valide\n";
		}
		if (txfPersonEmail.getText().isBlank() || !txfPersonEmail.getText().contains("@")) {
			erreur += "Saisir une adresse Email valide\n";
		}
		if (txfPersonAdresse.getText().isBlank()) {
			erreur += "Saisir l'adresse\n";
		}
		if (txfPersonCodePostal.getText().isBlank()) {
			erreur += "Saisir le code postal\n";
		}
		if (txfPersonVille.getText().isBlank()) {
			erreur += "Saisir la ville\n";
		}
		if (dapPersonDateNaissance.getValue() == null) {
			erreur += "Saisir la date de naissance\n";
		}

		if (!erreur.isEmpty()) {
			txaErreurs.setVisible(true);
			txaErreurs.setText(erreur);
		} else {
			validerPersonne();
			isBtnValiderClicked = true;
		}
	}
	public boolean isBtnValiderClicked() {
		return isBtnValiderClicked;
	}

	public void setBtnValiderClicked(boolean isBtnValiderClicked) {
		this.isBtnValiderClicked = isBtnValiderClicked;
	}

	private void validerPersonne() {


		person = new Person();

		person.setPersonNom(txfPersonNom.getText());
		person.setPersonPrenom(txfPersonPrenom.getText());
		person.setPersonPortable(txfPersonPortable.getText());
		person.setPersonEmail(txfPersonEmail.getText());
		person.setPersonAdresse(txfPersonAdresse.getText());
		person.setPersonCodePostal(txfPersonCodePostal.getText());
		person.setPersonVille(txfPersonVille.getText());
		person.setPersonDateDeNaissance(dapPersonDateNaissance.getValue());
		Stage stage = (Stage) btnAnnuler.getScene().getWindow();
		stage.close();	

	}


	public Person getPerson() {
		return person;
	}


	/** Méthode initialize **/
	@FXML private void initialize() {
		/** Remplissage de la combobox des civilités  
		 * et affectation à la combobox  
		 **/ 
		listeCivilite.add(new Civilite(1, "Monsieur", "M.")); 
		listeCivilite.add(new Civilite(2, "Madame", "Mme")); 
		cbxCivilite.setItems(listeCivilite); 

		dapPersonDateNaissance.setValue(LocalDate.now().minusYears(18)); 
		txaErreurs.setVisible(false); 
	}
}