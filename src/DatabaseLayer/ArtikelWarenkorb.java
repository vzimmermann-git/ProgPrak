package DatabaseLayer;

import java.io.Serializable;

public class ArtikelWarenkorb implements Serializable {

    private int artikelNummer;
    private String artikelBezeichnung;
    private int artikelMenge;
    private double artikelPreis;

    public ArtikelWarenkorb(int artikelNummer, String artikelBezeichnung, int artikelMenge, double artikelPreis) {
        this.artikelNummer = artikelNummer;
        this.artikelBezeichnung = artikelBezeichnung;
        this.artikelMenge = artikelMenge;
        this.artikelPreis = artikelPreis;
    }

    public int getArtikelNummer() {
        return artikelNummer;
    }

    public void setArtikelNummer(int artikelNummer) {
        this.artikelNummer = artikelNummer;
    }

    public String getArtikelBezeichnung() {
        return artikelBezeichnung;
    }

    public void setArtikelBezeichnung(String artikelBezeichnung) {
        this.artikelBezeichnung = artikelBezeichnung;
    }

    public int getArtikelMenge() {
        return artikelMenge;
    }

    public void setArtikelMenge(int artikelMenge) {
        this.artikelMenge = artikelMenge;
    }

    public double getArtikelPreis() {
        return artikelPreis;
    }

    public void setArtikelPreis(double artikelPreis) {
        this.artikelPreis = artikelPreis;
    }

    @Override
    public String toString() {
        return "ArtikelWarenkorb{" +
                "artikelNummer=" + artikelNummer +
                ", artikelBezeichnung='" + artikelBezeichnung + '\'' +
                ", artikelMenge=" + artikelMenge +
                ", artikelPreis=" + artikelPreis +
                '}';
    }
}
