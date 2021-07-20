package DatabaseLayer;


import PresentationLayer.ServerStub;

import java.io.Serializable;

public class Artikel implements Serializable {

    private int artikelnummer;
    private String artikelBezeichnung;
    private String artikelKategorie;
    private String artikelAltersfreigabe;
    private int artikelBestand;
    private double artikelNettoPreis;
    private double artikelMehrwertsteuer;

    public Artikel(ArtikelBuilder artikelBuilder) {

        this.artikelnummer = artikelBuilder.getArtikelnummer();
        this.artikelBezeichnung = artikelBuilder.getArtikelBezeichnung();
        this.artikelKategorie = artikelBuilder.getArtikelKategorie();
        this.artikelAltersfreigabe = artikelBuilder.getArtikelAltersfreigabe();
        this.artikelBestand = artikelBuilder.getArtikelBestand();
        this.artikelNettoPreis = artikelBuilder.getArtikelNettoPreis();
        this.artikelMehrwertsteuer = artikelBuilder.getArtikelMehrwertsteuer();

    }

    // Getter

    public int getArtikelnummer() {
        return artikelnummer;
    }

    public String getArtikelBezeichnung() {
        return artikelBezeichnung;
    }

    public String getArtikelKategorie() {
        return artikelKategorie;
    }

    public String getArtikelAltersfreigabe() {
        return artikelAltersfreigabe;
    }

    public int getArtikelBestand() {
        return artikelBestand;
    }

    public double getArtikelNettoPreis() {
        return artikelNettoPreis;
    }

    public double getArtikelMehrwertsteuer() {
        return artikelMehrwertsteuer;
    }

    // Setter

    public void setArtikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
    }

    public void setArtikelBezeichnung(String artikelBezeichnung) {
        this.artikelBezeichnung = artikelBezeichnung;
    }

    public void setArtikelKategorie(String artikelKategorie) {
        this.artikelKategorie = artikelKategorie;
    }

    public void setArtikelAltersfreigabe(String artikelAltersfreigabe) {
        this.artikelAltersfreigabe = artikelAltersfreigabe;
    }

    public void setArtikelBestand(int artikelBestand) {
        this.artikelBestand = artikelBestand;
    }

    public void setArtikelNettoPreis(double artikelNettoPreis) {
        this.artikelNettoPreis = artikelNettoPreis;
    }

    public void setArtikelMehrwertsteuer(double artikelMehrwertsteuer) {
        this.artikelMehrwertsteuer = artikelMehrwertsteuer;
    }

    // to String

    @Override
    public String toString() {
        return "Artikel{" +
                "artikelnummer=" + artikelnummer +
                ", artikelBezeichnung='" + artikelBezeichnung + '\'' +
                ", artikelKategorie='" + artikelKategorie + '\'' +
                ", artikelAltersfreigabe='" + artikelAltersfreigabe + '\'' +
                ", artikelBestand=" + artikelBestand +
                ", artikelNettoPreis=" + artikelNettoPreis +
                ", artikelMehrwertsteuer=" + artikelMehrwertsteuer +
                '}';
    }
}
