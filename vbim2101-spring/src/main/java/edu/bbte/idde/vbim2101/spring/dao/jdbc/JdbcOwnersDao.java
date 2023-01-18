package edu.bbte.idde.vbim2101.spring.dao.jdbc;

import edu.bbte.idde.vbim2101.spring.dao.OwnersDao;
import edu.bbte.idde.vbim2101.spring.model.Owner;
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
public class JdbcOwnersDao implements OwnersDao {
    @Autowired
    private DataSource dataSource;

    private Owner createOwnerFromResultSet(ResultSet resultSet) throws SQLException {
        Owner owner = new Owner(
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getInt("age")
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
    }

    @Override
    public Collection<Owner> findAll() {
        Collection<Owner> owners = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Owners");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Owner owner = createOwnerFromResultSet(resultSet);
                owners.add(owner);
            }
            log.info("[Owners - SQL] Find all successful");
        } catch (SQLException e) {
            log.error("[Owners - SQL] Find all failed... ", e);
        }
        return owners;
    }

    @Override
    public Owner getById(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Owners "
                    + "WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return createOwnerFromResultSet(resultSet);
            }
            log.info("[Owners - SQL] Find by id successful");
        } catch (SQLException e) {
            log.error("[Owners - SQL] Find by id failed... ", e);
        }
        return null;
    }

    @Override
    public Owner saveAndFlush(Owner entity) {
        Long id = entity.getId();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Owners "
                    + "WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                update(entity.getId(), entity);
            } else {
                PreparedStatement preparedStatement2 = connection.prepareStatement("INSERT INTO Owners "
                        + "VALUES (default, ?, ?, ?)");
                setParameters(entity, preparedStatement2);
                preparedStatement2.executeUpdate();
                preparedStatement2 = connection.prepareStatement("SELECT LAST_INSERT_ID()");
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                if (resultSet2.next()) {
                    id = resultSet2.getLong(1);
                }
                log.info("[Owners - SQL] Create successful");
            }

        } catch (SQLException e) {
            log.error("[Owners - SQL] Create failed... ", e);
        }
        return getById(id);
    }

    public void update(Long id, Owner entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Owners "
                    + "SET name=?, address=?, rating=? WHERE id=?");
            setParameters(entity, preparedStatement);
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
            Integer rowsAffected = preparedStatement.getUpdateCount();
            if (rowsAffected == 1) {
                log.info("[Owners - SQL] Update successful");
            }
            log.error("[Owners - SQL] Update unsuccessful");
        } catch (SQLException e) {
            log.error("[Owners - SQL] Update failed... ", e);
        }
    }

    @Override
    public void delete(Owner owner) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Owners WHERE id=?");
            preparedStatement.setLong(1, owner.getId());
            preparedStatement.executeUpdate();
            Integer rowCount = 0;
            preparedStatement = connection.prepareStatement("SELECT ROW_COUNT()");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }
            if (rowCount == 1) {
                log.info("[Owners - SQL] Delete successful");
            }
            log.error("[Owners - SQL] Delete unsuccessful");
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
