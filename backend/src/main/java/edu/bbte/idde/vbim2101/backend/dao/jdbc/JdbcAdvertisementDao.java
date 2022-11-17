package edu.bbte.idde.vbim2101.backend.dao.jdbc;

import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.model.Advertisement;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class JdbcAdvertisementDao implements AdvertisementsDao {
    private final DataSource dataSource;

    public JdbcAdvertisementDao() {
        dataSource = DataSourceFactory.getDataSource();
    }

    @Override
    public Collection<Advertisement> findAll() {
        Collection<Advertisement> advertisements = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Advertisements");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Advertisement advertisement = new Advertisement(
                        resultSet.getString("title"),
                        resultSet.getString("address"),
                        resultSet.getInt("price"),
                        resultSet.getInt("surfaceArea"),
                        resultSet.getInt("rooms"),
                        resultSet.getLong("owner")
                );
                advertisement.setId(resultSet.getLong("id"));
                advertisements.add(advertisement);
            }
            log.info("[Advertisements - SQL] Find all successful");
        } catch (SQLException e) {
            log.error("[Advertisements - SQL] Find all failed... ", e);
        }
        return advertisements;
    }

    @Override
    public Advertisement findById(Long id) {
        //String query = "SELECT * FROM Book WHERE Id = ?";
        try (Connection connection = dataSource.getConnection()) {
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
                        resultSet.getInt("rooms"),
                        resultSet.getLong("owner")
                );
                advertisement.setId(resultSet.getLong("id"));
                return advertisement;
            }
            log.info("[Advertisements - SQL] Find by id successful");
        } catch (SQLException e) {
            log.error("[Advertisements - SQL] Find by id failed... ", e);
        }
        return null;
    }

    @Override
    public void create(Advertisement entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Advertisements"
                    + "VALUES (default, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getAddress());
            preparedStatement.setInt(3, entity.getPrice());
            preparedStatement.setInt(4, entity.getSurfaceArea());
            preparedStatement.setInt(5, entity.getRooms());
            preparedStatement.executeUpdate();
            log.info("[Advertisements - SQL] Create successful");
        } catch (SQLException e) {
            log.error("[Advertisements - SQL] Create failed... ", e);
        }
    }

    @Override
    public void update(Long id, Advertisement entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Advertisements"
                    + "SET title=?, address=?, price=?, surfaceArea=?, rooms=? WHERE id=?");
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getAddress());
            preparedStatement.setInt(3, entity.getPrice());
            preparedStatement.setInt(4, entity.getSurfaceArea());
            preparedStatement.setInt(5, entity.getRooms());
            preparedStatement.setLong(6, id);
            preparedStatement.executeUpdate();
            log.info("[Advertisements - SQL] Update successful");
        } catch (SQLException e) {
            log.error("[Advertisements - SQL] Create failed... ", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Advertisements WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            log.info("[Advertisements - SQL] Delete successful");
        } catch (SQLException e) {
            log.error("[Advertisements - SQL] Delete failed... ", e);
        }
    }

    @Override
    public Collection<Advertisement> findByAge(Integer age) {
        Collection<Advertisement> advertisements = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Advertisements"
                    + " JOIN Owners ON Owners.id = Advertisements.owner"
                    + " WHERE Owners.age = ?");
            preparedStatement.setInt(1, age);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Advertisement advertisement = new Advertisement(
                        resultSet.getString("title"),
                        resultSet.getString("address"),
                        resultSet.getInt("price"),
                        resultSet.getInt("surfaceArea"),
                        resultSet.getInt("rooms"),
                        resultSet.getLong("owner")
                );
                advertisement.setId(resultSet.getLong("id"));
                advertisements.add(advertisement);
            }
            log.info("[Advertisements - SQL] Find by age successful");
        } catch (SQLException e) {
            log.error("[Advertisements - SQL] Find by age failed... ", e);
        }
        return advertisements;
    }
}
