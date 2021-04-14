package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Album;
import com.epam.task.web.project.service.AlbumService;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;

public class AlbumsCommand implements Command {

    private final AlbumService albumsService;

    private static final String ALBUMS_TITLE = "albumsTitle";
    private static final String ALBUMS_PAGE = "/WEB-INF/view/albums.jsp";

    public AlbumsCommand(AlbumService albumsService) {
        this.albumsService = albumsService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List<Album> albums = albumsService.getAllAlbums();

        HashSet<String> albumsTitle = new HashSet<>();
        albums.forEach(album -> albumsTitle.add(album.getAlbum_title()));

        request.setAttribute(ALBUMS_TITLE, albumsTitle);
        return CommandResult.forward(ALBUMS_PAGE);
    }

}
