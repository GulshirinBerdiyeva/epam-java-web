package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.*;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.entity.Playlist;
import com.epam.task.web.project.entity.User;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MusicOrderService {

    private final DaoHelperFactory daoHelperFactory;

    public MusicOrderService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void confirmMusicOrder(MusicOrder musicOrder, User user, Music music) throws ServiceException{
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao userDao = daoHelper.createUserDao();
            MusicOrderDao musicOrderDao = daoHelper.createMusicOrderDao();
            PlaylistDao playlistDao = daoHelper.createPlaylistDao();

            daoHelper.startTransaction();

            user.setCash(updateUserCash(musicOrder, user));
            user.setMusicAmount(user.getMusicAmount() + 1);

            musicOrder.setPayment(true);

            Long musicId = music.getId();
            Playlist playlist = new Playlist(user.getId(), musicId);

            userDao.updateCashAndMusicAmountById(user);
            musicOrderDao.save(musicOrder);
            playlistDao.save(playlist);

            daoHelper.commit();
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

            musicOrderDao.save(musicOrder);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public MusicOrder createOrder(User user, Music music) {
        int musicAmount = user.getMusicAmount();
        int discount = getDiscount(musicAmount);

        BigDecimal musicPrice = music.getPrice();
        BigDecimal finalPrice = getFinalPrice(discount, musicPrice);

        return new MusicOrder(user.getId(), music.getId(), discount, finalPrice, false);
    }

    private BigDecimal getFinalPrice(int discount, BigDecimal musicPrice) {
        BigDecimal percentageAmount = BigDecimal.valueOf(discount).multiply(musicPrice.divide(BigDecimal.valueOf(100)));
        return musicPrice.subtract(percentageAmount).setScale(2, RoundingMode.HALF_UP);
    }

    private int getDiscount(int musicAmount) {
        switch (musicAmount) {
            case 3:
                return 20;
            case 7:
                return 30;
            case 9:
                return 50;
            default:
                return 0;
        }
    }

}