package com.epam.task.web.project.entity;

public class Album implements Entity {

    private static final String TABLE_NAME = "album";

    private Long id;
    private Long musicID;
    private String albumTitle;
    private Music music;

    public Album(Long id, Long musicID, String albumTitle, Music music) {
        this.id = id;
        this.musicID = musicID;
        this.albumTitle = albumTitle;
        this.music = music;
    }

    public Album(Long musicID, String album_title) {
        this.musicID = musicID;
        this.albumTitle = album_title;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getMusicID() {
        return musicID;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public Music getMusic() {
        return music;
    }

}