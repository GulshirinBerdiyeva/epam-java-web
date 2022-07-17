package com.epam.task.web.project.dao;

import com.epam.task.web.project.entity.Album;
import com.epam.task.web.project.mapper.AlbumMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AlbumDao extends AbstractDao<Album> {

    private static final Logger LOGGER = LogManager.getLogger(AlbumDao.class);

    private static final String SELECT_BY_ALBUM_TITLE = "SELECT * FROM album " +
                                                        "INNER JOIN music ON album.music_id = music.id " +
                                                        "WHERE album.album_title = ?";
    private static final String SELECT_BY_MUSIC_ID = "SELECT * FROM album INNER JOIN music ON album.music_id = music.id";
    private static final String INSERT_INTO_ALBUM = "INSERT INTO album (music_id, album_title) VALUES (?, ?)";
    private static final String SELECT_EXISTS = "SELECT EXISTS(SELECT * from album WHERE album_title = ?)";
    private static final String REMOVE_BY_MUSIC_ID = "DELETE FROM album WHERE music_id = ?";

    private static final AtomicReference<AlbumDao> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private AlbumDao() {
        super(new AlbumMapper(), Album.getTableName());
    }

    public static AlbumDao getInstance() {
        if (!IS_INSTANCE_CREATED.get()) {

            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    AlbumDao albumDao = new AlbumDao();

                    INSTANCE.set(albumDao);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created AlbumDao instance");
                }

            } finally {
                INSTANCE_LOCK.unlock();
            }

        }

        return INSTANCE.get();
    }

    public boolean exist(String title) throws DaoException{
        return exist(SELECT_EXISTS, title);
    }

    public List<Album> getAllByAlbumTitle(String albumTitle) throws DaoException {
        return executeQuery(SELECT_BY_ALBUM_TITLE, albumTitle);
    }

    @Override
    public List<Album> getAll() throws DaoException {
        return executeQuery(SELECT_BY_MUSIC_ID);
    }

    @Override
    public void save(Album item) throws DaoException {
        executeUpdate(INSERT_INTO_ALBUM, item.getMusicID(), item.getAlbumTitle());
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_MUSIC_ID, id);
    }

}