package ServiceLayer;

import DatabaseLayer.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

public class WarenkorbService {

    ArtikelWarenkorbDAO artikelWarenkorbDAO = new ArtikelWarenkorbDAOImpl();
    RechnungDAO rechnungDAO = new RechnungDAOImpl();

    private static WarenkorbService warenkorbService = null;

    private WarenkorbService() {
    }

    public static WarenkorbService getW() {
        if (warenkorbService == null) {
            warenkorbService = new WarenkorbService();
        } return warenkorbService;
    }

    public void clearWarenkorb() {
        artikelWarenkorbDAO.clearWarenkorb();
    }

    public void addToWarenkorb(ArtikelWarenkorb artikelWarenkorb) {
        artikelWarenkorbDAO.addToWarenkorb(artikelWarenkorb);
    }

    public List<ArtikelWarenkorb> readWarenkorb() {
        return artikelWarenkorbDAO.readWarenkorb();
    }

    public void erstelleRechnung(Rechnung rechnung) {
        rechnungDAO.createRechnung(rechnung);
    }

    public void generiereRechnung(Rechnung rechnung) {

        DecimalFormat format = new DecimalFormat("#0.00");
        try {

            FileWriter writer = new FileWriter(rechnung.getRechnungsnummer() + ".txt");
            writer.write("Datum: " + rechnung.getDatum() + "\n");
            writer.write("Rechnungsnummer: " + rechnung.getRechnungsnummer() + "\n");
            writer.write("Kundennummer: " + rechnung.getKundennummer() + "\n");
            writer.write("Vorname: " + rechnung.getVorname() + "\n");
            writer.write("Nachname: " + rechnung.getNachname() + "\n");
            writer.write("Straße: " + rechnung.getStrasse() + " " + rechnung.getHausnummer() + "\n");
            writer.write("Ort: " + rechnung.getPlz() + " " + rechnung.getOrt() + "\n");

            rechnung.setWarenkorb(WarenkorbService.warenkorbService.readWarenkorb());

            int pos = 0;
            double gesPreis = 0;

            for (ArtikelWarenkorb a : rechnung.getWarenkorb()) {
                writer.write("\n");
                pos++;
                writer.write("Pos. " + pos + "\t");
                writer.write("Artikelnummer: " + a.getArtikelNummer() + "\t");
                writer.write("Bezeichnung: " + String.format("%-25s", a.getArtikelBezeichnung()) + "\t");
                writer.write("Menge: " + String.format("%-10s", a.getArtikelMenge()) + "\t");
                writer.write("Preis: " + String.format("%-10s", format.format(a.getArtikelPreis())) + "\t");
                gesPreis = gesPreis + a.getArtikelPreis();
            }
            writer.write("\nGesamtpreis: " + format.format(gesPreis));

            writer.close();

            // An dieser Stelle könnte man direkt die Funktion updateArtikel setzen und für jeden Artikel ein Update machen und die Bestände anpassen
            // Aus Zeitmangel leider nicht umgesetzt

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
