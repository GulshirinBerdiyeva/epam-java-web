package com.epam.task.web.project.entity;

import java.math.BigDecimal;

public class MusicOrder implements Entity{

    private Long id;
    private final Long musicId;
    private final Long userId;
    private String date;
    private int discount;
    private BigDecimal finalPrice;
    private boolean payment;


    public MusicOrder(Long userId, Long musicId, int discount, BigDecimal finalPrice, boolean payment) {
        this.userId = userId;
        this.musicId = musicId;
        this.discount = discount;
        this.finalPrice = finalPrice;
        this.payment = payment;
    }

    public MusicOrder(Long id, Long musicId, Long userId,
                      String date, int discount, BigDecimal finalPrice, boolean payment) {
        this.id = id;
        this.musicId = musicId;
        this.userId = userId;
        this.date = date;
        this.discount = discount;
        this.finalPrice = finalPrice;
        this.payment = payment;
    }

    public Long getMusicId() {
        return musicId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getDate() {
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

    @Override
    public Long getId() {
        return null;
    }
}
