package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.*;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.MusicOrder;
import com.epam.task.web.project.entity.User;

import java.math.BigDecimal;

public class MusicOrderService implements Service {

    private final DaoHelperFactory daoHelperFactory;

    public MusicOrderService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public void buy(User user, Music music) throws ServiceException, DaoException {
        DaoHelper daoHelper = daoHelperFactory.create();
        try {
            MusicOrderDao musicOrderDao = daoHelper.createMusicOrderDao();
            UserDao userDao = daoHelper.createUserDao();

            BigDecimal price = music.getPrice();
            BigDecimal cash = user.getCash();

            int enoughCash = price.compareTo(cash);
            if (enoughCash == 0 || enoughCash == -1) {

                int musicAmount = user.getMusicAmount();
                int discount = getDiscount(musicAmount);

                BigDecimal finalPrice;
                if (discount > 0) {
                    finalPrice = getFinalPrice(price, discount);
                } else {
                    finalPrice = price;
                }

                daoHelper.startTransaction();

                MusicOrder musicOrder = new MusicOrder(user.getId(), music.getId(), discount, finalPrice, true);
                musicOrderDao.save(musicOrder);

                BigDecimal updateCash = user.getCash().subtract(finalPrice);
                int updateMusicAmount = user.getMusicAmount() + 1;

                User updateUser = User.getClient(user.getId(), user.getUsername(), user.getPassword(), updateCash, updateMusicAmount);
                userDao.save(updateUser);

                daoHelper.commit();

            } else {
                MusicOrder musicOrder = new MusicOrder(user.getId(), music.getId(), 0, price, false);
                musicOrderDao.save(musicOrder);

            }
        } catch (Exception e) {
            daoHelper.rollback();
            throw new ServiceException(e);

        } finally {
            closeConnection(daoHelper);
        }

    }

    private int getDiscount(int musicAmount) {
        if (musicAmount >= 3 && musicAmount < 7) {
            return 10;
        }

        if (musicAmount >= 7 && musicAmount < 10) {
            return 20;
        }

        if (musicAmount >= 10) {
            return 30;
        }

        return 0;
    }

    private BigDecimal getFinalPrice(BigDecimal price, int discountValue) {
        BigDecimal discount = new BigDecimal(discountValue);
        BigDecimal hundred = new BigDecimal(100);

        return price.subtract((price.multiply(discount)).divide(hundred));
    }

    private void closeConnection(DaoHelper daoHelper) throws DaoException {
        try {
            daoHelper.close();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

}