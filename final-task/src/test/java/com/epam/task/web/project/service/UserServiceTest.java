package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.*;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.mockito.Mockito.*;

public class UserServiceTest {

    private static final User VALID_CLIENT = User.createClient(1L, "Client", "client", new BigDecimal("10"), 10, 10);
    private static final User INVALID_CLIENT = User.createClient(1L, "Client", "client", new BigDecimal("0"), 10, 10);
    private static final User EXPECTED_CLIENT = User.createClient(1L, "Client", "client", new BigDecimal("20.00"), 10, 10);
    private static final Music MUSIC = new Music(1L, "Diamond", "Rihanna", "Rihanna.mp3", "Rihanna.jpg", new BigDecimal("3"));
    private static final BigDecimal CASH = new BigDecimal("10");
    private static final BigDecimal NEW_CASH = VALID_CLIENT.getCash().add(CASH).setScale(2, RoundingMode.HALF_UP);
    private final DaoHelperFactory daoHelperFactory = Mockito.mock(DaoHelperFactory.class);
    private final UserService userService = new UserService(daoHelperFactory);
    private final DaoHelper daoHelper = Mockito.mock(DaoHelper.class);
    private final UserDao userDao = Mockito.mock(UserDao.class);

    @Test
    public void isEnoughCashShouldReturnTrueWhenAppliedValidUser() {
        boolean actual = userService.isEnoughCash(VALID_CLIENT, MUSIC);

        Assert.assertTrue(actual);
    }

    @Test
    public void isEnoughCashShouldReturnFalseWhenAppliedInvalidUser() {
        boolean actual = userService.isEnoughCash(INVALID_CLIENT, MUSIC);

        Assert.assertFalse(actual);
    }

    @Test(expected = ServiceException.class)
    public void updateBalanceShouldThrowServiceException() throws DaoException, ServiceException {
        //given
        when(daoHelperFactory.create()).thenReturn(daoHelper);
        when(daoHelper.createDao("user")).thenReturn(userDao);
        doThrow(ServiceException.class).when(userDao).updateCashById(VALID_CLIENT.getId(), NEW_CASH);

        //when
        userService.updateBalance(VALID_CLIENT, CASH);
    }

}