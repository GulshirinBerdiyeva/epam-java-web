package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.*;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.entity.Playlist;
import com.epam.task.web.project.entity.User;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MusicOrderService extends AbstractService<MusicOrder> {

    public MusicOrderService(DaoHelperFactory daoHelperFactory) {
        super(daoHelperFactory, MusicOrder.getTableName());
    }

    public MusicOrder createOrder(User user, Music music) {
        int discount = user.getDiscount();

        BigDecimal musicPrice = music.getPrice();

        BigDecimal percentageAmount = BigDecimal.valueOf(discount).multiply(musicPrice.divide(BigDecimal.valueOf(100)));

        BigDecimal finalPrice = musicPrice.subtract(percentageAmount).setScale(2, RoundingMode.HALF_UP);

        return new MusicOrder(user.getId(), music.getId(), discount, finalPrice, false);
    }

    public void confirmMusicOrder(MusicOrder musicOrder, User user, Music music) throws ServiceException{
        DaoHelper daoHelper = getDaoHelperFactory().create();

        try {
            UserDao userDao = (UserDao) daoHelper.createDao(User.getTableName());
            MusicOrderDao musicOrderDao = (MusicOrderDao) daoHelper.createDao(getDaoType());
            PlaylistDao playlistDao = (PlaylistDao) daoHelper.createDao(Playlist.getTableName());

            daoHelper.startTransaction();

            user.setCash(updateUserCash(musicOrder, user));
            user.setMusicAmount(user.getMusicAmount() + 1);

            musicOrder.setPayment(true);

            Playlist playlist = new Playlist(user.getId(), music.getId());

            userDao.updateCashAndMusicAmountById(user);
            musicOrderDao.save(musicOrder);
            playlistDao.save(playlist);

            daoHelper.endTransaction();

        } catch (DaoException e) {

            try {
                daoHelper.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException(daoException);
            }

            throw new ServiceException(e);
        }
    }

    private BigDecimal updateUserCash(MusicOrder musicOrder, User user) {
        BigDecimal userCash = user.getCash();

        BigDecimal paymentPrice = musicOrder.getFinalPrice();

        return userCash.subtract(paymentPrice).setScale(2, RoundingMode.HALF_UP);
    }

    public void cancelMusicOrder(MusicOrder musicOrder) throws ServiceException {
        try (DaoHelper daoHelper = getDaoHelperFactory().create()) {

            MusicOrderDao musicOrderDao = (MusicOrderDao) daoHelper.createDao(getDaoType());

            musicOrderDao.save(musicOrder);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}