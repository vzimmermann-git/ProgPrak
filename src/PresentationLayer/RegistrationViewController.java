package PresentationLayer;

import ServiceLayer.IServer;
import ServiceLayer.Server;
import ServiceLayer.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class RegistrationViewController implements Initializable {

    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private TextField benutzernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField vornameInput;
    @FXML
    private TextField nachnameInput;
    @FXML
    private TextField geburtsdatumInput;
    @FXML
    private TextField strasseInput;
    @FXML
    private TextField hausnummerInput;
    @FXML
    private TextField postleitzahlInput;
    @FXML
    private TextField ortInput;
    @FXML
    private ComboBox<String> bezahlmethodeInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bezahlmethodeInput.getItems().addAll("Lastschrift", "Paypal", "Kreditkarte", "Rechnung");
    }

    public void registrieren(ActionEvent event) throws IOException {
        String benutzername = benutzernameInput.getText();
        String password = passwordInput.getText();
        String email = emailInput.getText();
        String vorname = vornameInput.getText();
        String nachname = nachnameInput.getText();
        String geburtsdatum = geburtsdatumInput.getText();
        String strasse = strasseInput.getText();
        String hausnummer = hausnummerInput.getText();
        String postleitzahl = postleitzahlInput.getText();
        String ort = ortInput.getText();
        String bezahlmethode = bezahlmethodeInput.getSelectionModel().getSelectedItem();

        boolean isRegistration = ServerStub.getS().stub.registrieren(benutzername, password, email, vorname, nachname, geburtsdatum, strasse, hausnummer, postleitzahl, ort, bezahlmethode);

        if (isRegistration == true) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registrierung");
            alert.setHeaderText("Registrierung erfolgreich");

            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Registrierung");
            alert.setHeaderText("Registrierung fehlgeschlagen!");

            alert.showAndWait();
        }
    }
    public void backToMain(ActionEvent event) {
        try {
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
