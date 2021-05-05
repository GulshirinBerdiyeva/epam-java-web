package com.epam.task.web.project.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MusicOrder implements Entity{

    private Long id;
    private Long userId;
    private Long musicId;
    private LocalDateTime date;
    private int discount;
    private BigDecimal finalPrice;
    private boolean payment;

    public MusicOrder(Long id, Long userId, Long musicId, LocalDateTime date,
                      int discount, BigDecimal finalPrice, boolean payment) {

        this.id = id;
        this.userId = userId;
        this.musicId = musicId;
        this.date = date;
        this.discount = discount;
        this.finalPrice = finalPrice;
        this.payment = payment;
    }

    public MusicOrder(Long userId, Long musicId, int discount,
                      BigDecimal finalPrice, boolean payment) {

        this.userId = userId;
        this.musicId = musicId;
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

    public LocalDateTime getDate() {
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
