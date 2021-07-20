package DatabaseLayer;

public class UserBuilder {

    private int kundennummer;
    private String benutzername;
    private String passwort;
    private String email;
    private String vorname;
    private String nachname;
    private String geburtsdatum;
    private String strasse;
    private int hausnummer;
    private int postleitzahl;
    private String ort;
    private String bezahlmethode;
    private int accessLevel;



    public UserBuilder withKundennummer(int kundennummer) {
        this.kundennummer = kundennummer;
        return this;
    }

    public UserBuilder withBenutzername(String benutzername) {
        this.benutzername = benutzername;
        return this;
    }

    public UserBuilder withPasswort(String passwort) {
        this.passwort = passwort;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withVorname(String vorname) {
        this.vorname = vorname;
        return this;
    }

    public UserBuilder withNachname(String nachname) {
        this.nachname = nachname;
        return this;
    }

    public UserBuilder withGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
        return this;
    }

    public UserBuilder withStrasse(String strasse) {
        this.strasse = strasse;
        return this;
    }

    public UserBuilder withHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
        return this;
    }

    public UserBuilder withPostleitzahl(int postleitzahl) {
        this.postleitzahl = postleitzahl;
        return this;
    }

    public UserBuilder withOrt(String ort) {
        this.ort = ort;
        return this;
    }

    public UserBuilder withBezahlmethode(String bezahlmethode) {
        this.bezahlmethode = bezahlmethode;
        return this;
    }

    public UserBuilder withAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
        return this;
    }

    // Getter


    public int getKundennummer() {
        return kundennummer;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getEmail() {
        return email;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getGeburtsdatum() {
        return geburtsdatum;
    }

    public String getStrasse() {
        return strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public int getPostleitzahl() {
        return postleitzahl;
    }

    public String getOrt() {
        return ort;
    }

    public String getBezahlmethode() {
        return bezahlmethode;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    // Build method

    public User build() {
        return new User(this);
    }
}
