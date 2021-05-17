package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.*;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class MusicServiceTest {

    private static final String ARTIST = "artist";
    private static final String ARTIST_VALUE = "Rihanna";
    private static final String TITLE = "title";
    private static final String TITLE_VALUE = "Diamond";
    private static final Music FIRST_MUSIC = new Music(1L, TITLE_VALUE, ARTIST_VALUE, "Rihanna.mp3", "Rihanna.jpg", new BigDecimal("3"));
    private static final Music SECOND_MUSIC = new Music(2L, "Umbrella", ARTIST_VALUE, "Rihanna.mp3", "Rihanna.jpg", new BigDecimal("3"));
    private static final Music THIRD_MUSIC = new Music(3L, TITLE_VALUE, "Sam Smith", "Rihanna.mp3", "Rihanna.jpg", new BigDecimal("3"));
    private static final List<Music> MUSICS_BY_ARTIST = Arrays.asList(FIRST_MUSIC, SECOND_MUSIC);
    private static final List<Music> MUSICS_BY_TITLE = Arrays.asList(FIRST_MUSIC, THIRD_MUSIC);
    private static final User FIRST_CLIENT = User.createClient(1L, "Client", "client", new BigDecimal("10"), 10, 10);
    private static final User SECOND_CLIENT = User.createClient(2L, "Client", "client", new BigDecimal("10"), 10, 10);
    private static final int EXPECTED_FIRST_CLIENT_MUSIC_AMOUNT = FIRST_CLIENT.getMusicAmount() - 1;
    private static final int EXPECTED_SECOND_CLIENT_MUSIC_AMOUNT = SECOND_CLIENT.getMusicAmount() - 1;
    private static final List<User> USERS = Arrays.asList(FIRST_CLIENT, SECOND_CLIENT);

    private final DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
    private final MusicService musicService = new MusicService(daoHelperFactory);
    private final DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
    private final UserDao userDao = Mockito.mock(UserDao.class);
    private final MusicDao musicDao = Mockito.mock(MusicDao.class);
    private final PlaylistDao playlistDao = Mockito.mock(PlaylistDao.class);

    @Test
    public void findMusicsBySearchParameterShouldReturnListByArtistWhenSearchParameterArtist() throws DaoException, ServiceException {
        //given
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createMusicDao()).thenReturn(musicDao);
        when(musicDao.findMusicsByArtist(ARTIST_VALUE)).thenReturn(MUSICS_BY_ARTIST);

        //when
        List<Music> actual = musicService.findMusicsBySearchParameter(ARTIST, ARTIST_VALUE);

        //then
        Assert.assertEquals(MUSICS_BY_ARTIST, actual);
    }

    @Test
    public void findMusicsBySearchParameterShouldReturnListByTitleWhenSearchParameterTitle() throws DaoException, ServiceException {
        //given
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createMusicDao()).thenReturn(musicDao);
        when(musicDao.findMusicsByTitle(TITLE_VALUE)).thenReturn(MUSICS_BY_TITLE);

        //when
        List<Music> actual = musicService.findMusicsBySearchParameter(TITLE, TITLE_VALUE);

        //then
        Assert.assertEquals(MUSICS_BY_TITLE, actual);
    }

    @Test
    public void removeMusicByIdShouldDecreaseUserMusicAmount() throws DaoException, ServiceException {
        //given
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserDao()).thenReturn(userDao);
        when(daoHelper.createMusicDao()).thenReturn(musicDao);
        when(daoHelper.createPlaylistDao()).thenReturn(playlistDao);
        doNothing().when(daoHelper).startTransaction();
        doNothing().when(daoHelper).endTransaction();
        when(playlistDao.exist(FIRST_CLIENT.getId(), FIRST_MUSIC.getId())).thenReturn(true);
        when(playlistDao.exist(SECOND_CLIENT.getId(), FIRST_MUSIC.getId())).thenReturn(false);
        doNothing().when(userDao).updateMusicAmountById(FIRST_CLIENT.getId(), EXPECTED_FIRST_CLIENT_MUSIC_AMOUNT);
        doNothing().when(musicDao).removeById(FIRST_MUSIC.getId());

        //when
        musicService.removeMusicById(USERS, FIRST_MUSIC.getId());

        //then
        Assert.assertEquals(EXPECTED_FIRST_CLIENT_MUSIC_AMOUNT, FIRST_CLIENT.getMusicAmount());
        Assert.assertEquals(SECOND_CLIENT.getMusicAmount(), SECOND_CLIENT.getMusicAmount());
    }

    @Test(expected = ServiceException.class)
    public void removeMusicByIdShouldIncreaseUserMusicAmount() throws DaoException, ServiceException {
        //given
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserDao()).thenReturn(userDao);
        when(daoHelper.createMusicDao()).thenReturn(musicDao);
        when(daoHelper.createPlaylistDao()).thenReturn(playlistDao);
        doNothing().when(daoHelper).startTransaction();
        doNothing().when(daoHelper).endTransaction();
        when(playlistDao.exist(FIRST_CLIENT.getId(), FIRST_MUSIC.getId())).thenReturn(true);
        when(playlistDao.exist(SECOND_CLIENT.getId(), FIRST_MUSIC.getId())).thenReturn(false);
        doNothing().when(userDao).updateMusicAmountById(FIRST_CLIENT.getId(), EXPECTED_FIRST_CLIENT_MUSIC_AMOUNT);
        doThrow(ServiceException.class).when(musicDao).removeById(FIRST_MUSIC.getId());

        //when
        musicService.removeMusicById(USERS, FIRST_MUSIC.getId());
    }

}
