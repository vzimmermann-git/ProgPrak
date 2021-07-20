package PresentationLayer;

import DatabaseLayer.ArtikelWarenkorb;
import ServiceLayer.Server;
import ServiceLayer.WarenkorbService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class ShoppingCardViewController implements Initializable {

    private DecimalFormat f = new DecimalFormat("#0.00");

    @FXML private TableView<ArtikelWarenkorb> warenkorb;
    @FXML private TableColumn<ArtikelWarenkorb, Integer> clartikelnummer1;
    @FXML private TableColumn<ArtikelWarenkorb, String> clartikelbezeichnung1;
    @FXML private TableColumn<ArtikelWarenkorb, Integer> clartikelmenge1;
    @FXML private TableColumn<ArtikelWarenkorb, Double> clartikelpreis1;
    @FXML private Label gesamtSumme;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clartikelnummer1.setCellValueFactory(new PropertyValueFactory<>("artikelNummer"));
        clartikelbezeichnung1.setCellValueFactory(new PropertyValueFactory<>("artikelBezeichnung"));
        clartikelmenge1.setCellValueFactory(new PropertyValueFactory<>("artikelMenge"));
        clartikelpreis1.setCellValueFactory(new PropertyValueFactory<>("artikelPreis"));

        ObservableList<ArtikelWarenkorb> warenkorbliste = FXCollections.observableArrayList();
        try {
            warenkorbliste.addAll(ServerStub.getS().stub.readWarenkorb());

            warenkorb.setItems(warenkorbliste);

            double summe = 0;

            for (ArtikelWarenkorb a : warenkorbliste) {
                summe = summe + a.getArtikelPreis();
            }

            gesamtSumme.setText(f.format(summe) + "€");

        } catch (Exception e) {
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

    public void backToMainView(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("CustomerView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void kasse(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("KasseView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearWarenkorb(ActionEvent event) {
        try {
            ServerStub.getS().stub.clearWarenkorb();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warenkorb leeren");
            alert.setHeaderText("Warenkorb geleert!");

            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("ShoppingCardView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
