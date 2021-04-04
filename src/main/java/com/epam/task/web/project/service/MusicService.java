package com.epam.task.web.project.service;

import com.epam.task.web.project.dao.DaoHelper;
import com.epam.task.web.project.dao.DaoHelperFactory;
import com.epam.task.web.project.dao.MusicDao;
import com.epam.task.web.project.entity.Music;

import java.util.List;
import java.util.Optional;

public class MusicService implements Service {

    private final DaoHelperFactory daoHelperFactory;

    public MusicService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<Music> getAllMusics() throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            return musicDao.getAll();

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Music> getMusicById(Long id) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            return musicDao.getById(id);

        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Music> findMusicByTitle(String title) throws ServiceException {
        try (DaoHelper daoHelper = daoHelperFactory.create()) {
            MusicDao musicDao = daoHelper.createMusicDao();

            return musicDao.findMusicByTitle(title);

        } catch (Exception e) {
            throw new ServiceException(e);
        }

    }

}
