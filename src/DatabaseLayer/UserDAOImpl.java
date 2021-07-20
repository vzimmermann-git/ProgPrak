package DatabaseLayer;

import ServiceLayer.Server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final Connection connection = Database.getConnection();

    private static final String INSERT_USERS = "INSERT INTO users (benutzername, passwort, email, vorname, nachname, geburtsdatum, straße, hausnummer, postleitzahl, ort, bezahlmethode, accesslevel) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_USERS = "UPDATE users SET kundennummer = ?, benutzername = ?, passwort = ?, email = ?, vorname = ?, nachname = ?, geburtsdatum = ?, straße = ?, hausnummer = ?, postleitzahl = ?, ort = ?, bezahlmethode = ?, accesslevel = ? WHERE kundennummer = ?;";
    private static final String SELECT_USER_BY_KUNDENNUMMMER = "SELECT * FROM users WHERE kundennummer = ?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users;";
    private static final String DELETE_USERS = "DELETE FROM users WHERE kundennummer = ?;";
    private static final String SELECT_NOACCESS_USERS = "SELECT * FROM users WHERE accesslevel = 1;";

    @Override
    public boolean insertUser(User user) throws SQLException {

        boolean userInserted;

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS)) {
            preparedStatement.setString(1, user.getBenutzername());
            preparedStatement.setString(2, user.getPasswort());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getVorname());
            preparedStatement.setString(5, user.getNachname());
            preparedStatement.setString(6, user.getGeburtsdatum());
            preparedStatement.setString(7, user.getStrasse());
            preparedStatement.setInt(8, user.getHausnummer());
            preparedStatement.setInt(9, user.getPostleitzahl());
            preparedStatement.setString(10, user.getOrt());
            preparedStatement.setString(11, user.getBezahlmethode());
            preparedStatement.setInt(12, user.getAccessLevel());
            userInserted = preparedStatement.executeUpdate() > 0;
        }
        return userInserted;
    }

    @Override
    public boolean updateUser(User user) {
        boolean userUpdated;
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS);) {
            preparedStatement.setInt(1, user.getKundennummer());
            preparedStatement.setString(2, user.getBenutzername());
            preparedStatement.setString(3, user.getPasswort());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getVorname());
            preparedStatement.setString(6, user.getNachname());
            preparedStatement.setString(7, user.getGeburtsdatum());
            preparedStatement.setString(8, user.getStrasse());
            preparedStatement.setInt(9, user.getHausnummer());
            preparedStatement.setInt(10, user.getPostleitzahl());
            preparedStatement.setString(11, user.getOrt());
            preparedStatement.setString(12, user.getBezahlmethode());
            preparedStatement.setInt(13, user.getAccessLevel());
            preparedStatement.setInt(14, user.getKundennummer());

            userUpdated = preparedStatement.executeUpdate() > 0;
            return userUpdated;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User selectUser(int kundennummer) {
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_KUNDENNUMMMER);) {
            preparedStatement.setInt(1, kundennummer);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = new UserBuilder()
                        .withKundennummer(resultSet.getInt("kundennummer"))
                        .withBenutzername(resultSet.getString("benutzername"))
                        .withPasswort(resultSet.getString("passwort"))
                        .withEmail(resultSet.getString("email"))
                        .withVorname(resultSet.getString("vorname"))
                        .withNachname(resultSet.getString("nachname"))
                        .withGeburtsdatum(resultSet.getString("geburtsdatum"))
                        .withStrasse(resultSet.getString("straße"))
                        .withHausnummer(resultSet.getInt("hausnummer"))
                        .withPostleitzahl(resultSet.getInt("postleitzahl"))
                        .withOrt(resultSet.getString("ort"))
                        .withBezahlmethode(resultSet.getString("bezahlmethode"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> selectAllUsers() {
        List<User> userlist = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new UserBuilder()
                        .withKundennummer(resultSet.getInt("kundennummer"))
                        .withBenutzername(resultSet.getString("benutzername"))
                        .withPasswort(resultSet.getString("passwort"))
                        .withEmail(resultSet.getString("email"))
                        .withVorname(resultSet.getString("vorname"))
                        .withNachname(resultSet.getString("nachname"))
                        .withGeburtsdatum(resultSet.getString("geburtsdatum"))
                        .withStrasse(resultSet.getString("straße"))
                        .withHausnummer(resultSet.getInt("hausnummer"))
                        .withPostleitzahl(resultSet.getInt("postleitzahl"))
                        .withOrt(resultSet.getString("ort"))
                        .withBezahlmethode(resultSet.getString("bezahlmethode"))
                        .withAccessLevel(resultSet.getInt("accesslevel"))
                        .build();
                userlist.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userlist;
    }

    @Override
    public boolean deleteUser(int kundennummer) throws SQLException {
        boolean userDeleted;
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS);) {
            preparedStatement.setInt(1, kundennummer);

            userDeleted = preparedStatement.executeUpdate() > 0;

        }
        return userDeleted;
    }

    @Override
    public List<User> selectFreizugebendeUser() {
        List<User> freizugebendeuser = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NOACCESS_USERS);) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new UserBuilder()
                        .withKundennummer(resultSet.getInt("kundennummer"))
                        .withBenutzername(resultSet.getString("benutzername"))
                        .withPasswort(resultSet.getString("passwort"))
                        .withEmail(resultSet.getString("email"))
                        .withVorname(resultSet.getString("vorname"))
                        .withNachname(resultSet.getString("nachname"))
                        .withGeburtsdatum(resultSet.getString("geburtsdatum"))
                        .withStrasse(resultSet.getString("straße"))
                        .withHausnummer(resultSet.getInt("hausnummer"))
                        .withPostleitzahl(resultSet.getInt("postleitzahl"))
                        .withOrt(resultSet.getString("ort"))
                        .withBezahlmethode(resultSet.getString("bezahlmethode"))
                        .withAccessLevel(resultSet.getInt("accesslevel"))
                        .build();
                freizugebendeuser.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return freizugebendeuser;
    }
}