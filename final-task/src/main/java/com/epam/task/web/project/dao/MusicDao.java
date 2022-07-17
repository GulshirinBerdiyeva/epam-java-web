package com.epam.task.web.project.dao;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.mapper.MusicMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MusicDao extends AbstractDao<Music>{

    private static final Logger LOGGER = LogManager.getLogger(MusicDao.class);

    private static final String UPDATE_MUSIC = "UPDATE music SET title = ?, artist = ?, audio_file_name = ?, " +
                                                                 "image_file_name = ?, price = ? WHERE id = ?";
    private static final String INSERT_MUSIC = "INSERT INTO music " +
                                               "(title, artist, audio_file_name, image_file_name, price) " +
                                               "VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ARTIST_AND_TITLE = "SELECT * FROM music WHERE artist = ? AND title = ?";
    private static final String SELECT_BY_ARTIST = "SELECT * FROM music WHERE artist LIKE CONCAT( '%',?,'%')";
    private static final String SELECT_BY_TITLE = "SELECT * FROM music WHERE title LIKE CONCAT( '%',?,'%')";
    private static final String UPDATE_PRICE = "UPDATE music SET price = ? WHERE id = ?";
    private static final String REMOVE_BY_ID = "DELETE FROM music WHERE id = ?";

    private static final AtomicReference<MusicDao> INSTANCE = new AtomicReference<>();
    private static final AtomicBoolean IS_INSTANCE_CREATED = new AtomicBoolean();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();

    private MusicDao() {
        super(new MusicMapper(), Music.getTableName());
    }

    public static MusicDao getInstance() {
        if (!IS_INSTANCE_CREATED.get()) {

            INSTANCE_LOCK.lock();
            try {
                if (!IS_INSTANCE_CREATED.get()) {
                    MusicDao musicDao = new MusicDao();

                    INSTANCE.set(musicDao);
                    IS_INSTANCE_CREATED.set(true);

                    LOGGER.info("Created MusicDao instance");
                }

            } finally {
                INSTANCE_LOCK.unlock();
            }
        }

        return INSTANCE.get();
    }

    public Optional<Music> getMusicByArtistAndTitle(String artist, String title) throws DaoException {
        return executeForSingleResult(SELECT_BY_ARTIST_AND_TITLE, artist, title);
    }

    public List<Music> getMusicsByArtist(String artist) throws DaoException {
        return executeQuery(SELECT_BY_ARTIST, artist);
    }

    public List<Music> getMusicsByTitle(String title) throws DaoException {
        return executeQuery(SELECT_BY_TITLE, title);
    }

    public void updatePriceById(Long id, BigDecimal price) throws DaoException {
        executeUpdate(UPDATE_PRICE, price, id);
    }

    public void updateMusic(Music music) throws DaoException {
        executeUpdate(UPDATE_MUSIC, music.getTitle(), music.getArtist(), music.getAudioFileName(),
                      music.getImageFileName(), music.getPrice(), music.getId());
    }

    @Override
    public void save(Music item) throws DaoException {
        executeUpdate(INSERT_MUSIC, item.getTitle(), item.getArtist(), item.getAudioFileName(),
                      item.getImageFileName(), item.getPrice());
    }

    @Override
    public void removeById(Long id) throws DaoException {
        executeUpdate(REMOVE_BY_ID, id);
    }

}