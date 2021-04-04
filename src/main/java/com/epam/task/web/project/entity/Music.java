package com.epam.task.web.project.entity;

import java.math.BigDecimal;

public class Music implements Entity{

    private final Long id;
    private String title;
    private String artist;
    private String audioPath;
    private String imagePath;
    private BigDecimal price;

    public Music(Long id, String title, String artist,
                 String audioPath, String imagePath, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.audioPath = audioPath;
        this.imagePath = imagePath;
        this.price = price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public Long getId() {
        return id;
    }
}
