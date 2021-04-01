package com.epam.task.web.project.entity;

public class Music implements Entity{

    private final Long id;
    private String title;
    private String artist;
    private String audioPath;
    private String imagePath;

    public Music(Long id, String title, String artist, String audioPath, String imagePath) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.audioPath = audioPath;
        this.imagePath = imagePath;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public Long getId() {
        return null;
    }
}
