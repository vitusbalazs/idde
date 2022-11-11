package edu.bbte.idde.vbim2101.backend.dao.jdbc;

import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.model.Advertisement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class JdbcAdvertisementDao implements AdvertisementsDao {
    private Connection connection;
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcAdvertisementDao.class);

    public JdbcAdvertisementDao() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost/idde_vbim2101",
                    "root",
                    "root"
            );
            LOGGER.info("[SQL] Connection initialized");
        } catch (ClassNotFoundException e) {
            LOGGER.error("[SQL] No driver");
        } catch (SQLException e) {
            LOGGER.error("[SQL] SQL exception: ", e);
        }

    }

    @Override
    public Collection<Advertisement> findAllAdvertisements() {
        Collection<Advertisement> advertisements = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Advertisements");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Advertisement advertisement = new Advertisement(
                        resultSet.getString("title"),
                        resultSet.getString("address"),
                        resultSet.getInt("price"),
                        resultSet.getInt("surfaceArea"),
                        resultSet.getInt("rooms")
                );
                advertisement.setId(resultSet.getLong("id"));
                advertisements.add(advertisement);
            }
            LOGGER.info("[SQL] Find all successful");
        } catch (SQLException e) {
            LOGGER.error("[SQL] Find all failed... ", e);
        }
        return advertisements;
    }

    @Override
    public Advertisement findById(Long id) {
        //String query = "SELECT * FROM Book WHERE Id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Advertisements"
                    + "WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Advertisement advertisement = new Advertisement(
                        resultSet.getString("title"),
                        resultSet.getString("address"),
                        resultSet.getInt("price"),
                        resultSet.getInt("surfaceArea"),
                        resultSet.getInt("rooms")
                );
                advertisement.setId(resultSet.getLong("id"));
                return advertisement;
            }
            LOGGER.info("[SQL] Find by id successful");
        } catch (SQLException e) {
            LOGGER.error("[SQL] Find by id failed... ", e);
        }
        return null;
    }

    @Override
    public void createAdvertisement(Advertisement entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Advertisements"
                    + "VALUES (default, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getAddress());
            preparedStatement.setInt(3, entity.getPrice());
            preparedStatement.setInt(4, entity.getSurfaceArea());
            preparedStatement.setInt(5, entity.getRooms());
            preparedStatement.executeUpdate();
            LOGGER.info("[SQL] Create successful");
        } catch (SQLException e) {
            LOGGER.error("[SQL] Create failed... ", e);
        }
    }

    @Override
    public void updateAdvertisement(Long id, Advertisement entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Advertisements"
                    + "SET title=?, address=?, price=?, surfaceArea=?, rooms=? WHERE id=?");
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getAddress());
            preparedStatement.setInt(3, entity.getPrice());
            preparedStatement.setInt(4, entity.getSurfaceArea());
            preparedStatement.setInt(5, entity.getRooms());
            preparedStatement.setLong(6, id);
            preparedStatement.executeUpdate();
            LOGGER.info("[SQL] Update successful");
        } catch (SQLException e) {
            LOGGER.error("[SQL] Create failed... ", e);
        }
    }

    @Override
    public void deleteAdvertisement(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Advertisements WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            LOGGER.info("[SQL] Delete successful");
        } catch (SQLException e) {
            LOGGER.error("[SQL] Delete failed... ", e);
        }
    }
}
