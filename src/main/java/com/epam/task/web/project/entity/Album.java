package com.epam.task.web.project.entity;

public class Album implements Entity {

    private Long id;
    private Long musicID;
    private String album_title;
    private Music music;

    public Album(Long id, Long musicID, String album_title, Music music) {
        this.id = id;
        this.musicID = musicID;
        this.album_title = album_title;
        this.music = music;
    }

    public Album(Long musicID, String album_title) {
        this.musicID = musicID;
        this.album_title = album_title;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getMusicID() {
        return musicID;
    }

    public String getAlbum_title() {
        return album_title;
    }

    public Music getMusic() {
        return music;
    }

}
