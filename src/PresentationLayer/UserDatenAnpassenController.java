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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ResourceBundle;

public class UserDatenAnpassenController implements Initializable {

    @FXML private Label benutzername;
    @FXML private PasswordField passwordInput;
    @FXML private TextField emailInput;
    @FXML private TextField vornameInput;
    @FXML private TextField nachnameInput;
    @FXML private TextField geburtsdatumInput;
    @FXML private TextField strasseInput;
    @FXML private TextField hausnummerInput;
    @FXML private TextField postleitzahlInput;
    @FXML private TextField ortInput;
    @FXML private ComboBox<String> bezahlmethodeInput;

    User user = ServerStub.getS().stub.getCurrentUser();

    public UserDatenAnpassenController() throws RemoteException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bezahlmethodeInput.getItems().addAll("Lastschrift", "Paypal", "Kreditkarte", "Rechnung");
        benutzername.setText(user.getBenutzername());
        passwordInput.setPromptText("Neues Passwort...");
        emailInput.setText(user.getEmail());
        vornameInput.setText(user.getVorname());
        nachnameInput.setText(user.getNachname());
        geburtsdatumInput.setText(user.getGeburtsdatum());
        strasseInput.setText(user.getStrasse());
        hausnummerInput.setText(String.valueOf(user.getHausnummer()));
        postleitzahlInput.setText(String.valueOf(user.getPostleitzahl()));
        ortInput.setText(user.getOrt());
        bezahlmethodeInput.getSelectionModel().select(user.getBezahlmethode());

    }

    public void backToMain(ActionEvent event) {
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

    public void userDatenAktualisieren(ActionEvent event) {
        try {
            if (!passwordInput.getText().isEmpty()) {
                user.setPasswort(passwordInput.getText());
            }
            user.setEmail(emailInput.getText());
            user.setVorname(vornameInput.getText());
            user.setNachname(nachnameInput.getText());
            user.setGeburtsdatum(geburtsdatumInput.getText());
            user.setStrasse(strasseInput.getText());
            user.setHausnummer(Integer.parseInt(hausnummerInput.getText()));
            user.setPostleitzahl(Integer.parseInt(postleitzahlInput.getText()));
            user.setOrt(ortInput.getText());
            user.setBezahlmethode(bezahlmethodeInput.getSelectionModel().getSelectedItem());

            ServerStub.getS().stub.updateUser(user);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Daten anpassen");
            alert.setHeaderText("Daten erfolgreich geändert! \n\n Bitte loggen Sie sich neu ein!");

            alert.showAndWait();

            Parent root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Daten anpassen");
            alert.setHeaderText("Fehler bei der Daten Eingabe. Bitte überpürfen Sie die Eingabe!");

            alert.showAndWait();
        }

    }


}
