package controller;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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
		personDonnees.add(new Person(0, "Moureaux", "Hugo"));
		personDonnees.add(new Person(1, "Dupont", "Marie"));
		personDonnees.add(new Person(2, "Martin", "Lucas"));
		personDonnees.add(new Person(3, "Dubois", "Sophie"));
		personDonnees.add(new Person(4, "Lefebvre", "Thomas"));

		tcNom.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getPersonNomProperty());
		tcPrenom.setCellValueFactory(cellDataFeatures -> cellDataFeatures.getValue().getPersonPrenomProperty());
		tvDonnees.setItems(personDonnees);
	}

	@FXML
	private void evtOnMouseClickedBtnRajouter() {
		try { 
			FXMLLoader loader  = new FXMLLoader(); 
			loader.setLocation(getClass().getResource("/view/PersonDefinition.fxml")); 
			AnchorPane root = (AnchorPane) loader.load();    
			Scene scene = new Scene(root); 

			personDefinitionController controller = loader.getController();
			controller.setLblTitre("Rajout d'une personne"); 
			controller.setTraitement("create"); 
			int idtMax = 0; 
			for(Person personne : personDonnees) { 
				if(personne.getPersonId()>idtMax) idtMax = 
						personne.getPersonId(); 
			} 
			idtMax++; 
			controller.setIdentifiantCreate(idtMax);
			Stage definitionStage = new Stage(); 
			definitionStage.setScene(scene); 
			definitionStage.setTitle("Définition d'une personne"); 
			definitionStage.initModality(Modality.APPLICATION_MODAL);
			definitionStage.showAndWait(); 

			if (controller.isBtnValiderClicked()) {
				personDonnees.add(controller.getPerson());
			}

		} catch(Exception e) { 
			e.printStackTrace(); 
		}
	}

	@FXML
	private void evtOnMouseClickedBtnModifier() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/PersonDefinition.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			Stage definitionStage = new Stage();
			definitionStage.setScene(scene);
			definitionStage.setTitle("Définition d'une personne");

			personDefinitionController controller = loader.getController();

			controller.setLblTitre("Modification d'une personne");
			controller.setTraitement("update");
			controller.setPerson(personSelected);

			definitionStage.initModality(Modality.APPLICATION_MODAL);
			definitionStage.showAndWait();

			if (controller.isBtnValiderClicked()) {
				personSelected = controller.getPerson();
				for (Person personMaj : personDonnees) {
					if (personMaj.getPersonId() == personSelected.getPersonId()) {
						personMaj.setPersonNom(personSelected.getPersonNom());
						personMaj.setPersonPrenom(personSelected.getPersonPrenom());
						personMaj.setPersonCivilite(personSelected.getPersonCivilite());
						personMaj.setPersonPortable(personSelected.getPersonPortable());
						personMaj.setPersonEmail(personSelected.getPersonEmail());
						personMaj.setPersonAdresse(personSelected.getPersonAdresse());
						personMaj.setPersonCodePostal(personSelected.getPersonCodePostal());
						personMaj.setPersonVille(personSelected.getPersonVille());
						personMaj.setPersonDateDeNaissance(personSelected.getPersonDateDeNaissance());
					}
				}
				tvDonnees.refresh(); 

				lblCivilite.setText((personSelected.getPersonCivilite() == 1) ? "Monsieur" : "Madame");
				lblNom.setText(personSelected.getPersonNom());
				lblPrenom.setText(personSelected.getPersonPrenom());
				lblPortable.setText(personSelected.getPersonPortable());
				lblEmail.setText(personSelected.getPersonEmail());
				lblAdresse.setText(personSelected.getPersonAdresse());
				lblCp.setText(personSelected.getPersonCodePostal());
				lblVille.setText(personSelected.getPersonVille());
				lblDateDeNaissance.setText(personSelected.getPersonDateDeNaissance().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void evtOnMouseClickedBtnSupprimer() {
		try { 
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/PersonDefinition.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
			Stage definitionStage = new Stage();
			definitionStage.setScene(scene);
			definitionStage.setTitle("Suppression d'une personne");

			personDefinitionController controller = loader.getController();
			controller.setLblTitre("Suppression d'une personne");
			controller.setPerson(personSelected);
			controller.setTraitement("delete");

			definitionStage.initModality(Modality.APPLICATION_MODAL);
			definitionStage.showAndWait();

			if (controller.isBtnValiderClicked()) {
				personSelected = controller.getPerson();

				int index = 0;
				for (Person personSuppr : personDonnees) {
					if (personSuppr.getPersonId() == personSelected.getPersonId()) break;
					index++;
				}
				personDonnees.remove(index);

				lblCivilite.setText(""); 
				lblNom.setText(""); 
				lblPrenom.setText(""); 
				lblPortable.setText(""); 
				lblEmail.setText(""); 
				lblAdresse.setText(""); 
				lblCp.setText(""); 
				lblVille.setText(""); 
				lblDateDeNaissance.setText(""); 
				btnModifier.setDisable(true); 
				btnSupprimer.setDisable(true); 
			}
		} catch(Exception e) { 
			e.printStackTrace(); 
		}
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
		lblDateDeNaissance.setText(personSelected.getPersonDateDeNaissance().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
	}
}