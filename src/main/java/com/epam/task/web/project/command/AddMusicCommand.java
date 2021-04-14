package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.service.MusicService;
import com.epam.task.web.project.service.ServiceException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class AddMusicCommand implements Command {

    private final MusicService musicService;
    private static final String MAIN_COMMAND = "?command=main";

    public AddMusicCommand(MusicService musicService) {
        this.musicService = musicService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            List<FileItem> data = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

            Music extractedMusic = musicService.extractData(data);

            Optional<Music> optionalMusic = musicService.findMusicByArtistAndTitle(
                                                extractedMusic.getArtist(), extractedMusic.getTitle());
            if (optionalMusic.isPresent()) {
                Music music = optionalMusic.get();
                musicService.updateMusic(music);
            } else {
                musicService.saveMusic(extractedMusic);
            }

            return CommandResult.redirect(request.getRequestURI() + MAIN_COMMAND);
        } catch (FileUploadException e) {
            throw new ServiceException(e);
        }
    }

}
