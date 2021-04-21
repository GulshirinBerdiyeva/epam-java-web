package com.epam.task.web.project.entity;

import java.math.BigDecimal;

public class Music implements Entity{

    private Long id;
    private String title;
    private String artist;
    private String audioPath;
    private String imagePath;
    private BigDecimal price;

    public Music() {}

    public Music(Long id, String title, String artist,
                 String audioPath, String imagePath, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.audioPath = audioPath;
        this.imagePath = imagePath;
        this.price = price;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

