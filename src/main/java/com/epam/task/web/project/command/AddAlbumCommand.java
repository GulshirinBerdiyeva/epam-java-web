package com.epam.task.web.project.command;

import com.epam.task.web.project.service.AlbumService;
import com.epam.task.web.project.service.ServiceException;
import com.epam.task.web.project.validator.InputParameterValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddAlbumCommand implements Command{

    private static final String ALBUM_TITLE = "albumTitle";
    private static final String ALBUM_ELEMENTS = "albumElements";
    private static final String EMPTY_INPUT_PARAMETERS = "emptyInputParameters";

    private static final String CREATE_ALBUM_PAGE = "/WEB-INF/view/createAlbum.jsp";
    private static final String ALBUMS_PAGE_COMMAND = "?command=albums";

    private final AlbumService albumService;
    private InputParameterValidator validator;

    public AddAlbumCommand(AlbumService albumService, InputParameterValidator validator) {
        this.albumService = albumService;
        this.validator = validator;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String albumTitle = request.getParameter(ALBUM_TITLE);
        String[] selectedMusicsId = request.getParameterValues(ALBUM_ELEMENTS);

        boolean isValid = validator.isValidString(albumTitle);
        if (!isValid || selectedMusicsId == null) {
            request.setAttribute(EMPTY_INPUT_PARAMETERS, true);
            return CommandResult.forward(CREATE_ALBUM_PAGE);
        }

        boolean isExist = albumService.exist(albumTitle);
        if (isExist) {
            albumService.removeByTitle(albumTitle);
        }

        albumService.saveAlbum(albumTitle, selectedMusicsId);

        return CommandResult.redirect(ALBUMS_PAGE_COMMAND);
    }

}
