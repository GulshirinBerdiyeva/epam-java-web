package com.epam.task.web.project.entity;

public class Playlist implements Entity{

    private static final String TABLE_NAME = "playlist";

    private Long id;
    private Long userId;
    private Long musicId;
    private Music music;

    public Playlist(Long id, Long userId, Long musicId, Music music) {
        this.id = id;
        this.userId = userId;
        this.musicId = musicId;
        this.music = music;
    }

    public Playlist(Long userId, Long musicId) {
        this.userId = userId;
        this.musicId = musicId;
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

    public Long getMusicId() {
        return musicId;
    }

    public Music getMusic() {
        return music;
    }

}