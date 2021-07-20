package DatabaseLayer;

import java.sql.SQLException;
import java.util.List;

public interface ArtikelWarenkorbDAO {

    void clearWarenkorb();

    void addToWarenkorb(ArtikelWarenkorb artikelWarenkorb);

    List<ArtikelWarenkorb> readWarenkorb();

}
