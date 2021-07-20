package DatabaseLayer;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    // Create or insert user
    boolean insertUser(User user) throws SQLException;

    // Update user
    boolean updateUser(User user);

    // Select user by kundennummer
    User selectUser(int kundennummer);

    // Select users
    List<User> selectAllUsers();

    // delete user
    boolean deleteUser(int kundennummer) throws SQLException;

    // Select freizugebende User
    List<User> selectFreizugebendeUser();
}
