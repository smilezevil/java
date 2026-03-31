package com.hotel.model;

import java.math.BigDecimal;

public class RoomType {
    private int typeId;
    private String typeName;
    private BigDecimal pricePerNight;

    public RoomType() {}

    public RoomType(int typeId, String typeName, BigDecimal pricePerNight) {
        this.typeId = typeId;
        this.typeName = typeName;
        this.pricePerNight = pricePerNight;
    }

    public int getTypeId() { return typeId; }
    public void setTypeId(int typeId) { this.typeId = typeId; }
    public String getTypeName() { return typeName; }
    public void setTypeName(String typeName) { this.typeName = typeName; }
    public BigDecimal getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(BigDecimal pricePerNight) { this.pricePerNight = pricePerNight; }

    @Override
    public String toString() {
        return "RoomType{id=" + typeId + ", name='" + typeName + "', price=" + pricePerNight + "}";
    }
}