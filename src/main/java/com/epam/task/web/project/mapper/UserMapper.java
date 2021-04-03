package com.epam.task.web.project.mapper;

import com.epam.task.web.project.entity.Role;
import com.epam.task.web.project.entity.User;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements Mapper<User> {

    private static final String ADMIN = "admin";

    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String ROLE = "role";
    public static final String CASH = "cash";
    public static final String MUSIC_AMOUNT = "music_amount";

    @Override
    public User map(ResultSet resultSet) throws SQLException {

        Long id = resultSet.getLong(ID);
        String username = resultSet.getString(USERNAME);
        String password = resultSet.getString(PASSWORD);
        String role = resultSet.getString(ROLE);

        if (ADMIN.equals(role)) {
            return User.getAdmin(id, username, password);

        } else {
            BigDecimal cash = resultSet.getBigDecimal(CASH);
            int musicAmount = resultSet.getInt(MUSIC_AMOUNT);

            return User.getClient(id, username, password, cash, musicAmount);
        }

    }

}
