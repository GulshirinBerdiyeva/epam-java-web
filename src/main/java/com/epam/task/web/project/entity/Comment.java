package com.epam.task.web.project.entity;

import java.time.LocalDateTime;

public class Comment implements Entity{

    private Long id;
    private Long userId;
    private String username;
    private Long musicId;
    private LocalDateTime date;
    private String comment;

    public Comment(Long id, Long userId, String username,
                   Long musicId, LocalDateTime date, String comment) {

        this.id = id;
        this.userId = userId;
        this.username = username;
        this.musicId = musicId;
        this.date = date;
        this.comment = comment;
    }

    public Comment(Long userId, Long musicId, String comment) {
        this.userId = userId;
        this.musicId = musicId;
        this.comment = comment;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Long getMusicId() {
        return musicId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

}
