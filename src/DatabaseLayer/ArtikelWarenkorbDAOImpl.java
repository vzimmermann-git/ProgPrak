package DatabaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtikelWarenkorbDAOImpl implements ArtikelWarenkorbDAO {

    private final Connection connection = Database.getConnection();

    @Override
    public void clearWarenkorb() {
        String sql = "DELETE FROM warenkorb";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addToWarenkorb(ArtikelWarenkorb artikelWarenkorb) {
        String sql = "INSERT INTO warenkorb (artikelnummer, artikelbezeichnung, artikelmenge, artikelpreis) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, artikelWarenkorb.getArtikelNummer());
            preparedStatement.setString(2, artikelWarenkorb.getArtikelBezeichnung());
            preparedStatement.setInt(3, artikelWarenkorb.getArtikelMenge());
            preparedStatement.setDouble(4, artikelWarenkorb.getArtikelPreis());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ArtikelWarenkorb> readWarenkorb() {
        String sql = "SELECT * FROM warenkorb";
        List<ArtikelWarenkorb> warenkorb = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int artikelnummer = resultSet.getInt("artikelnummer");
                String artikelbezeichnung = resultSet.getString("artikelbezeichnung");
                int artikelmenge = resultSet.getInt("artikelmenge");
                double artikelpreis = resultSet.getDouble("artikelpreis");
                ArtikelWarenkorb artikelWarenkorb = new ArtikelWarenkorb(artikelnummer, artikelbezeichnung, artikelmenge, artikelpreis);
                warenkorb.add(artikelWarenkorb);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warenkorb;
    }


}
