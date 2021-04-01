package com.epam.task.web.project.mapper;

import com.epam.task.web.project.entity.Admin;
import com.epam.task.web.project.entity.Client;
import com.epam.task.web.project.entity.Role;
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
        String tempRole = (String) resultSet.getString(ROLE);
        Role role = null;

        if (ADMIN.equals(tempRole)) {
            role = Role.ADMIN;
            return new Admin(id, name, login, password, role);

        } else {
            role = Role.CLIENT;
            BigDecimal cash = resultSet.getBigDecimal(CASH);
            int musicAmount = resultSet.getInt(MUSIC_AMOUNT);

            return new Client(id, name, login, password, role, cash, musicAmount);
        }

    }

}
