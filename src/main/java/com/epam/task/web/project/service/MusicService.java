package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.DaoHelper;
import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.dao.MusicDao;
import com.epam.task.web.project.entity.Music;

import java.util.List;

public class MusicService {

    private final DaoHelperFactory daoHelperFactory;

    public MusicService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Music> getMusics() throws ServiceException{
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            return musicDao.getAll();

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

}
