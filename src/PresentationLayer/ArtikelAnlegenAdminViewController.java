package PresentationLayer;

import DatabaseLayer.Artikel;
import DatabaseLayer.ArtikelBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ArtikelAnlegenAdminViewController implements Initializable {

    @FXML private TextField artikelbezeichnunginput;
    @FXML private TextField artikelkategorieinput;
    @FXML private ComboBox<String> artikelfskinput;
    @FXML private TextField artikelbestandinput;
    @FXML private TextField artikelnettopreisinput;
    @FXML private ComboBox<String> artikelmwstinput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        artikelfskinput.getItems().addAll("0", "6", "12", "16", "18");
        artikelmwstinput.getItems().addAll("7", "19");
    }

    public void artikelAnlegen(ActionEvent event) {
        try {
            Artikel artikel = new ArtikelBuilder()
                    .withArtikelBezeichnung(artikelbezeichnunginput.getText())
                    .withArtikelKategorie(artikelkategorieinput.getText())
                    .withArtikelAltersfreigabe(artikelfskinput.getSelectionModel().getSelectedItem())
                    .withArtikelBestand(Integer.parseInt(artikelbestandinput.getText()))
                    .withArtikelNettoPreis(Double.parseDouble(artikelnettopreisinput.getText().replace(",", ".")))
                    .withArtikelMehrwertsteuer(Double.parseDouble(artikelmwstinput.getSelectionModel().getSelectedItem()))
                    .build();

            if (ServerStub.getS().stub.insertArtikel(artikel)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Artikel anlegen");
                alert.setHeaderText("Artikel erfolgreich angelegt!");
                alert.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("ArtikelAnlegenAdminView.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Artikel anlegen");
                alert.setHeaderText("Artikel konnte nicht angelegt werden");
                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Artikel anlegen");
            alert.setHeaderText("Artikel konnte nicht angelegt werden");
            alert.showAndWait();
        }

    }

    public void backToMainView(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
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
}
