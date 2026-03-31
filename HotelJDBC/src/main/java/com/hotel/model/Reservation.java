package com.hotel.model;

import java.time.LocalDateTime;

public class Reservation {
    private int reservationId;
    private int guestsCount;
    private LocalDateTime checkInOutDatetime;
    private int clientId;
    private int roomId;

    public Reservation() {}

    public Reservation(int reservationId, int guestsCount, LocalDateTime checkInOutDatetime,
                       int clientId, int roomId) {
        this.reservationId = reservationId;
        this.guestsCount = guestsCount;
        this.checkInOutDatetime = checkInOutDatetime;
        this.clientId = clientId;
        this.roomId = roomId;
    }

    public int getReservationId() { return reservationId; }
    public void setReservationId(int reservationId) { this.reservationId = reservationId; }
    public int getGuestsCount() { return guestsCount; }
    public void setGuestsCount(int guestsCount) { this.guestsCount = guestsCount; }
    public LocalDateTime getCheckInOutDatetime() { return checkInOutDatetime; }
    public void setCheckInOutDatetime(LocalDateTime dt) { this.checkInOutDatetime = dt; }
    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }
    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    @Override
    public String toString() {
        return "Reservation{id=" + reservationId + ", guests=" + guestsCount +
                ", datetime=" + checkInOutDatetime + ", clientId=" + clientId + ", roomId=" + roomId + "}";
    }
}