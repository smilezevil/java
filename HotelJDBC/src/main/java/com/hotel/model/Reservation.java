package com.hotel.model;

import java.time.LocalDateTime;

public class Reservation {
    private int reservationId;
    private int guestsCount;
    private LocalDateTime checkInDatetime;
    private LocalDateTime checkOutDatetime;
    private int clientId;
    private int roomId;

    public Reservation() {}

    public Reservation(int reservationId, int guestsCount,
                       LocalDateTime checkInDatetime, LocalDateTime checkOutDatetime,
                       int clientId, int roomId) {
        this.reservationId = reservationId;
        this.guestsCount = guestsCount;
        this.checkInDatetime = checkInDatetime;
        this.checkOutDatetime = checkOutDatetime;
        this.clientId = clientId;
        this.roomId = roomId;
    }

    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }
    public int getGuestsCount() { return guestsCount; }
    public void setGuestsCount(int guestsCount) { this.guestsCount = guestsCount; }
    public LocalDateTime getCheckInDatetime() { return checkInDatetime; }
    public void setCheckInDatetime(LocalDateTime checkInDatetime) { this.checkInDatetime = checkInDatetime; }
    public LocalDateTime getCheckOutDatetime() { return checkOutDatetime; }
    public void setCheckOutDatetime(LocalDateTime checkOutDatetime) { this.checkOutDatetime = checkOutDatetime; }
    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }
    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    @Override
    public String toString() {
        return "Reservation{id=" + reservationId +
                ", guests=" + guestsCount +
                ", checkIn=" + checkInDatetime +
                ", checkOut=" + checkOutDatetime +
                ", clientId=" + clientId +
                ", roomId=" + roomId + "}";
    }
}