package com.epam.task.web.project.entity;

import java.math.BigDecimal;

public class User implements Entity{

    private Long id;
    private String username;
    private String password;
    private Role role;
    private BigDecimal cash;
    private int musicAmount;
    private int discount;

    private User(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    private User(Long id, String username, String password,
                 BigDecimal cash, int musicAmount,int discount, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.cash = cash;
        this.musicAmount = musicAmount;
        this.discount = discount;
        this.role = role;
    }

    private User(String username, String password,
                 BigDecimal cash, int musicAmount,int discount, Role role) {
        this.username = username;
        this.password = password;
        this.cash = cash;
        this.musicAmount = musicAmount;
        this.discount = discount;
        this.role = role;
    }

    public static User createAdmin(Long id, String username, String password) {
        return new User(id, username, password, Role.ADMIN);
    }

    public static User createClient(Long id, String username, String password,
                                    BigDecimal cash, int musicAmount, int discount) {

        return new User(id, username, password, cash, musicAmount, discount, Role.CLIENT);
    }

    public static User createClient(String username, String password,
                                    BigDecimal cash, int musicAmount, int discount) {

        return new User(username, password, cash, musicAmount, discount, Role.CLIENT);
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

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

}
