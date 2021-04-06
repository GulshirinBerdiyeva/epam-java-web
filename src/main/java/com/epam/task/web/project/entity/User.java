package com.epam.task.web.project.entity;

import java.math.BigDecimal;

public class User implements Entity{

    private final Long id;
    private String username;
    private String password;
    private Role role;
    private BigDecimal cash;
    private int musicAmount;

    private User(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    private User(Long id, String username, String password, Role role, BigDecimal cash, int musicAmount) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.cash = cash;
        this.musicAmount = musicAmount;
    }

    public static User getAdmin(Long id, String username, String password) {
        return new User(id, username, password, Role.ADMIN);
    }

    public static User getClient(Long id, String username, String password, BigDecimal cash, int musicAmount) {
        return new User(id, username, password, Role.CLIENT, cash, musicAmount);
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public int getMusicAmount() {
        return musicAmount;
    }

    public void setMusicAmount(int musicAmount) {
        this.musicAmount = musicAmount;
    }
}
