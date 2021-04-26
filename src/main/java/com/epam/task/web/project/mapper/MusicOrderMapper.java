package com.epam.task.web.project.mapper;

import com.epam.task.web.project.entity.MusicOrder;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MusicOrderMapper implements Mapper<MusicOrder> {

    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String MUSIC_ID = "music_id";
    public static final String DATE = "date";
    public static final String DISCOUNT = "discount";
    public static final String FINAL_PRICE = "final_price";
    public static final String PAYMENT = "payment";

    @Override
    public MusicOrder map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID);
        Long userId = resultSet.getLong(USER_ID);
        Long musicId = resultSet.getLong(MUSIC_ID);
        LocalDateTime date = resultSet.getTimestamp(DATE).toLocalDateTime();
        int discount = resultSet.getInt(DISCOUNT);
        BigDecimal finalPrice = resultSet.getBigDecimal(FINAL_PRICE);
        boolean payment = resultSet.getBoolean(PAYMENT);

        return new MusicOrder(id, userId, musicId, date, discount, finalPrice, payment);
    }

}
