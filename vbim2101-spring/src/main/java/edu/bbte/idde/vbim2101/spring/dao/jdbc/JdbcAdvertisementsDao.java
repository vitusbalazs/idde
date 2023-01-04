package edu.bbte.idde.vbim2101.spring.dao.jdbc;

import edu.bbte.idde.vbim2101.spring.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.spring.model.Advertisement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Repository
@Slf4j
@Profile("jdbc")
public class JdbcAdvertisementsDao implements AdvertisementsDao {
    @Autowired
    private DataSource dataSource;

    private Advertisement createAdvertisementFromResultSet(ResultSet resultSet) throws SQLException {
        Advertisement advertisement;
        advertisement = new Advertisement(
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
        Collection<Advertisement> advertisements = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Advertisements");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Advertisement advertisement = createAdvertisementFromResultSet(resultSet);
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Advertisements "
                    + "WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createAdvertisementFromResultSet(resultSet);
            }
            log.info("[Advertisements - SQL] Find by id successful");
        } catch (SQLException e) {
            log.error("[Advertisements - SQL] Find by id failed... ", e);
        }
        return null;
    }

    @Override
    public Long create(Advertisement entity) {
        Long id = null;
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Advertisements "
                    + "VALUES (default, ?, ?, ?, ?, ?, ?)");
            setParameters(entity, preparedStatement);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
            log.info("[Advertisements - SQL] Create successful");
        } catch (SQLException e) {
            log.error("[Advertisements - SQL] Create failed... ", e);
        }
        return id;
    }

    @Override
    public Boolean update(Long id, Advertisement entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Advertisements "
                    + "SET title=?, address=?, price=?, surfaceArea=?, rooms=?, owner=? WHERE id=?");
            setParameters(entity, preparedStatement);
            preparedStatement.setLong(7, id);
            preparedStatement.executeUpdate();
            Integer rowsAffected = preparedStatement.getUpdateCount();
            if (rowsAffected == 1) {
                log.info("[Advertisements - SQL] Update successful");
                return true;
            }
            log.error("[Advertisements - SQL] Update unsuccessful");
        } catch (SQLException e) {
            log.error("[Advertisements - SQL] Update failed... ", e);
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Advertisements WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            Integer rowCount = 0;
            preparedStatement = connection.prepareStatement("SELECT ROW_COUNT()");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }
            if (rowCount == 1) {
                log.info("[Advertisements - SQL] Delete successful");
                return true;
            }
            log.error("[Advertisements - SQL] Delete unsuccessful");
        } catch (SQLException e) {
            log.error("[Advertisements - SQL] Delete failed... ", e);
        }
        return false;
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