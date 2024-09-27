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
	
	/** Constantes de la classe **/
	private static final String CR = "\n";
	
	
		/**Méthodes évènement**/
		@FXML private void evtOnMouseClickedBtnAnnuler() {
			Stage stage = (Stage) btnAnnuler.getScene().getWindow(); 
			stage.close(); 	
		}
		@FXML private void evtOnMouseClickedBtnValider() {
			
		}
		
	/**Méthode initialize**/
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
