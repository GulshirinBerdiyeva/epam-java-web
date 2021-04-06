package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.*;
import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.entity.Playlist;
import com.epam.task.web.project.entity.User;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MusicOrderService implements Service {

    private final DaoHelperFactory daoHelperFactory;

    public MusicOrderService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void confirmMusicOrder(MusicOrder musicOrder, User user, Long musicId) throws ServiceException{

        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao userDao = daoHelper.createUserDao();
            MusicOrderDao musicOrderDao = daoHelper.createMusicOrderDao();
            PlaylistDao playlistDao = daoHelper.createPlaylistDao();

            if (musicOrder != null || user != null) {

                daoHelper.startTransaction();

                user.setCash(updateUserCash(musicOrder, user));
                user.setMusicAmount(user.getMusicAmount() + 1);

                musicOrder.setPayment(true);

                Playlist playlist = new Playlist(user.getId(), musicId);

                userDao.save(user);
                musicOrderDao.save(musicOrder);
                playlistDao.save(playlist);

                daoHelper.commit();
            }

        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }

    private BigDecimal updateUserCash(MusicOrder musicOrder, User user) {
        BigDecimal userCash = user.getCash();
        BigDecimal paymentPrice = musicOrder.getFinalPrice();

        return userCash.subtract(paymentPrice).setScale(2, RoundingMode.HALF_UP);
    }

    public void cancelMusicOrder(MusicOrder musicOrder) throws ServiceException {

        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicOrderDao musicOrderDao = daoHelper.createMusicOrderDao();

            if (musicOrder != null) {
                musicOrderDao.save(musicOrder);
            }

        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }

}