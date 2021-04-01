package com.epam.task.web.project.entity;

import java.math.BigDecimal;

public class Client extends User{

    private BigDecimal cash;
    private int musicAmount;

    public Client(Long id, String name, String login, String password, Role role, BigDecimal cash, int musicAmount) {
        super(id, name, login, password, role);

        this.cash = cash;
        this.musicAmount = musicAmount;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public int getMusicAmount() {
        return musicAmount;
    }

}
