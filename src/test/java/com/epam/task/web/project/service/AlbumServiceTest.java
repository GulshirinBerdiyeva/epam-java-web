package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.*;
import com.epam.task.web.project.entity.Album;
import com.epam.task.web.project.entity.Music;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

public class AlbumServiceTest {

    private static final String ALBUM_TITLE = "top music";
    private static final Music FIRST_MUSIC = new Music(1L, "Diamond", "Rihanna", "Rihanna.mp3", "Rihanna.jpg", new BigDecimal("3"));
    private static final Music SECOND_MUSIC = new Music(2L, "Naturally", "Selena Gomez", "SelenaGomez.mp3", "SelenaGomez.jpg", new BigDecimal("3"));
    private static final Album FIRST_ALBUM_MUSIC = new Album(1L, FIRST_MUSIC.getId(), ALBUM_TITLE, FIRST_MUSIC);
    private static final Album SECOND_ALBUM_MUSIC = new Album(2L, SECOND_MUSIC.getId(), ALBUM_TITLE, SECOND_MUSIC);
    private static final List<Album> ALBUMS = Arrays.asList(FIRST_ALBUM_MUSIC, SECOND_ALBUM_MUSIC);
    private static final List<Music> MUSICS = Arrays.asList(FIRST_MUSIC, SECOND_MUSIC);

    private final DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
    private final AlbumService albumService = new AlbumService(daoHelperFactory);
    private final DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
    private final AlbumDao albumDao = Mockito.mock(AlbumDao.class);

    @Test
    public void getAllMusicsByAlbumTitleShouldReturnListWhenMusicIdApplied() throws DaoException, ServiceException {
        //given
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createAlbumDao()).thenReturn(albumDao);
        when(albumDao.getAllByAlbumTitle(ALBUM_TITLE)).thenReturn(ALBUMS);

        //when
        List<Music> actual = albumService.getAllMusicsByAlbumTitle(ALBUM_TITLE);

        //then
        Assert.assertEquals(MUSICS, actual);
    }

    @Test(expected = ServiceException.class)
    public void findCommentsByMusicIdShouldThrowServiceException() throws DaoException, ServiceException {
        //given
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createAlbumDao()).thenReturn(albumDao);
        when(albumDao.getAllByAlbumTitle(ALBUM_TITLE)).thenThrow(new DaoException());

        //when
        List<Music> actual = albumService.getAllMusicsByAlbumTitle(ALBUM_TITLE);
    }

}
