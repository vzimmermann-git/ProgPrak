package PresentationLayer;



import DatabaseLayer.Rechnung;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class KasseViewController implements Initializable {

    @FXML private AnchorPane zahlung;
    @FXML private ComboBox<String> selectZahlung;
    @FXML private TextField vornameinput;
    @FXML private TextField nachnameinput;
    @FXML private TextField strasseinput;
    @FXML private TextField hausnummerinput;
    @FXML private TextField plzinput;
    @FXML private TextField ortinput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectZahlung.getItems().addAll("Rechnung", "Lastschrift", "Paypal", "Kreditkarte");
        try {
            vornameinput.setText(ServerStub.getS().stub.getCurrentUser().getVorname());
            nachnameinput.setText(ServerStub.getS().stub.getCurrentUser().getNachname());
            strasseinput.setText(ServerStub.getS().stub.getCurrentUser().getStrasse());
            hausnummerinput.setText(Integer.toString(ServerStub.getS().stub.getCurrentUser().getHausnummer()));
            plzinput.setText(Integer.toString(ServerStub.getS().stub.getCurrentUser().getPostleitzahl()));
            ortinput.setText(ServerStub.getS().stub.getCurrentUser().getOrt());

        } catch (Exception e) {
            e.printStackTrace();
        }

        changeZahlung();
    }

    public void changeZahlung() {
            selectZahlung.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {

                zahlung.getChildren().clear();

                switch(newValue) {
                    case "Lastschrift":
                        try {
                            zahlung.getChildren().add(FXMLLoader.load(getClass().getResource("Lastschrift.fxml")));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Paypal":
                        try {
                            zahlung.getChildren().add(FXMLLoader.load(getClass().getResource("Paypal.fxml")));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Rechnung":
                        try {
                            zahlung.getChildren().add(FXMLLoader.load(getClass().getResource("Rechnung.fxml")));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "Kreditkarte":
                        try {
                            zahlung.getChildren().add(FXMLLoader.load(getClass().getResource("Kreditkarte.fxml")));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("Ein Fehler ist aufgetreten!");
                        break;
                }

            });

    }

    public void cashOut() {
        try {

            Rechnung.getR().setDatum(LocalDate.now());
            Rechnung.getR().setKundennummer(ServerStub.getS().stub.getCurrentUser().getKundennummer());
            Rechnung.getR().setVorname(vornameinput.getText());
            Rechnung.getR().setNachname(nachnameinput.getText());
            Rechnung.getR().setStrasse(strasseinput.getText());
            Rechnung.getR().setHausnummer(Integer.parseInt(hausnummerinput.getText()));
            Rechnung.getR().setPlz(Integer.parseInt(plzinput.getText()));
            Rechnung.getR().setOrt(ortinput.getText());

            ServerStub.getS().stub.createRechnung(Rechnung.getR());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}