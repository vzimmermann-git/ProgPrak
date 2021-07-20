package DatabaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtikelDAOImpl implements ArtikelDAO {

    private final Connection connection = Database.getConnection();

    private static final String INSERT_ARTIKEL = "INSERT INTO artikelliste (artikelbezeichnung, artikelkategorie, artikelaltersfreigabe, artikelbestand, artikelnettopreis, artikelmehrwertsteuer) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_ARTIKEL = "UPDATE artikelliste SET artikelnummer = ?, artikelbezeichnung = ?, artikelkategorie = ?, artikelaltersfreigabe = ?, artikelbestand = ?, artikelnettopreis = ?, artikelmehrwertsteuer = ? WHERE artikelnummer = ?;";
    private static final String SELECT_ARTIKEL = "SELECT * FROM artikelliste WHERE artikelnummer = ?;";
    private static final String SELECT_ALL_ARTIKEL = "SELECT * FROM artikelliste;";
    private static final String DELETE_ARTIKEL = "DELETE FROM artikelliste WHERE artikelnummer = ?;";

    @Override
    public boolean insertArtikel(Artikel artikel) {
        boolean artikelInserted;

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ARTIKEL);) {
            preparedStatement.setString(1, artikel.getArtikelBezeichnung());
            preparedStatement.setString(2, artikel.getArtikelKategorie());
            preparedStatement.setString(3, artikel.getArtikelAltersfreigabe());
            preparedStatement.setInt(4, artikel.getArtikelBestand());
            preparedStatement.setDouble(5, artikel.getArtikelNettoPreis());
            preparedStatement.setDouble(6, artikel.getArtikelMehrwertsteuer());

            artikelInserted = preparedStatement.executeUpdate() > 0;
            return artikelInserted;
        } catch (SQLException e) {
            e.printStackTrace();
        } return false;
    }

    @Override
    public boolean updateArtikel(Artikel artikel) {
        boolean artikelUpdated;

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ARTIKEL);) {
            preparedStatement.setInt(1, artikel.getArtikelnummer());
            preparedStatement.setString(2, artikel.getArtikelBezeichnung());
            preparedStatement.setString(3, artikel.getArtikelKategorie());
            preparedStatement.setString(4, artikel.getArtikelAltersfreigabe());
            preparedStatement.setInt(5, artikel.getArtikelBestand());
            preparedStatement.setDouble(6, artikel.getArtikelNettoPreis());
            preparedStatement.setDouble(7, artikel.getArtikelMehrwertsteuer());
            preparedStatement.setInt(8, artikel.getArtikelnummer());

            artikelUpdated = preparedStatement.executeUpdate() > 0;
            return artikelUpdated;
        } catch (SQLException e) {
            e.printStackTrace();
        } return false;
    }

    @Override
    public Artikel selectArtikel(int artikelnummer) {
        Artikel artikel = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ARTIKEL);) {
            preparedStatement.setInt(1, artikelnummer);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                artikel = new ArtikelBuilder()
                        .withArtikelnummer(resultSet.getInt("artikelnummer"))
                        .withArtikelBezeichnung(resultSet.getString("artikelbezeichnung"))
                        .withArtikelKategorie(resultSet.getString("artikelkategorie"))
                        .withArtikelAltersfreigabe(resultSet.getString("artikelaltersfreigabe"))
                        .withArtikelBestand(resultSet.getInt("artikelbestand"))
                        .withArtikelNettoPreis(resultSet.getDouble("artikelnettopreis"))
                        .withArtikelMehrwertsteuer(resultSet.getDouble("artikelmehrwertsteuer"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return artikel;
    }

    @Override
    public List<Artikel> selectAllArtikel() {
        List<Artikel> artikelliste = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ARTIKEL);) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Artikel artikel = new ArtikelBuilder()
                        .withArtikelnummer(resultSet.getInt("artikelnummer"))
                        .withArtikelBezeichnung(resultSet.getString("artikelbezeichnung"))
                        .withArtikelKategorie(resultSet.getString("artikelkategorie"))
                        .withArtikelAltersfreigabe(resultSet.getString("artikelaltersfreigabe"))
                        .withArtikelBestand(resultSet.getInt("artikelbestand"))
                        .withArtikelNettoPreis(resultSet.getDouble("artikelnettopreis"))
                        .withArtikelMehrwertsteuer(resultSet.getDouble("artikelmehrwertsteuer"))
                        .build();
                artikelliste.add(artikel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } return artikelliste;
    }

    @Override
    public boolean deleteArtikel(int artikelnummer) {
        boolean artikelDeleted;
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ARTIKEL);) {
            preparedStatement.setInt(1, artikelnummer);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
