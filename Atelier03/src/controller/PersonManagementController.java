package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;

public class PersonManagementController {

	@FXML private TableView<Person> tvDonnees;
	@FXML private TableColumn<Person, String> tcNom;  
	@FXML private TableColumn<Person, String> tcPrenom;
	@FXML private Label lblNom;
	@FXML private Label lblPrenom;
	@FXML private Label lblCivilite;
	@FXML private Label lblPortable;
	@FXML private Label lblEmail;
	@FXML private Label lblDateDeNaissance;
	@FXML private Label lblAdresse;
	@FXML private Label lblCp;
	@FXML private Label lblVille;
	@FXML private Button btnRajouter;
	@FXML private Button btnModifier;
	@FXML private Button btnSupprimer;

	private ObservableList<Person> personDonnees = FXCollections.observableArrayList();
	private Person personSelected;

	@FXML
	private void initialize() {
		personDonnees.add(new Person(1, "Moureaux", "Hugo"));
		personDonnees.add(new Person(1, "Moureaux", "Hugo"));
		personDonnees.add(new Person(1, "Moureaux", "Hugo"));
		personDonnees.add(new Person(1, "Moureaux", "Hugo"));
		personDonnees.add(new Person(1, "Moureaux", "Hugo"));

		tcNom.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getPersonNomProperty());
		tcPrenom.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getPersonPrenomProperty());
		tvDonnees.setItems(personDonnees);
	}

	@FXML
	private void evtOnMouseClickedBtnRajouter() {
		try { 
			FXMLLoader loader  = new FXMLLoader(); 
			loader.setLocation(getClass().getResource("/view/PersonDefinition.fxml")); 
			AnchorPane root    
			= (AnchorPane) loader.load();    
			Scene scene = new Scene(root); 

			personDefinitionController controler = loader.getController();
			Stage definitionStage = new Stage(); 
			definitionStage.setScene(scene); 
			definitionStage.setTitle("Définition d'une personne"); 
			definitionStage.initModality(Modality.APPLICATION_MODAL);
			definitionStage.showAndWait(); 
			
			System.out.println(controler.isBtnValiderClicked());
			if (controler.isBtnValiderClicked()) {
                personDonnees.add(controler.getPerson());
                System.out.println(controler.getPerson().getPersonEmail());
			}
				
		} catch(Exception e) { 
			e.printStackTrace(); 
		}


	}

	@FXML
	private void evtOnMouseClickedBtnModifier() {
	}

	@FXML
	private void evtOnMouseClickedBtnSupprimer() {
	}

	@FXML
	private void evtOnMousePressedTbvDonnees(MouseEvent event) {
		if(event.isPrimaryButtonDown() && event.getClickCount() == 2) {
			personSelected = tvDonnees.getSelectionModel().getSelectedItem();
			if(personSelected != null) {
				affichageDonnees();
				btnRajouter.setDisable(false);
				btnModifier.setDisable(false);
				btnSupprimer.setDisable(false);
			}
		}
	}

	private void affichageDonnees() {
		lblCivilite.setText((personSelected.getPersonCivilite() == 1) ? "Monsieur" : "Madame");
		lblNom.setText(personSelected.getPersonNom());
		lblPrenom.setText(personSelected.getPersonPrenom());
		lblPortable.setText(personSelected.getPersonPortable());
		lblEmail.setText(personSelected.getPersonEmail());
		lblAdresse.setText(personSelected.getPersonAdresse());
		lblCp.setText(personSelected.getPersonCodePostal());
		lblVille.setText(personSelected.getPersonVille());
	}
}