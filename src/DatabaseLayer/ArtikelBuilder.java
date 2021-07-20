package DatabaseLayer;

public class ArtikelBuilder {

    private int artikelnummer;
    private String artikelBezeichnung;
    private String artikelKategorie;
    private String artikelAltersfreigabe;
    private int artikelBestand;
    private double artikelNettoPreis;
    private double artikelMehrwertsteuer;

    public ArtikelBuilder withArtikelnummer(int artikelnummer) {
        this.artikelnummer = artikelnummer;
        return this;
    }

    public ArtikelBuilder withArtikelBezeichnung(String artikelBezeichnung) {
        this.artikelBezeichnung = artikelBezeichnung;
        return this;
    }

    public ArtikelBuilder withArtikelKategorie(String artikelKategorie) {
        this.artikelKategorie = artikelKategorie;
        return this;
    }

    public ArtikelBuilder withArtikelAltersfreigabe(String artikelAltersfreigabe) {
        this.artikelAltersfreigabe = artikelAltersfreigabe;
        return this;
    }

    public ArtikelBuilder withArtikelBestand(int artikelBestand) {
        this.artikelBestand = artikelBestand;
        return this;
    }

    public ArtikelBuilder withArtikelNettoPreis(double artikelNettoPreis) {
        this.artikelNettoPreis = artikelNettoPreis;
        return this;
    }

    public ArtikelBuilder withArtikelMehrwertsteuer(double artikelMehrwertsteuer) {
        this.artikelMehrwertsteuer = artikelMehrwertsteuer;
        return this;
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

    // Build method

    public Artikel build() {
        return new Artikel(this);
    }
}
