package com.epam.task.web.project.dao;

import com.epam.task.web.project.connection.ProxyConnection;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {

    private static final String USER = "user";
    private static final String MUSIC = "music";
    private static final String MUSIC_ORDER = "music_order";
    private static final String PLAYLIST = "playlist";
    private static final String ALBUM = "album";
    private static final String COMMENT = "comment";

    private final ProxyConnection proxyConnection;

    public DaoHelper(ProxyConnection proxyConnection) {
        this.proxyConnection = proxyConnection;
    }

    public Dao createDao(String type) throws DaoException {
        switch (type) {
            case USER:
                return createUserDao();
            case MUSIC:
                return createMusicDao();
            case MUSIC_ORDER:
                return createMusicOrderDao();
            case PLAYLIST:
                return createPlaylistDao();
            case ALBUM:
                return createAlbumDao();
            case COMMENT:
                return createCommentDao();
            default:
                throw new DaoException("Unknown type of Dao! \"" + type + "\"");
        }
    }

    private UserDao createUserDao() {
        UserDao userDao = UserDao.getInstance();

        userDao.setProxyConnection(proxyConnection);

        return userDao;
    }

    private MusicDao createMusicDao() {
        MusicDao musicDao = MusicDao.getInstance();

        musicDao.setProxyConnection(proxyConnection);

        return musicDao;
    }

    private MusicOrderDao createMusicOrderDao() {
        MusicOrderDao musicOrderDao = MusicOrderDao.getInstance();

        musicOrderDao.setProxyConnection(proxyConnection);

        return musicOrderDao;
    }

    private PlaylistDao createPlaylistDao() {
        PlaylistDao playlistDao = PlaylistDao.getInstance();

        playlistDao.setProxyConnection(proxyConnection);

        return playlistDao;
    }

    private AlbumDao createAlbumDao() {
        AlbumDao albumDao = AlbumDao.getInstance();

        albumDao.setProxyConnection(proxyConnection);

        return albumDao;
    }

    private CommentDao createCommentDao() {
        CommentDao commentDao = CommentDao.getInstance();

        commentDao.setProxyConnection(proxyConnection);

        return commentDao;
    }


    public void startTransaction() throws DaoException {
        try {
            proxyConnection.setAutoCommit(false);

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            proxyConnection.commit();
            proxyConnection.setAutoCommit(true);

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    public void rollback() throws DaoException {
        try {
            proxyConnection.rollback();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public void close() throws DaoException {
        try {
            proxyConnection.setAutoCommit(true);
            proxyConnection.close();

        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

}
