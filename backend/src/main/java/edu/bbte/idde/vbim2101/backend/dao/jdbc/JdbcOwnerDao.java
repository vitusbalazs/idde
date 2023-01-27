package edu.bbte.idde.vbim2101.backend.dao.jdbc;

import edu.bbte.idde.vbim2101.backend.config.Config;
import edu.bbte.idde.vbim2101.backend.config.ConfigFactory;
import edu.bbte.idde.vbim2101.backend.dao.OwnersDao;
import edu.bbte.idde.vbim2101.backend.model.Owner;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class JdbcOwnerDao implements OwnersDao {
    private final DataSource dataSource;

    public JdbcOwnerDao() {
        dataSource = DataSourceFactory.getDataSource();
    }

    // Creates Advertisement object from resultSet
    private Owner createOwnerFromResultSet(ResultSet resultSet) throws SQLException {
        Owner owner = new Owner(
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getInt("age"),
                resultSet.getInt("version")
        );
        owner.setId(resultSet.getLong("id"));
        return owner;
    }

    // Sets the parameters of a preparedStatement from an Advertisement object
    // It doesn't set the id
    private void setParameters(Owner owner, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, owner.getName());
        preparedStatement.setString(2, owner.getEmail());
        preparedStatement.setInt(3, owner.getAge());
        preparedStatement.setInt(4, owner.getVersion());
    }

    @Override
    public Collection<Owner> findAll() {
        Config config = ConfigFactory.getConfig();
        Boolean showVersion = config.getShowVersion();
        Collection<Owner> owners = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Owners");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Owner owner = createOwnerFromResultSet(resultSet);
                if (showVersion) {
                    owners.add(owner);
                } else {
                    owner.setVersion(null);
                    owners.add(owner);
                }
            }
            log.info("[Owners - SQL] Find all successful");
        } catch (SQLException e) {
            log.error("[Owners - SQL] Find all failed... ", e);
        }
        return owners;
    }

    @Override
    public Owner findById(Long id) {
        Config config = ConfigFactory.getConfig();
        Boolean showVersion = config.getShowVersion();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Owners "
                    + "WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (showVersion) {
                    return createOwnerFromResultSet(resultSet);
                } else {
                    Owner owner = createOwnerFromResultSet(resultSet);
                    owner.setVersion(null);
                    return owner;
                }

            }
            log.info("[Owners - SQL] Find by id successful");
        } catch (SQLException e) {
            log.error("[Owners - SQL] Find by id failed... ", e);
        }
        return null;
    }

    @Override
    public void create(Owner entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Owners "
                    + "VALUES (default, ?, ?, ?, ?)");
            setParameters(entity, preparedStatement);
            preparedStatement.executeUpdate();
            log.info("[Owners - SQL] Create successful");
        } catch (SQLException e) {
            log.error("[Owners - SQL] Create failed... ", e);
        }
    }

    @Override
    public void update(Long id, Owner entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Owners "
                    + "SET name=?, email=?, age=?, version=? WHERE id=?");
            setParameters(entity, preparedStatement);
            preparedStatement.setInt(4, entity.getVersion() + 1);
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();
            log.info("[Owners - SQL] Update successful");
        } catch (SQLException e) {
            log.error("[Owners - SQL] Update failed... ", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Owners WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            log.info("[Owners - SQL] Delete successful");
        } catch (SQLException e) {
            log.error("[Owners - SQL] Delete failed... ", e);
        }
    }

    @Override
    public Collection<Owner> findByAge(Integer age) {
        Collection<Owner> owners = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Owners WHERE age=?");
            preparedStatement.setInt(1, age);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Owner owner = createOwnerFromResultSet(resultSet);
                owners.add(owner);
            }
            log.info("[Owners - SQL] Find by age successful");
        } catch (SQLException e) {
            log.error("[Owners - SQL] Find by age failed... ", e);
        }
        return owners;
    }
}
