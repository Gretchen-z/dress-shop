package ru.gretchen.dressshop.repository;

import ru.gretchen.dressshop.model.DressEntity;
import ru.gretchen.dressshop.model.Enumeration.Color;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DressRepository extends BaseRepository {

    private final static String GET_BY_ID_SQL = "SELECT * FROM dress WHERE id = ?;";
    private final static String SAVE_SQL = "INSERT INTO dress (colour, price, in_stock) VALUES (?, ?, ?);";
    private final static String GET_ALL_SQL = "SELECT * FROM dress;";
    private final static String UPDATE_COLOR_STOCK = "UPDATE dress SET colour = ?, in_stock = ? WHERE id = ?;";
    private final static String UPDATE_PRICE = "UPDATE dress SET price = ? WHERE id = ?;";
    private final static String DELETE_BY_ID_SQL = "DELETE FROM dress WHERE id = ?;";

    public Optional<DressEntity> getById(Long id) throws SQLException {
        Color color = null;
        Long price = null;
        Long inStock = null;
        PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID_SQL);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            color = Color.valueOf(resultSet.getString("colour"));
            price = resultSet.getLong("price");
            inStock = resultSet.getLong("in_stock");
        } return Optional.of(new DressEntity(id, color, price, inStock));
    }

    public DressEntity save(DressEntity dress) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, dress.getColor().toString());
        preparedStatement.setLong(2, dress.getPrice());
        preparedStatement.setLong(3, dress.getInStock());
        preparedStatement.execute();
        ResultSet resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
        Long id = resultSet.getLong(1);
        dress.setId(id);
        return dress;
    }

    public DressEntity update(Long id, DressEntity dress) throws SQLException {
        Connection connection = getConnection();
        connection.setAutoCommit(false);

        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COLOR_STOCK);
        preparedStatement.setString(1, dress.getColor().toString());
        preparedStatement.setLong(2, dress.getInStock());
        preparedStatement.setLong(3, id);
        preparedStatement.executeUpdate();

        PreparedStatement preparedStatement2 = connection.prepareStatement(UPDATE_PRICE);
        preparedStatement2.setLong(1, dress.getPrice());
        preparedStatement2.setLong(2, id);
        preparedStatement2.executeUpdate();
        connection.commit();
        connection.setAutoCommit(true);
        return dress;
    }

    public void delete(Long id) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_BY_ID_SQL);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }

    public List<DressEntity> getAll() throws SQLException {
        Statement statement = getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_SQL);
        List<DressEntity> dresses = new ArrayList<>();
        while (resultSet.next()) {
            Long id = resultSet.getLong("id");
            Color color = Color.valueOf(resultSet.getString("colour"));
            Long price = resultSet.getLong("price");
            Long inStock = resultSet.getLong("in_stock");
            dresses.add(new DressEntity(id, color, price, inStock));
        }
        return dresses;
    }
}
