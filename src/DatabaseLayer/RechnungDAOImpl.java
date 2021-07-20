package DatabaseLayer;

import ServiceLayer.WarenkorbService;

import java.sql.*;
import java.util.Random;

public class RechnungDAOImpl implements RechnungDAO {

    private final Connection connection = Database.getConnection();

    private static final String INSERT_RECHNUNG = "INSERT INTO rechnung (rechnungsnummer, kundennummer) VALUES (?, ?);";

    @Override
    public void createRechnung(Rechnung rechnung) {
        try {
            Random random = new Random();
            int rechnungsnummer = random.nextInt(999999);
            rechnung.setRechnungsnummer(rechnungsnummer);

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECHNUNG);
            preparedStatement.setInt(1, rechnungsnummer);
            preparedStatement.setInt(2, rechnung.getKundennummer());
            preparedStatement.executeUpdate();

            WarenkorbService.getW().generiereRechnung(rechnung);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
