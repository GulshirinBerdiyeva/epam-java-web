package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.entity.User;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class DeleteMusicCommand implements Command {

    private static final String SELECTED_MUSIC = "selectedMusic";
    private static final String MUSIC_IS_ABSENT = "musicIsAbsent";
    private static final String MUSIC_REMOVED = "musicRemoved";

    private static final String SEARCH_PAGE = "/WEB-INF/view/search.jsp";

    private final MusicService musicService;
    private final UserService userService;

    public DeleteMusicCommand(MusicService musicService, UserService userService) {
        this.musicService = musicService;
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Music music = (Music) request.getSession().getAttribute(SELECTED_MUSIC);

        if (music == null) {
            throw new NullPointerException("Parameter is NULL...");
        }

        Long musicId = music.getId();
        Optional<Music> optionalMusic = musicService.getMusicById(musicId);
        List<User> allClients = userService.getAllClients();

        if (optionalMusic.isPresent()) {
            musicService.removeMusicById(allClients, musicId);
            request.setAttribute(MUSIC_REMOVED, true);
        } else {
            request.setAttribute(MUSIC_IS_ABSENT, true);
        }

        return CommandResult.forward(SEARCH_PAGE);
    }

}
