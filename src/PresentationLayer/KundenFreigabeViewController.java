package PresentationLayer;

import DatabaseLayer.Artikel;
import DatabaseLayer.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class KundenFreigabeViewController implements Initializable {

    @FXML private TextField searchinput;

    @FXML private TableView<User> freizugebendeKundenListe;
    @FXML private TableColumn<User, Integer> clkundennummer;
    @FXML private TableColumn<User, String> clbenutzername;
    @FXML private TableColumn<User, String> clemail;
    @FXML private TableColumn<User, String> clvorname;
    @FXML private TableColumn<User, String> clnachname;
    @FXML private TableColumn<User, String> clgeburtsdatum;
    @FXML private TableColumn<User, String> clstrasse;
    @FXML private TableColumn<User, Integer> clhausnummer;
    @FXML private TableColumn<User, Integer> clplz;
    @FXML private TableColumn<User, String> clort;
    @FXML private TableColumn<User, String> clzahlung;
    @FXML private TableColumn<User, Integer> claccesslevel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clkundennummer.setCellValueFactory(new PropertyValueFactory<>("kundennummer"));
        clbenutzername.setCellValueFactory(new PropertyValueFactory<>("benutzername"));
        clemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clvorname.setCellValueFactory(new PropertyValueFactory<>("vorname"));
        clnachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        clgeburtsdatum.setCellValueFactory(new PropertyValueFactory<>("geburtsdatum"));
        clstrasse.setCellValueFactory(new PropertyValueFactory<>("strasse"));
        clhausnummer.setCellValueFactory(new PropertyValueFactory<>("hausnummer"));
        clplz.setCellValueFactory(new PropertyValueFactory<>("postleitzahl"));
        clort.setCellValueFactory(new PropertyValueFactory<>("ort"));
        clzahlung.setCellValueFactory(new PropertyValueFactory<>("bezahlmethode"));
        claccesslevel.setCellValueFactory(new PropertyValueFactory<>("accessLevel"));

        try {
            ObservableList<User> kundenliste = FXCollections.observableArrayList();

            kundenliste.addAll(ServerStub.getS().stub.selectFreizugebendeUser());

            FilteredList<User> filteredList = new FilteredList<>(kundenliste, b -> true);

            searchinput.textProperty().addListener((observable, oldValue, newValue) -> filteredList.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getBenutzername().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(user.getKundennummer()).contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getVorname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getNachname().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getStrasse().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getOrt().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (user.getBezahlmethode().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else
                    return false;
            }));

            freizugebendeKundenListe.setItems(filteredList);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void backToMainView(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ManagerView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logout(ActionEvent event) {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Logout");
            alert.setHeaderText("Logout successful!");

            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void freigeben(ActionEvent event) {
        try {
            User user = freizugebendeKundenListe.getSelectionModel().getSelectedItem();

            user.setAccessLevel(2);

            boolean freigegeben = ServerStub.getS().stub.userFreigeben(user);

            if (freigegeben == true) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Freigabe");
                alert.setHeaderText("User freigegeben!");

                alert.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("KundenFreigabeView.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("User Freigabe");
                alert.setHeaderText("User konnte nicht freigegeben werden!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
