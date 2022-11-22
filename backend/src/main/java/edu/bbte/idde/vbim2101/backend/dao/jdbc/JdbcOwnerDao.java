package edu.bbte.idde.vbim2101.backend.dao.jdbc;

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

    @Override
    public Collection<Owner> findAll() {
        Collection<Owner> owners = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Owners");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Owner owner = new Owner(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("age")
                );
                owner.setId(resultSet.getLong("id"));
                owners.add(owner);
            }
            log.info("[Owners - SQL] Find all successful");
        } catch (SQLException e) {
            log.error("[Owners - SQL] Find all failed... ", e);
        }
        return owners;
    }

    @Override
    public Owner findById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Owners "
                    + "WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Owner owner = new Owner(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("age")
                );
                owner.setId(resultSet.getLong("id"));
                return owner;
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
                    + "VALUES (default, ?, ?, ?)");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setInt(3, entity.getAge());
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
                    + "SET name=?, address=?, rating=? WHERE id=?");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setInt(3, entity.getAge());
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
            log.info("[Owners - SQL] Update successful");
        } catch (SQLException e) {
            log.error("[Owners - SQL] Create failed... ", e);
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
                Owner owner = new Owner(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getInt("age")
                );
                owner.setId(resultSet.getLong("id"));
                owners.add(owner);
            }
            log.info("[Owners - SQL] Find by age successful");
        } catch (SQLException e) {
            log.error("[Owners - SQL] Find by age failed... ", e);
        }
        return owners;
    }
}
