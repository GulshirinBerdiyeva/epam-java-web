package com.epam.task.web.project.command;

import com.epam.task.web.project.service.AlbumService;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubmitAlbumCommand implements Command{

    private final AlbumService albumService;

    private static final String ALBUM_TITLE = "albumTitle";
    private static final String ALBUM_ELEMENTS = "albumElements";
    private static final String ALBUMS_PAGE_COMMAND = "?command=albums";

    public SubmitAlbumCommand(AlbumService albumService) {
        this.albumService = albumService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String albumTitle = request.getParameter(ALBUM_TITLE);
        String[] selectedMusicsId = request.getParameterValues(ALBUM_ELEMENTS);

        boolean isExist = albumService.isExist(albumTitle);
        if (isExist) {
            albumService.removeByTitle(albumTitle);
        }
        albumService.saveAlbum(albumTitle, selectedMusicsId);

        return CommandResult.redirect(ALBUMS_PAGE_COMMAND);
    }

}
