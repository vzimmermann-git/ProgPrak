package PresentationLayer;

import DatabaseLayer.Artikel;
import DatabaseLayer.ArtikelWarenkorb;
import ServiceLayer.ArtikelService;
import ServiceLayer.IServer;
import ServiceLayer.Server;
import ServiceLayer.WarenkorbService;
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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerViewController implements Initializable {

    private DecimalFormat f = new DecimalFormat("#0.00");

    private Stage stage;
    private Scene scene;

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

    @FXML private Spinner<Integer> spinner;

    @FXML private TextField searchinput;

    // Init Method

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99999999);
        spinner.setValueFactory(valueFactory);

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

    // GUI Design & Actions

    public void artikelDetailsAnsicht() {
            Artikel artikel = tableview.getSelectionModel().getSelectedItem();
            artnr.setText(String.valueOf(artikel.getArtikelnummer()));
            artbezeichnung.setText(artikel.getArtikelBezeichnung());
            artkategorie.setText(artikel.getArtikelKategorie());
            artfsk.setText(artikel.getArtikelAltersfreigabe());
            artbestand.setText(artikel.getArtikelBestand() + "Stk.");
            artnettopreis.setText(f.format(artikel.getArtikelNettoPreis()) + "€");
            artmehrwertsteuer.setText(artikel.getArtikelMehrwertsteuer() + "%");
            artbruttopreis.setText(f.format(artikel.getArtikelNettoPreis() * (1 + (artikel.getArtikelMehrwertsteuer() / 100))) + "€");
            detailsansicht.setVisible(true);
    }

    public void switchToWarenkorb(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ShoppingCardView.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addToWarenkorb() {
        int artikelnummer = tableview.getSelectionModel().getSelectedItem().getArtikelnummer();
        String artikelbezeichnung = tableview.getSelectionModel().getSelectedItem().getArtikelBezeichnung();
        int artikelmenge = spinner.getValue();
        double artikelpreis = Double.parseDouble(f.format((spinner.getValue() * (Double.parseDouble(artbruttopreis.getText().replace(",", ".").replace("€", ""))))).replace(",", "."));
        ArtikelWarenkorb artikelWarenkorb = new ArtikelWarenkorb(artikelnummer, artikelbezeichnung, artikelmenge, artikelpreis);
        WarenkorbService.getW().addToWarenkorb(artikelWarenkorb);
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

    public void switchToDatenAnpassen(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("UserDatenAnpassen.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
