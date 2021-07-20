package ServiceLayer;

import DatabaseLayer.User;
import DatabaseLayer.UserBuilder;
import DatabaseLayer.UserDAO;
import DatabaseLayer.UserDAOImpl;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService userService = null;

    private UserService() {
    }

    public static UserService getU() {
        if (userService == null) {
            userService = new UserService();
        } return userService;
    }

    UserDAO userDAO = new UserDAOImpl();

    public boolean registrieren(String benutzername, String password, String email, String vorname, String nachname, String geburtsdatum, String strasse, String hausnummer, String postleitzahl, String ort, String bezahlmethode) throws RemoteException {
        User user = new UserBuilder()
                .withBenutzername(benutzername)
                .withPasswort(password)
                .withEmail(email)
                .withVorname(vorname)
                .withNachname(nachname)
                .withGeburtsdatum(geburtsdatum)
                .withStrasse(strasse)
                .withHausnummer(Integer.parseInt(hausnummer))
                .withPostleitzahl(Integer.parseInt(postleitzahl))
                .withOrt(ort)
                .withBezahlmethode(bezahlmethode)
                .withAccessLevel(1)
                .build();

        try {
            userDAO.insertUser(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User login(String username, String password) {
        List<User> userlist = userDAO.selectAllUsers();

        for (User user : userlist) {
            if (user.getBenutzername().equals(username) && user.getPasswort().equals(password)) {
                WarenkorbService.getW().clearWarenkorb();
                return user;
            }
        }
        return null;
    }

    public boolean mitarbeiterAnlegen(User user) {
        try {
            userDAO.insertUser(user);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateUser(User user) {
        return userDAO.updateUser(user);
    }
}
