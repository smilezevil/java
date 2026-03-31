package com.hotel.model;

public class Room {
    private int roomId;
    private String roomNumber;
    private int capacity;
    private int typeId;
    private String roomCol;

    public Room() {}

    public Room(int roomId, String roomNumber, int capacity, int typeId, String roomCol) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.typeId = typeId;
        this.roomCol = roomCol;
    }

    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }
    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }
    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }
    public int getTypeId() { return typeId; }
    public void setTypeId(int typeId) { this.typeId = typeId; }
    public String getRoomCol() { return roomCol; }
    public void setRoomCol(String roomCol) { this.roomCol = roomCol; }

    @Override
    public String toString() {
        return "Room{id=" + roomId + ", number='" + roomNumber + "', capacity=" + capacity + ", typeId=" + typeId + "}";
    }
}