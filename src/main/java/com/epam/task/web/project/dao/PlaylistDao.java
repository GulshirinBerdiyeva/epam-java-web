package com.epam.task.web.project.dao;

import com.epam.task.web.project.entity.Playlist;

import com.epam.task.web.project.mapper.PlaylistMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PlaylistDao extends AbstractDao<Playlist> {

    private static final Logger LOGGER = LogManager.getLogger(PlaylistDao.class);

    private static final String SELECT_BY_USER_ID = "SELECT * FROM playlist " +
                                                    "INNER JOIN music ON playlist.music_id = music.id " +
                                                    "WHERE user_id = ?";
    private static final String SELECT_EXISTS = "SELECT EXISTS(SELECT * from playlist WHERE user_id = ? AND music_id = ?)";
    private static final String INSERT_PLAYLIST = "INSERT INTO playlist (user_id, music_id) VALUES (?, ?)";
    private static final String REMOVE_BY_MUSIC_ID = "DELETE FROM playlist WHERE music_id = ?";

    private static final AtomicReference<PlaylistDao> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private PlaylistDao() {
        super(new PlaylistMapper(), Playlist.getTableName());
    }

    public static PlaylistDao getInstance() {
        if (!IS_INSTANCE_CREATED.get()) {

            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    PlaylistDao playlistDao = new PlaylistDao();

                    INSTANCE.set(playlistDao);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created PlaylistDao instance");
                }

            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }

    public List<Playlist> getAllMusicsByUserId(Long userId) throws DaoException {
        return executeQuery(SELECT_BY_USER_ID, userId);
    }

    public boolean exist(Long userId, Long musicId) throws DaoException{
        return exist(SELECT_EXISTS, userId, musicId);
    }

    @Override
    public void save(Playlist item) throws DaoException {
        executeUpdate(INSERT_PLAYLIST, item.getUserId(), item.getMusicId());
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_MUSIC_ID, id);
    }

}