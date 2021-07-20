package ServiceLayer;

import DatabaseLayer.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


public class Server extends UnicastRemoteObject implements IServer {

    private User currentUser;

    public Server() throws RemoteException {
        super();
    }

    // Server start

    public static void main(String[] args) {
        try {
            Server server = new Server();
            Registry registry = LocateRegistry.createRegistry(5555);
            registry.rebind("Service", server);
            System.out.println("Server started!");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Userservice

    @Override
    public User login(String username, String password) throws RemoteException {
        currentUser = UserService.getU().login(username, password);
        return currentUser;
    }

    @Override
    public boolean registrieren(String benutzername, String password, String email, String vorname, String nachname, String geburtsdatum, String strasse, String hausnummer, String postleitzahl, String ort, String bezahlmethode) throws RemoteException {
        return UserService.getU().registrieren(benutzername, password, email, vorname, nachname, geburtsdatum, strasse, hausnummer, postleitzahl, ort, bezahlmethode);
    }

    @Override
    public boolean insertMitarbeiter(User user) throws RemoteException {
        return UserService.getU().mitarbeiterAnlegen(user);
    }

    @Override
    public boolean updateUser(User user) throws RemoteException {
        return UserService.getU().updateUser(user);
    }

    //Artikelservice

    @Override
    public boolean insertArtikel(Artikel artikel) throws RemoteException {
        return ArtikelService.getA().insertArtikel(artikel);
    }

    @Override
    public List<Artikel> erstelleArtikelliste() throws RemoteException {
        return ArtikelService.getA().erstelleArtikelliste();
    }

    @Override
    public boolean deleteArtikel(int artikelnummer) throws RemoteException {
        return ArtikelService.getA().deleteArtikel(artikelnummer);
    }

    @Override
    public void clearWarenkorb() throws RemoteException {
        WarenkorbService.getW().clearWarenkorb();
    }

    @Override
    public void addToWarenkorb(ArtikelWarenkorb artikelWarenkorb) throws RemoteException {
        WarenkorbService.getW().addToWarenkorb(artikelWarenkorb);
    }

    @Override
    public List<ArtikelWarenkorb> readWarenkorb() throws RemoteException {
        return WarenkorbService.getW().readWarenkorb();
    }

    @Override
    public void createRechnung(Rechnung rechnung) throws RemoteException {
        WarenkorbService.getW().erstelleRechnung(rechnung);
    }

    //Mitarbeiterservice

    @Override
    public List<User> selectFreizugebendeUser() throws RemoteException {
        return MitarbeiterService.getM().selectFreizugebendeUser();
    }

    @Override
    public boolean userFreigeben(User user) throws RemoteException {
        return MitarbeiterService.getM().userFreigeben(user);
    }

    @Override
    public boolean updateArtikel(Artikel artikel) throws RemoteException {
        return MitarbeiterService.getM().updateArtikel(artikel);
    }


    // Test
    @Override
    public User getCurrentUser() {
        return currentUser;
    }

}
