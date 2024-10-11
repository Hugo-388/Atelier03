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
import static bdd.CiviliteBdd.selectAllCivilites;



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
	private String traitement = ""; 
	private int identifiantCreate = 0; 

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

		if(traitement.equals("create")) { 
			person = new Person(); 
			person.setPersonId(identifiantCreate); 
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

		listeCivilite = selectAllCivilites();
		/** Remplissage de la combobox des civilités  
		 * et affectation à la combobox  
		 **/ 
		//listeCivilite.add(new Civilite(1, "Monsieur", "M.")); 
		//listeCivilite.add(new Civilite(2, "Madame", "Mme")); 
		cbxCivilite.setItems(listeCivilite); 

		/** Monsieur par défaut **/ 
		int index = 0; 
		for(Civilite civilite : listeCivilite) { 
			if(civilite.getCiviliteLbl().equals("Monsieur"))break; 
			index++; 
		} 
		cbxCivilite.getSelectionModel().select(index);

		dapPersonDateNaissance.setValue(LocalDate.now().minusYears(18)); 
		txaErreurs.setVisible(false); 
	}

	public void setLblTitre(String titre) { 
		lblTitre.setText(titre); 
	} 

	public void setTraitement(String trt) { 
		//TODO A verifier si cela marche
		this.traitement = trt; 
		if(traitement.equals("delete")) { 
			txfPersonNom.setDisable(true); 
			txfPersonPrenom.setDisable(true); 
			txfPersonPortable.setDisable(true); 
			txfPersonEmail.setDisable(true); 
			txfPersonAdresse.setDisable(true); 
			txfPersonCodePostal.setDisable(true); 
			txfPersonVille.setDisable(true); 
			cbxCivilite.setDisable(true); 
			dapPersonDateNaissance.setDisable(true); 
		} 
	} 
	public void setPerson(Person personne) { 

		this.person = personne; 
		int index   = 0; 
		for(Civilite civilite :listeCivilite) { 
			if(civilite.getCiviliteIdt()==person.getPersonCivilite()) break; 
			index++; 
		} 
		cbxCivilite.getSelectionModel().select(index); 
		txfPersonNom.setText(person.getPersonNom()); 
		txfPersonPrenom.setText(person.getPersonPrenom()); 
		txfPersonPortable.setText(person.getPersonPortable()); 
		txfPersonEmail.setText(person.getPersonEmail()); 
		txfPersonAdresse.setText(person.getPersonAdresse()); 
		txfPersonCodePostal.setText(person.getPersonCodePostal()); 
		txfPersonVille.setText(person.getPersonVille()); 
		dapPersonDateNaissance.setValue(person.getPersonDateDeNaissance()); 
	} 

	public void setIdentifiantCreate(int newIdentifiant) { 
		this.identifiantCreate = newIdentifiant; 
	}
} 
