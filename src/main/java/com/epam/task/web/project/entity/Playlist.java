package com.epam.task.web.project.entity;

public class Playlist implements Entity{

    private final Long id;
    private final Long userId;
    private final Long musicId;

    public Playlist(Long id, Long userId, Long musicId) {
        this.id = id;
        this.userId = userId;
        this.musicId = musicId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getMusicId() {
        return musicId;
    }

    @Override
    public Long getId() {
        return null;
    }
}
