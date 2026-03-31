package com.hotel.model;

public class Client {
    private int clientId;
    private String fullName;
    private String passportData;

    public Client() {}

    public Client(int clientId, String fullName, String passportData) {
        this.clientId = clientId;
        this.fullName = fullName;
        this.passportData = passportData;
    }

    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getPassportData() { return passportData; }
    public void setPassportData(String passportData) { this.passportData = passportData; }

    @Override
    public String toString() {
        return "Client{id=" + clientId + ", name='" + fullName + "', passport='" + passportData + "'}";
    }
}