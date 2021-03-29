package com.epam.task.web.project.mapper;

import com.epam.task.web.project.entity.Admin;
import com.epam.task.web.project.entity.Client;
import com.epam.task.web.project.entity.User;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User>{

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String CASH = "cash";
    public static final String MUSIC_AMOUNT = "music_amount";

    private static final String ADMIN = "admin";

    @Override
    public User map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        String name = resultSet.getString(NAME);
        String login = resultSet.getString(LOGIN);
        String password = resultSet.getString(PASSWORD);

        String role = (String) resultSet.getObject(ROLE);

        if (ADMIN.equals(role)) {

            return new Admin(id, name, login, password);
        } else {
            BigDecimal cash = resultSet.getBigDecimal(CASH);
            int musicAmount = resultSet.getInt(MUSIC_AMOUNT);

            return new Client(id, name, login, password, cash, musicAmount);
        }

    }

}
