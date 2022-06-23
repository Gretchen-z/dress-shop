package ru.gretchen.dressshop.repository;

import lombok.Getter;
import ru.gretchen.dressshop.exception.RepositoryInitializeException;
import ru.gretchen.dressshop.sql.JDBCUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@Getter
public abstract class BaseRepository {

    private final Connection connection;
    private final JDBCUtil jdbcUtil;

    public BaseRepository() throws RepositoryInitializeException {
        jdbcUtil = new JDBCUtil();
        try {
            this.connection = jdbcUtil.getConnection();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            throw new RepositoryInitializeException(e.getMessage());
        }
    }
}
