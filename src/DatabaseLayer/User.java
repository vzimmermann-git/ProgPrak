package DatabaseLayer;

import java.io.Serializable;

public class User implements Serializable {

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

    public User(UserBuilder userBuilder) {

        this.kundennummer = userBuilder.getKundennummer();
        this.benutzername = userBuilder.getBenutzername();
        this.passwort = userBuilder.getPasswort();
        this.email = userBuilder.getEmail();
        this.vorname = userBuilder.getVorname();
        this.nachname = userBuilder.getNachname();
        this.geburtsdatum = userBuilder.getGeburtsdatum();
        this.strasse = userBuilder.getStrasse();
        this.hausnummer = userBuilder.getHausnummer();
        this.postleitzahl = userBuilder.getPostleitzahl();
        this.ort = userBuilder.getOrt();
        this.bezahlmethode = userBuilder.getBezahlmethode();
        this.accessLevel = userBuilder.getAccessLevel();
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

    // Setter

    public void setKundennummer(int kundennummer) {
        this.kundennummer = kundennummer;
    }

    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public void setGeburtsdatum(String geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setHausnummer(int hausnummer) {
        this.hausnummer = hausnummer;
    }

    public void setPostleitzahl(int postleitzahl) {
        this.postleitzahl = postleitzahl;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void setBezahlmethode(String bezahlmethode) {
        this.bezahlmethode = bezahlmethode;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    // to String


    @Override
    public String toString() {
        return "User{" +
                "kundennummer=" + kundennummer +
                ", benutzername='" + benutzername + '\'' +
                ", passwort='" + passwort + '\'' +
                ", email='" + email + '\'' +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geburtsdatum='" + geburtsdatum + '\'' +
                ", strasse='" + strasse + '\'' +
                ", hausnummer=" + hausnummer +
                ", postleitzahl=" + postleitzahl +
                ", ort='" + ort + '\'' +
                ", bezahlmethode='" + bezahlmethode + '\'' +
                ", accessLevel=" + accessLevel +
                '}';
    }
}
