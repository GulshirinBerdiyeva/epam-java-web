package com.epam.task.web.project.entity;

import java.time.LocalDateTime;

public class Comment implements Entity{

    private static final String TABLE_NAME = "comment";

    private Long id;
    private Long userId;
    private String username;
    private Long musicId;
    private LocalDateTime dateTime;
    private String localeDateTime;
    private String comment;

    public Comment(Long id, Long userId, String username, Long musicId, LocalDateTime dateTime, String comment) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.musicId = musicId;
        this.dateTime = dateTime;
        this.comment = comment;
    }

    public Comment(Long userId, Long musicId, String comment) {
        this.userId = userId;
        this.musicId = musicId;
        this.comment = comment;
    }

    public static String getTableName() {
        return TABLE_NAME;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getLocaleDateTime() {
        return localeDateTime;
    }

    public void setLocaleDateTime(String localeDateTime) {
        this.localeDateTime = localeDateTime;
    }

    public String getComment() {
        return comment;
    }

}