package com.epam.task.web.project.entity;

import java.math.BigDecimal;

public class Music implements Entity{

    private Long id;
    private String title;
    private String artist;
    private String audioFileName;
    private String imageFileName;
    private BigDecimal price;

    public Music() {}

    public Music(Long id, String title, String artist,
                 String audioFileName, String imageFileName, BigDecimal price) {

        this.id = id;
        this.title = title;
        this.artist = artist;
        this.audioFileName = audioFileName;
        this.imageFileName = imageFileName;
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

    public String getAudioFileName() {
        return audioFileName;
    }

    public void setAudioFileName(String audioFileName) {
        this.audioFileName = audioFileName;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

