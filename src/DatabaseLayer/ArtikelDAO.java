package DatabaseLayer;

import java.sql.SQLException;
import java.util.List;

public interface ArtikelDAO {

    // Create or insert artikel
    boolean insertArtikel(Artikel artikel);

    // Update artikel
    boolean updateArtikel(Artikel artikel);

    // Select artikel by artikelnummer
    Artikel selectArtikel(int artikelnummer) throws SQLException;

    // Select artikel
    List<Artikel> selectAllArtikel();

    // delete artikel
    boolean deleteArtikel(int artikelnummer);

}
