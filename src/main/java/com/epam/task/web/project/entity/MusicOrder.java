package com.epam.task.web.project.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class MusicOrder implements Entity{

    private Long id;
    private final Long musicId;
    private final Long userId;
    private Timestamp date;
    private int discount;
    private BigDecimal finalPrice;
    private boolean payment;

    public MusicOrder(Long id, Long musicId, Long userId, Timestamp date, int discount, BigDecimal finalPrice, boolean payment) {
        this.id = id;
        this.musicId = musicId;
        this.userId = userId;
        this.date = date;
        this.discount = discount;
        this.finalPrice = finalPrice;
        this.payment = payment;
    }

    public MusicOrder(Long musicId, Long userId, int discount, BigDecimal finalPrice, boolean payment) {
        this.musicId = musicId;
        this.userId = userId;
        this.discount = discount;
        this.finalPrice = finalPrice;
        this.payment = payment;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Long getMusicId() {
        return musicId;
    }

    public Long getUserId() {
        return userId;
    }

    public Timestamp getDate() {
        return date;
    }

    public int getDiscount() {
        return discount;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public boolean isPayment() {
        return payment;
    }

    public void setPayment(boolean payment) {
        this.payment = payment;
    }
}
