package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.AlbumService;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AlbumMusicsCommand implements Command {

    private final AlbumService albumService;

    private static final String SELECTED_ALBUM_TITLE = "selectedAlbumTitle";
    private static final String ALBUM = "album";
    private static final String SHOW_ALBUM = "showAlbum";

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";

    public AlbumMusicsCommand(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String albumTitle = request.getParameter(SELECTED_ALBUM_TITLE);

        List<Music> albumMusics = albumService.getAllMusicsByAlbumTitle(albumTitle);

        request.setAttribute(SHOW_ALBUM, true);
        request.setAttribute(ALBUM, albumMusics);

        return CommandResult.forward(MAIN_PAGE);
    }

}
