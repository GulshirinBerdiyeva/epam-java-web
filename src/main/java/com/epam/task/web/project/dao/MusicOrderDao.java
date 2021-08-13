package com.epam.task.web.project.dao;

import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.mapper.MusicOrderMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MusicOrderDao extends AbstractDao<MusicOrder>{

    private static final Logger LOGGER = LogManager.getLogger(MusicOrderDao.class);

    private static final String INSERT_MUSIC_ORDER = "INSERT INTO music_order " +
                                                     "(user_id, music_id, discount, final_price, payment) " +
                                                     "VALUES (?, ?, ?, ?, ?)";
    private static final String REMOVE_BY_MUSIC_ID = "DELETE FROM music_order WHERE music_id = ?";

    private static final AtomicReference<MusicOrderDao> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private MusicOrderDao() {
        super(new MusicOrderMapper(), MusicOrder.getTableName());
    }

    public static MusicOrderDao getInstance() {
        if (!IS_INSTANCE_CREATED.get()) {

            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    MusicOrderDao musicOrderDao = new MusicOrderDao();

                    INSTANCE.set(musicOrderDao);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created MusicOrderDao instance");
                }

            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }

    @Override
    public void save(MusicOrder item) throws DaoException {
        executeUpdate(INSERT_MUSIC_ORDER, item.getUserId(), item.getMusicId(),
                      item.getDiscount(), item.getFinalPrice(), item.isPayment());
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_MUSIC_ID, id);
    }

}