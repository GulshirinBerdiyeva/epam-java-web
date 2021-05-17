package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class EditMusicCommand implements Command {

    private static final String SELECTED_MUSIC = "selectedMusic";
    private static final String CAN_EDIT = "canEdit";
    private static final String MUSIC_IS_ABSENT = "musicIsAbsent";

    private static final String PURCHASE_PAGE = "/WEB-INF/view/purchase.jsp";
    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";

    private final MusicService musicService;

    public EditMusicCommand(MusicService musicService) {
        this.musicService = musicService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Music music = (Music) request.getSession().getAttribute(SELECTED_MUSIC);

        if (music == null) {
            throw new NullPointerException("Parameter is NULL!");
        }

        Optional<Music> optionalMusic = musicService.getMusicById(music.getId());

        if (optionalMusic.isPresent()) {
            request.setAttribute(CAN_EDIT, true);
            return CommandResult.forward(PURCHASE_PAGE);
        } else {
            request.setAttribute(MUSIC_IS_ABSENT, true);
            return CommandResult.forward(MAIN_PAGE);
        }
    }

}