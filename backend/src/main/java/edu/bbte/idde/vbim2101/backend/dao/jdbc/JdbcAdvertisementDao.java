package edu.bbte.idde.vbim2101.backend.dao.jdbc;

import edu.bbte.idde.vbim2101.backend.config.Config;
import edu.bbte.idde.vbim2101.backend.config.ConfigFactory;
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

    // Creates Advertisement object from resultSet
    private Advertisement createAdvertisementFromResultSet(ResultSet resultSet) throws SQLException {
        Advertisement advertisement;
        advertisement = new Advertisement(
                resultSet.getString("title"),
                resultSet.getString("address"),
                resultSet.getInt("price"),
                resultSet.getInt("surfaceArea"),
                resultSet.getInt("rooms"),
                resultSet.getLong("owner"),
                resultSet.getInt("version")
        );
        advertisement.setId(resultSet.getLong("id"));
        return advertisement;
    }

    // Sets the parameters of a preparedStatement from an Advertisement object
    // It doesn't set the id
    private void setParameters(Advertisement advertisement, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, advertisement.getTitle());
        preparedStatement.setString(2, advertisement.getAddress());
        preparedStatement.setInt(3, advertisement.getPrice());
        preparedStatement.setInt(4, advertisement.getSurfaceArea());
        preparedStatement.setInt(5, advertisement.getRooms());
        preparedStatement.setLong(6, advertisement.getOwner());
    }

    @Override
    public Collection<Advertisement> findAll() {
        Config config = ConfigFactory.getConfig();
        Boolean showVersion = config.getShowVersion();
        Collection<Advertisement> advertisements = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Advertisements");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Advertisement advertisement = createAdvertisementFromResultSet(resultSet);
                if (showVersion) {
                    advertisements.add(advertisement);
                } else {
                    advertisement.setVersion(null);
                    advertisements.add(advertisement);
                }

            }
            log.info("[Advertisements - SQL] Find all successful");
        } catch (SQLException e) {
            log.error("[Advertisements - SQL] Find all failed... ", e);
        }
        return advertisements;
    }

    @Override
    public Advertisement findById(Long id) {
        Config config = ConfigFactory.getConfig();
        Boolean showVersion = config.getShowVersion();
        //String query = "SELECT * FROM Book WHERE Id = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Advertisements "
                    + "WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (showVersion) {
                    return createAdvertisementFromResultSet(resultSet);
                } else {
                    Advertisement advertisement = createAdvertisementFromResultSet(resultSet);
                    advertisement.setVersion(null);
                    return advertisement;
                }

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
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Advertisements "
                    + "VALUES (default, ?, ?, ?, ?, ?, ?, ?)");
            setParameters(entity, preparedStatement);
            preparedStatement.setInt(7, entity.getVersion());
            preparedStatement.executeUpdate();
            log.info("[Advertisements - SQL] Create successful");
        } catch (SQLException e) {
            log.error("[Advertisements - SQL] Create failed... ", e);
        }
    }

    @Override
    public void update(Long id, Advertisement entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Advertisements "
                    + "SET title=?, address=?, price=?, surfaceArea=?, rooms=?, owner=?, version=? WHERE id=?");
            setParameters(entity, preparedStatement);
            Integer newVersion = entity.getVersion() + 1;
            preparedStatement.setInt(7, newVersion);
            preparedStatement.setLong(8, id);
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
    public Collection<Advertisement> findByRooms(Integer rooms) {
        Collection<Advertisement> advertisements = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Advertisements"
                    + " WHERE rooms = ?");
            preparedStatement.setInt(1, rooms);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Advertisement advertisement = createAdvertisementFromResultSet(resultSet);
                advertisements.add(advertisement);
            }
            log.info("[Advertisements - SQL] Find by rooms successful");
        } catch (SQLException e) {
            log.error("[Advertisements - SQL] Find by rooms failed... ", e);
        }
        return advertisements;
    }
}
