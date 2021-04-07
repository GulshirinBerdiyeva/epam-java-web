package com.epam.task.web.project.entity;

public class Album implements Entity {

    private final Long id;
    private final Long musicID;
    private String title;

    public Album(Long id, Long musicID, String title) {
        this.id = id;
        this.musicID = musicID;
        this.title = title;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getMusicID() {
        return musicID;
    }

    public String getTitle() {
        return title;
    }

}
