package ServiceLayer;

import DatabaseLayer.*;

import java.rmi.RemoteException;
import java.util.List;

public class MitarbeiterService {

    UserDAO userDAO = new UserDAOImpl();
    ArtikelDAO artikelDAO = new ArtikelDAOImpl();

    private static MitarbeiterService mitarbeiterService = null;

    private MitarbeiterService() {
    }

    public static MitarbeiterService getM() {
        if (mitarbeiterService == null) {
            mitarbeiterService = new MitarbeiterService();
        } return mitarbeiterService;
    }

    public List<User> selectFreizugebendeUser() throws RemoteException {
        return userDAO.selectFreizugebendeUser();
    }

    public boolean userFreigeben(User user) {
        return userDAO.updateUser(user);
    }

    public boolean updateArtikel(Artikel artikel) {
        return artikelDAO.updateArtikel(artikel);
    }

}
