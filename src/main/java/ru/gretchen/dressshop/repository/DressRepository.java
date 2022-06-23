package ru.gretchen.dressshop.repository;

import ru.gretchen.dressshop.model.DressEntity;
import ru.gretchen.dressshop.model.Enumeration.Colour;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DressRepository extends BaseRepository {

    private final static String GET_BY_ID_SQL = "SELECT * FROM dress WHERE id = ?;";
    private final static String SAVE_SQL = "INSERT INTO dress (colour, price, in_stock) VALUES (?, ?, ?);";
    private final static String GET_ALL_SQL = "SELECT * FROM dress;";
    private final static String UPDATE_BY_ID_SQL = "UPDATE dress SET colour = ?, price = ?, in_stock = ? WHERE id = ?;";
    private final static String DELETE_BY_ID_SQL = "DELETE FROM dress WHERE id = ?;";

    public Optional<DressEntity> getById(Long id) throws SQLException {
        Colour colour = null;
        Long price = null;
        Long inStock = null;
        PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID_SQL);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            colour = (Colour) resultSet.getObject("colour");
            price = resultSet.getLong("price");
            inStock = resultSet.getLong("in_stock");
        } return Optional.of(new DressEntity(id, colour, price, inStock));

    }

    public DressEntity save(DressEntity dress) throws SQLException {
        PreparedStatement preparedStatement = getConnection().prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setObject(1, dress.getColour());
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
        PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_BY_ID_SQL);
        preparedStatement.setObject(1, dress.getColour());
        preparedStatement.setLong(2, dress.getPrice());
        preparedStatement.setLong(3, dress.getInStock());
        preparedStatement.setLong(4, id);
        preparedStatement.executeUpdate();
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
            Colour colour = (Colour) resultSet.getObject("colour");
            Long price = resultSet.getLong("price");
            Long inStock = resultSet.getLong("in_stock");
            dresses.add(new DressEntity(id, colour, price, inStock));
        }
        return dresses;
    }
}
