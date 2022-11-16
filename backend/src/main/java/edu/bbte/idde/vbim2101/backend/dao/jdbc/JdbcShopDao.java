package edu.bbte.idde.vbim2101.backend.dao.jdbc;

import edu.bbte.idde.vbim2101.backend.dao.ShopsDao;
import edu.bbte.idde.vbim2101.backend.model.Shop;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Slf4j
public class JdbcShopDao implements ShopsDao {
    private final DataSource dataSource;

    public JdbcShopDao() {
        dataSource = DataSourceFactory.getDataSource();
    }

    @Override
    public Collection<Shop> findAll() {
        Collection<Shop> shops = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Shops");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Shop shop = new Shop(
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getInt("rating")
                );
                shop.setId(resultSet.getLong("id"));
                shops.add(shop);
            }
            log.info("[Shops - SQL] Find all successful");
        } catch (SQLException e) {
            log.error("[Shops - SQL] Find all failed... ", e);
        }
        return shops;
    }

    @Override
    public Shop findById(Long id) {
        //String query = "SELECT * FROM Book WHERE Id = ?";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Shops"
                    + "WHERE id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Shop shop = new Shop(
                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getInt("rating")
                );
                shop.setId(resultSet.getLong("id"));
                return shop;
            }
            log.info("[Shops - SQL] Find by id successful");
        } catch (SQLException e) {
            log.error("[Shops - SQL] Find by id failed... ", e);
        }
        return null;
    }

    @Override
    public void create(Shop entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Shops"
                    + "VALUES (default, ?, ?, ?)");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getAddress());
            preparedStatement.setInt(3, entity.getRating());
            preparedStatement.executeUpdate();
            log.info("[Shops - SQL] Create successful");
        } catch (SQLException e) {
            log.error("[Shops - SQL] Create failed... ", e);
        }
    }

    @Override
    public void update(Long id, Shop entity) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Shops"
                    + "SET name=?, address=?, rating=? WHERE id=?");
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getAddress());
            preparedStatement.setInt(3, entity.getRating());
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
            log.info("[Shops - SQL] Update successful");
        } catch (SQLException e) {
            log.error("[Shops - SQL] Create failed... ", e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Shops WHERE id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            log.info("[Shops - SQL] Delete successful");
        } catch (SQLException e) {
            log.error("[Shops - SQL] Delete failed... ", e);
        }
    }
}
