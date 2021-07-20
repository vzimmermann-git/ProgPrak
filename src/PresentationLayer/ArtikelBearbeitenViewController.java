package PresentationLayer;

import DatabaseLayer.Artikel;
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

public class ArtikelBearbeitenViewController implements Initializable {

    private Artikel artikel;

    @FXML private TextField artikelbezeichnunginput;
    @FXML private TextField artikelkategorieinput;
    @FXML private ComboBox<String> artikelfskinput;
    @FXML private TextField artikelbestandinput;
    @FXML private TextField artikelnettopreisinput;
    @FXML private ComboBox<Double> artikelmwstinput;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        artikelfskinput.getItems().setAll("0", "6", "12", "16", "18");
        artikelmwstinput.getItems().setAll(7D, 19D);



    }

    public void initArtikel(Artikel artikel) {
        this.artikel = artikel;
        artikelbezeichnunginput.setText(artikel.getArtikelBezeichnung());
        artikelkategorieinput.setText(artikel.getArtikelKategorie());
        artikelfskinput.getSelectionModel().select(artikel.getArtikelAltersfreigabe());
        artikelbestandinput.setText(Integer.toString(artikel.getArtikelBestand()));
        artikelnettopreisinput.setText(Double.toString(artikel.getArtikelNettoPreis()));
        artikelmwstinput.getSelectionModel().select(artikel.getArtikelMehrwertsteuer());

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

    public void artikelAktualisieren(ActionEvent event) {
        try {

            artikel.setArtikelBezeichnung(artikelbezeichnunginput.getText());
            artikel.setArtikelKategorie(artikelkategorieinput.getText());
            artikel.setArtikelAltersfreigabe(artikelfskinput.getSelectionModel().getSelectedItem());
            artikel.setArtikelBestand(Integer.parseInt(artikelbestandinput.getText()));
            artikel.setArtikelNettoPreis(Double.parseDouble(artikelnettopreisinput.getText().replace(",", ".")));
            artikel.setArtikelMehrwertsteuer(artikelmwstinput.getSelectionModel().getSelectedItem());

            if (ServerStub.getS().stub.updateArtikel(artikel)) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aktualisieren");
                alert.setHeaderText("Artikel aktualisiert!");
                alert.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("ManagerView.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aktualisieren");
                alert.setHeaderText("Artikel konnte nicht aktualisiert werden!");
                alert.showAndWait();

            }

        } catch (Exception e) {

            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aktualisieren");
            alert.setHeaderText("Artikel konnte nicht aktualisiert werden!");
            alert.showAndWait();

        }
    }
}