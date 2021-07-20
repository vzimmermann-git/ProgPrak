package PresentationLayer;

import DatabaseLayer.Artikel;
import DatabaseLayer.Rechnung;
import DatabaseLayer.User;
import ServiceLayer.ArtikelService;
import ServiceLayer.IServer;
import ServiceLayer.Server;
import ServiceLayer.UserService;
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
import java.util.Locale;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML private TextField usernameInput;
    @FXML private PasswordField passwordInput;

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

    @FXML private TextField searchinput;


    private Stage stage;
    private Scene scene;

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


    public void login(ActionEvent event) throws IOException {

        String username = usernameInput.getText();
        String password = passwordInput.getText();
        User user = ServerStub.getS().stub.login(username, password);

        if (user == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText("Ungültige Anmeldedaten!");

            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else if (user.getAccessLevel() == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText("Nicht freigeschaltet!");

            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else if (user.getAccessLevel() == 2) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText("Login erfolgreich!");

            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("CustomerView.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else if (user.getAccessLevel() == 3) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText("Login erfolgreich!");

            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("ManagerView.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else if (user.getAccessLevel() == 4) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText("Login erfolgreich!");

            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void registrieren(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("RegistrationView.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public void artikelDetailsAnsicht() {
        DecimalFormat f = new DecimalFormat("#0.00");

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

}