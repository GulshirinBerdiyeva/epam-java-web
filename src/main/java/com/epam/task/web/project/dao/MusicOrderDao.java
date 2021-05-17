package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;
import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.mapper.MusicOrderMapper;

public class MusicOrderDao extends AbstractDao<MusicOrder>{

    private static final String TABLE_NAME = "music_order";

    private static final String INSERT_MUSIC_ORDER = "INSERT INTO music_order (user_id, music_id, discount, final_price, payment) VALUES (?, ?, ?, ?, ?)";
    private static final String REMOVE_BY_MUSIC_ID = "DELETE FROM music_order WHERE music_id = ?";

    public MusicOrderDao(ProxyConnection proxyConnection) {
        super(proxyConnection, new MusicOrderMapper(), TABLE_NAME);
    }

    @Override
    public void save(MusicOrder item) throws DaoException {
        executeUpdate(INSERT_MUSIC_ORDER, item.getUserId(), item.getMusicId(), item.getDiscount(), item.getFinalPrice(), item.isPayment());
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_MUSIC_ID, id);
    }

}