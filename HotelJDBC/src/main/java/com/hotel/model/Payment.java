package com.hotel.model;

import java.math.BigDecimal;


public class Payment {
    private int paymentId;
    private BigDecimal totalAmount;
    private int reservationId;

    public Payment() {}

    public Payment(int paymentId, BigDecimal totalAmount, int reservationId) {
        this.paymentId = paymentId;
        this.totalAmount = totalAmount;
        this.reservationId = reservationId;
    }

    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }

    @Override
    public String toString() {
        return "Payment{id=" + paymentId + ", amount=" + totalAmount + ", reservationId=" + reservationId + "}";
    }
}