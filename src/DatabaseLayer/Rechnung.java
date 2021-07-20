package DatabaseLayer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Rechnung implements Serializable {

    private LocalDate datum;
    private int rechnungsnummer;
    private int kundennummer;
    private String vorname;
    private String nachname;
    private String strasse;
    private int hausnummer;
    private int plz;
    private String ort;
    private List<ArtikelWarenkorb> warenkorb;

    private static Rechnung rechnung = null;

    private Rechnung() {
    }

    public static Rechnung getR() {
        if (rechnung == null) {
            rechnung = new Rechnung();
        } return rechnung;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public int getRechnungsnummer() {
        return rechnungsnummer;
    }

    public void setRechnungsnummer(int rechnungsnummer) {
        this.rechnungsnummer = rechnungsnummer;
    }

    public int getKundennummer() {
        return kundennummer;
    }

    public void setKundennummer(int kundennummer) {
        this.kundennummer = kundennummer;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public List<ArtikelWarenkorb> getWarenkorb() {
        return warenkorb;
    }

    public void setWarenkorb(List<ArtikelWarenkorb> warenkorb) {
        this.warenkorb = warenkorb;
    }

    @Override
    public String toString() {
        return "Rechnung{" +
                "datum=" + datum +
                ", rechnungsnummer=" + rechnungsnummer +
                ", kundennummer=" + kundennummer +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", strasse='" + strasse + '\'' +
                ", hausnummer=" + hausnummer +
                ", plz=" + plz +
                ", ort='" + ort + '\'' +
                ", warenkorb=" + warenkorb +
                '}';
    }
}
