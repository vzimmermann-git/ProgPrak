package ServiceLayer;

import DatabaseLayer.Artikel;
import DatabaseLayer.ArtikelDAO;
import DatabaseLayer.ArtikelDAOImpl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class ArtikelService {

    private static ArtikelService artikelService = null;

    private ArtikelService() {
    }

    public static ArtikelService getA() {
        if (artikelService == null) {
            artikelService = new ArtikelService();
        } return artikelService;
    }

    ArtikelDAO artikelDAO = new ArtikelDAOImpl();

    public List<Artikel> erstelleArtikelliste() {
        List<Artikel> artikelliste = artikelDAO.selectAllArtikel();

        return artikelliste;
    }

    public boolean insertArtikel(Artikel artikel) {
        return artikelDAO.insertArtikel(artikel);
    }

    public boolean updateArtikel(Artikel artikel) {
        return artikelDAO.updateArtikel(artikel);
    }

    public boolean deleteArtikel(int artikelnummer) {
        return artikelDAO.deleteArtikel(artikelnummer);
    }
}
