package PresentationLayer;


import DatabaseLayer.Artikel;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class AdminViewController implements Initializable {

    private DecimalFormat f = new DecimalFormat("#0.00");

    @FXML TextField searchinput;

    @FXML private TableView<Artikel> tableview;
    @FXML private TableColumn<Artikel, Integer> clartikelnummer;
    @FXML private TableColumn<Artikel, String> clartikelbezeichnung;
    @FXML private TableColumn<Artikel, String> clartikelkategorie;
    @FXML private TableColumn<Artikel, String> clartikelaltersfreigabe;
    @FXML private TableColumn<Artikel, Integer> clartikelbestand;
    @FXML private TableColumn<Artikel, Double> clartikelpreis;
    @FXML private TableColumn<Artikel, Integer> clartikelmehrwertsteuer;

    @FXML private AnchorPane detailsansicht;
    @FXML private Label artnr;
    @FXML private Label artbezeichnung;
    @FXML private Label artkategorie;
    @FXML private Label artfsk;
    @FXML private Label artbestand;
    @FXML private Label artnettopreis;
    @FXML private Label artmehrwertsteuer;
    @FXML private Label artbruttopreis;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        detailsansicht.setVisible(false);

        clartikelnummer.setCellValueFactory(new PropertyValueFactory<>("artikelnummer"));
        clartikelbezeichnung.setCellValueFactory(new PropertyValueFactory<>("artikelBezeichnung"));
        clartikelkategorie.setCellValueFactory(new PropertyValueFactory<>("artikelKategorie"));
        clartikelaltersfreigabe.setCellValueFactory(new PropertyValueFactory<>("artikelAltersfreigabe"));
        clartikelbestand.setCellValueFactory(new PropertyValueFactory<>("artikelBestand"));
        clartikelpreis.setCellValueFactory(new PropertyValueFactory<>("artikelNettoPreis"));
        clartikelmehrwertsteuer.setCellValueFactory(new PropertyValueFactory<>("artikelMehrwertsteuer"));

        try {
            ObservableList<Artikel> artikelliste = FXCollections.observableArrayList();

            artikelliste.addAll(ServerStub.getS().stub.erstelleArtikelliste());

            FilteredList<Artikel> filteredList = new FilteredList<>(artikelliste, b -> true);

            searchinput.textProperty().addListener((observable, oldValue, newValue) -> filteredList.setPredicate(artikel -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (artikel.getArtikelBezeichnung().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (artikel.getArtikelKategorie().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else
                    return false;
            }));

            tableview.setItems(filteredList);
        } catch (RemoteException e) {
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

    public void artikelDetailsAnsicht() {
        Artikel artikel = tableview.getSelectionModel().getSelectedItem();
        artnr.setText(String.valueOf(artikel.getArtikelnummer()));
        artbezeichnung.setText(artikel.getArtikelBezeichnung());
        artkategorie.setText(artikel.getArtikelKategorie());
        artfsk.setText(artikel.getArtikelAltersfreigabe());
        artbestand.setText(artikel.getArtikelBestand() + "Stk.");
        artnettopreis.setText(f.format(artikel.getArtikelNettoPreis()) + "???");
        artmehrwertsteuer.setText(artikel.getArtikelMehrwertsteuer() + "%");
        artbruttopreis.setText(f.format(artikel.getArtikelNettoPreis() * (1 + (artikel.getArtikelMehrwertsteuer() / 100))) + "???");
        detailsansicht.setVisible(true);
    }

    public void switchToArtikelAnlegen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ArtikelAnlegenadminView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToKundenFreigeben(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("KundenFreigabeAdminView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void artikelBearbeiten(ActionEvent event) throws IOException {
        Artikel artikel = tableview.getSelectionModel().getSelectedItem();

        if (artikel == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Artikel bearbeiten");
            alert.setHeaderText("Kein Artikel ausgew??hlt!");

            alert.showAndWait();
        }  else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("ArtikelBearbeitenAdminView.fxml"));
            Parent root = loader.load();

            ArtikelBearbeitenAdminViewController controller = loader.getController();
            controller.initArtikel(tableview.getSelectionModel().getSelectedItem());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }
    }

    public void switchToMitarbeiterAnlegen(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MitarbeiteranlegenView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteArtikel(ActionEvent event) {
        try {
            if (ServerStub.getS().stub.deleteArtikel(tableview.getSelectionModel().getSelectedItem().getArtikelnummer())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Artikel l??schen");
                alert.setHeaderText("Artikel wurde gel??scht!");
                alert.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Artikel l??schen");
                alert.setHeaderText("Artikel konnte nicht gel??scht werden!");

                alert.showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Artikel l??schen");
            alert.setHeaderText("Artikel konnte nicht gel??scht werden!");

            alert.showAndWait();
        }
    }



}
