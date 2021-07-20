package PresentationLayer;

import DatabaseLayer.User;
import DatabaseLayer.UserBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class MitarbeiteranlegenViewController implements Initializable {

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


    public void mitarbeiterAnlegen(ActionEvent event) {
        try {
            User user = new UserBuilder()
                    .withBenutzername(benutzernameInput.getText())
                    .withPasswort(passwordInput.getText())
                    .withEmail(emailInput.getText())
                    .withVorname(vornameInput.getText())
                    .withNachname(nachnameInput.getText())
                    .withGeburtsdatum(geburtsdatumInput.getText())
                    .withStrasse(strasseInput.getText())
                    .withHausnummer(Integer.parseInt(hausnummerInput.getText()))
                    .withPostleitzahl(Integer.parseInt(postleitzahlInput.getText()))
                    .withOrt(ortInput.getText())
                    .withBezahlmethode(bezahlmethodeInput.getSelectionModel().getSelectedItem())
                    .withAccessLevel(3)
                    .build();

            if (ServerStub.getS().stub.insertMitarbeiter(user)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mitarbeiter anlegen");
                alert.setHeaderText("Mitarbeiter erfolgreich angelegt!");
                alert.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("AdminView.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Mitarbeiter anlegen");
                alert.setHeaderText("Mitarbeiter anlegen fehlgeschlagen!");
                alert.showAndWait();
            }


        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Mitarbeiter anlegen");
            alert.setHeaderText("Mitarbeiter anlegen fehlgeschlagen!");
            alert.showAndWait();
        }
    }

    public void backToMain(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bezahlmethodeInput.getItems().addAll("Lastschrift", "Paypal", "Kreditkarte", "Rechnung");
    }
}
