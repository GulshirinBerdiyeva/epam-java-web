package com.epam.task.web.project.command;

import com.epam.task.web.project.entity.Comment;
import com.epam.task.web.project.entity.Music;
import com.epam.task.web.project.localizer.DateTimeLocalizer;
import com.epam.task.web.project.service.CommentService;
import com.epam.task.web.project.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

public class CommentsCommand implements Command {

    private static final String SELECTED_MUSIC = "selectedMusic";
    private static final String SELECTED_MUSIC_COMMENTS = "selectedMusicComments";
    private static final String LOCAL = "local";

    private static final String PURCHASE_PAGE_COMMAND = "?command=purchasePage";

    private final CommentService commentService;

    public CommentsCommand(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession();
        Music music = (Music) session.getAttribute(SELECTED_MUSIC);

        if (music == null) {
            throw new NullPointerException("Parameter is NULL!");
        }

        Long musicId = music.getId();
        List<Comment> comments = commentService.findCommentsByMusicId(musicId);

        if (!comments.isEmpty()) {
            String locale = (String) session.getAttribute(LOCAL);
            DateTimeLocalizer localizer = new DateTimeLocalizer(locale);

            for (Comment comment : comments) {
                String localizedDateTime = localizer.localizeDateTime(comment.getDateTime());
                comment.setLocaleDateTime(localizedDateTime);
            }
        }

        session.setAttribute(SELECTED_MUSIC_COMMENTS, comments);
        return CommandResult.redirect(PURCHASE_PAGE_COMMAND);
    }

}