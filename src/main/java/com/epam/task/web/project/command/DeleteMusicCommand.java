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
    private static final String DELETE_DIALOG = "deleteDialog";
    private static final String CAN_EDIT = "canEdit";
    private static final String CAN_DELETE = "canDelete";
    private static final String DELETE = "delete";
    private static final String MUSIC_REMOVED = "musicRemoved";

    private static final String MAIN_PAGE = "/WEB-INF/view/main.jsp";
    private static final String PURCHASE_PAGE = "/WEB-INF/view/purchase.jsp";

    private final MusicService musicService;
    private final UserService userService;

    public DeleteMusicCommand(MusicService musicService, UserService userService) {
        this.musicService = musicService;
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Music music = (Music) request.getSession().getAttribute(SELECTED_MUSIC);
        String deleteDialogValue = request.getParameter(DELETE_DIALOG);
        String deleteValue = request.getParameter(DELETE);

        if ((music == null || deleteDialogValue == null) && deleteValue == null) {
            throw new NullPointerException("Parameter is NULL!");
        }

        boolean showDeleteDialog = Boolean.parseBoolean(deleteDialogValue);
        if (showDeleteDialog) {
            request.setAttribute(CAN_EDIT, true);
            request.setAttribute(CAN_DELETE, true);

            return CommandResult.forward(PURCHASE_PAGE);
        }

        boolean delete = Boolean.parseBoolean(deleteValue);
        if (delete) {
            Long musicId = music.getId();
            Optional<Music> optionalMusic = musicService.getMusicById(musicId);
            List<User> allClients = userService.getAllClients();

            if (optionalMusic.isPresent()) {
                musicService.removeMusicById(allClients, musicId);
                request.setAttribute(MUSIC_REMOVED, true);
            } else {
                request.setAttribute(MUSIC_IS_ABSENT, true);
            }

            return CommandResult.forward(MAIN_PAGE);
        } else {
            request.setAttribute(CAN_EDIT, true);
            return CommandResult.forward(PURCHASE_PAGE);
        }
    }

}