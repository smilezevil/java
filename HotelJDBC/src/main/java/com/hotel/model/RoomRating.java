package com.hotel.model;


public class RoomRating {
    private int ratingId;
    private float popularityScore;
    private int roomId;

    public RoomRating() {}

    public RoomRating(int ratingId, float popularityScore, int roomId) {
        this.ratingId = ratingId;
        this.popularityScore = popularityScore;
        this.roomId = roomId;
    }

    public int getRatingId() { return ratingId; }
    public void setRatingId(int ratingId) { this.ratingId = ratingId; }
    public float getPopularityScore() { return popularityScore; }
    public void setPopularityScore(float popularityScore) { this.popularityScore = popularityScore; }
    public int getRoomId() { return roomId; }
    public void setRoomId(int roomId) { this.roomId = roomId; }

    @Override
    public String toString() {
        return "RoomRating{id=" + ratingId + ", score=" + popularityScore + ", roomId=" + roomId + "}";
    }
}