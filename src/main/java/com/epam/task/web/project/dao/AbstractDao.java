package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;
import com.epam.task.web.project.entity.Entity;
import com.epam.task.web.project.mapper.Mapper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao <T extends Entity> implements Dao<T>{

    private final ProxyConnection proxyConnection;
    private final Mapper<T> mapper;
    private final String tableName;

    protected static final String SELECT_ALL = "SELECT * FROM ";
    protected static final String WHERE_ID = " WHERE id = ";

    public AbstractDao(ProxyConnection proxyConnection, Mapper<T> mapper, String tableName) {
        this.proxyConnection = proxyConnection;
        this.mapper = mapper;
        this.tableName = tableName;
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = proxyConnection.prepareStatement(query);

        for (int i = 1; i <= params.length; i++){
            statement.setObject(i, params[i-1]);
        }

        return statement;
    }

    protected List<T> executeQuery(String query, Object... params) throws DaoException{
        try(PreparedStatement statement = createStatement(query, params);
            ResultSet resultSet = statement.executeQuery()) {

            List<T> entities = new ArrayList<>();
            while (resultSet.next()){
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }

            return entities;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    protected boolean existExecuteQuery(String query, Object... params) throws DaoException{
        try(PreparedStatement statement = createStatement(query, params);
            ResultSet resultSet = statement.executeQuery()) {

            int result = 0;
            while (resultSet.next()){
               result = resultSet.getInt(1);
            }

            return result == 1;

        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    protected void executeUpdate(String query, Object... params) throws DaoException{
        try(PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    protected Optional<T> executeForSingleResult(String query, Object... params) throws DaoException{
        List<T> items = executeQuery(query, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));

        } else if (items.size() > 1) {
            throw new DaoException("More than one record found!");

        } else {
            return Optional.empty();
        }

    }

    @Override
    public Optional<T> getById(Long id) throws DaoException {
        return executeForSingleResult(SELECT_ALL + tableName + WHERE_ID + id);
    }

    @Override
    public List<T> getAll() throws DaoException {
        return executeQuery(SELECT_ALL + tableName);
    }

    @Override
    public void save(T item) throws DaoException {
    }

    @Override
    public void removeById(Long id) throws DaoException {

    }

}
