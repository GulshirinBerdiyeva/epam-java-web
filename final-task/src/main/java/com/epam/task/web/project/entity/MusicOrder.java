package com.epam.task.web.project.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class MusicOrder implements Entity{

    private static final String TABLE_NAME = "music_order";

    private Long id;
    private Long userId;
    private Long musicId;
    private LocalDateTime date;
    private int discount;
    private BigDecimal finalPrice;
    private boolean payment;

    public MusicOrder(Long id, Long userId, Long musicId, LocalDateTime date, int discount, BigDecimal finalPrice, boolean payment) {
        this.id = id;
        this.userId = userId;
        this.musicId = musicId;
        this.date = date;
        this.discount = discount;
        this.finalPrice = finalPrice;
        this.payment = payment;
    }

    public MusicOrder(Long userId, Long musicId, int discount, BigDecimal finalPrice, boolean payment) {
        this.userId = userId;
        this.musicId = musicId;
        this.discount = discount;
        this.finalPrice = finalPrice;
        this.payment = payment;
    }

    public static String getTableName() {
        return TABLE_NAME;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MusicOrder that = (MusicOrder) o;

        return discount == that.discount && payment == that.payment && Objects.equals(id, that.id) && Objects.equals(userId, that.userId) &&
               Objects.equals(musicId, that.musicId) && Objects.equals(date, that.date) && Objects.equals(finalPrice, that.finalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, musicId, date, discount, finalPrice, payment);
    }

}