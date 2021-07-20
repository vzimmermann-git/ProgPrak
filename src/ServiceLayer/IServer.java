package ServiceLayer;

import DatabaseLayer.Artikel;
import DatabaseLayer.ArtikelWarenkorb;
import DatabaseLayer.Rechnung;
import DatabaseLayer.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IServer extends Remote {

    //Userservice

    User login(String username, String password) throws RemoteException;

    boolean registrieren(String benutzername, String password, String email, String vorname, String nachname, String geburtsdatum, String strasse, String hausnummer, String postleitzahl, String ort, String bezahlmethode) throws RemoteException;

    boolean insertMitarbeiter(User user) throws RemoteException;

    boolean updateUser(User user) throws RemoteException;

    //Artikelservice

    boolean insertArtikel(Artikel artikel) throws RemoteException;

    List<Artikel> erstelleArtikelliste() throws RemoteException;

    boolean deleteArtikel(int artikelnummer) throws RemoteException;
    //Warenkorbservice

    void clearWarenkorb() throws RemoteException;

    void addToWarenkorb(ArtikelWarenkorb artikelWarenkorb) throws RemoteException;

    List<ArtikelWarenkorb> readWarenkorb() throws RemoteException;

    void createRechnung(Rechnung rechnung) throws RemoteException;

    //Mitarbeiterservice

    List<User> selectFreizugebendeUser() throws RemoteException;

    boolean userFreigeben(User user) throws RemoteException;

    boolean updateArtikel(Artikel artikel) throws RemoteException;

    // Test

    User getCurrentUser() throws RemoteException;

}
