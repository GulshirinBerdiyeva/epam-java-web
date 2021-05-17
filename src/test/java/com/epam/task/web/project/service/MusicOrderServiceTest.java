package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.*;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.entity.Playlist;
import com.epam.task.web.project.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class MusicOrderServiceTest {

    private static final User CLIENT = User.createClient(1L, "Client", "client", new BigDecimal("10"), 10, 10);
    private static final Music MUSIC = new Music(1L, "Diamond", "Rihanna", "Rihanna.mp3", "Rihanna.jpg", new BigDecimal("3"));
    private static final MusicOrder MUSIC_ORDER = new MusicOrder(CLIENT.getId(), MUSIC.getId(), CLIENT.getDiscount(), new BigDecimal("2.70"), false);
    private static final User EXPECTED_CLIENT = User.createClient(1L, "Client", "client", new BigDecimal("7.30"), 11, 10);
    private static final MusicOrder EXPECTED_MUSIC_ORDER = new MusicOrder(CLIENT.getId(), MUSIC.getId(), CLIENT.getDiscount(), new BigDecimal("2.70"), true);

    private final DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
    private final MusicOrderService musicOrderService = new MusicOrderService(daoHelperFactory);
    private final DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
    private final UserDao userDao = Mockito.mock(UserDao.class);
    private final MusicOrderDao musicOrderDao = Mockito.mock(MusicOrderDao.class);
    private final PlaylistDao playlistDao = Mockito.mock(PlaylistDao.class);

    @Test
    public void createOrderShouldReturnMusicOrderWhenUserAndMusicApplied() {
        MusicOrder actual = musicOrderService.createOrder(CLIENT, MUSIC);

        Assert.assertEquals(MUSIC_ORDER, actual);
    }

    @Test
    public void confirmMusicOrderShouldReturnTrueAfterPaymentTransactionWhenParametersApplied() throws DaoException, ServiceException {
        //given
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserDao()).thenReturn(userDao);
        when(daoHelper.createMusicOrderDao()).thenReturn(musicOrderDao);
        when(daoHelper.createPlaylistDao()).thenReturn(playlistDao);
        doNothing().when(daoHelper).startTransaction();
        doNothing().when(daoHelper).endTransaction();
        doNothing().when(userDao).updateCashAndMusicAmountById(CLIENT);
        doNothing().when(musicOrderDao).save(MUSIC_ORDER);
        doNothing().when(playlistDao).save(any(Playlist.class));

        //when
        musicOrderService.confirmMusicOrder(MUSIC_ORDER, CLIENT, MUSIC);

        //then
        Assert.assertEquals(EXPECTED_CLIENT, CLIENT);
        Assert.assertEquals(EXPECTED_MUSIC_ORDER, MUSIC_ORDER);
    }

@Test(expected = ServiceException.class)
    public void confirmMusicOrderShouldThrowServiceException() throws DaoException, ServiceException {
        //given
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createUserDao()).thenReturn(userDao);
        when(daoHelper.createMusicOrderDao()).thenReturn(musicOrderDao);
        when(daoHelper.createPlaylistDao()).thenReturn(playlistDao);
        doNothing().when(daoHelper).startTransaction();
        doNothing().when(daoHelper).endTransaction();
        doNothing().when(userDao).updateCashAndMusicAmountById(CLIENT);
        doNothing().when(musicOrderDao).save(MUSIC_ORDER);
        doThrow(ServiceException.class).when(playlistDao).save(any(Playlist.class));

        //when
        musicOrderService.confirmMusicOrder(MUSIC_ORDER, CLIENT, MUSIC);
    }

}