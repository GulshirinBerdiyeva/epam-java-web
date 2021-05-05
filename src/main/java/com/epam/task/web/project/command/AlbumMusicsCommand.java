package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.AlbumService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.InputParameterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AlbumMusicsCommand implements Command {

    private static final String SELECTED_ALBUM_TITLE = "selectedAlbumTitle";
    private static final String ALBUM = "album";
    private static final String SIZE = "size";
    private static final String MUSIC = "music";
    private static final String SHOW_ALBUM = "showAlbum";

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";

    private final AlbumService albumService;
    private final InputParameterValidator validator;

    public AlbumMusicsCommand(AlbumService albumService, InputParameterValidator validator) {
        this.albumService = albumService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        String albumTitle = request.getParameter(SELECTED_ALBUM_TITLE);

        boolean isValid = validator.isValidString(albumTitle);
        if (!isValid) {
            throw new NullPointerException("Parameter is NULL...");
        }

        List<Music> musics = albumService.getAllMusicsByAlbumTitle(albumTitle);

        request.setAttribute(SHOW_ALBUM, true);
        session.setAttribute(ALBUM, musics);
        session.setAttribute(SIZE, musics.size());
        request.setAttribute(MUSIC, musics.get(0));

        return CommandResult.forward(MAIN_PAGE);
    }

}
